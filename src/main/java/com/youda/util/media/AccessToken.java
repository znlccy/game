package com.youda.util.media;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.TreeMap;

import static io.agora.media.Utils.crc32;

public class AccessToken {

    public enum Privileges {
        kJoinChannel(1),
        kPublishAudioStream(2),
        kPublishVideoStream(3),
        kPublishDataStream(4),

        kPublishAudiocdn(5),
        kPublishVideoCdn(6),
        kRequestPublishAudioStream(7),
        kRequestPublishVideoStream(8),
        kRequestPublishDataStream(9),
        kInvitePublishAudioStream(10),
        kInvitePublishVideoStream(11),
        kInvitePublishDataStream(12),

        kAdministrateChannel(101);

        public short intValue;

        Privileges(int value) {
            intValue = (short) value;
        }
    }

    public String appId;
    public String appCertificate;
    public String channelName;
    public String uid;
    public byte[] signature;
    public byte[] messageRawContent;
    public long crcChannelName;
    public byte[] crcChannelNames2;
    public long crcUid;
    public byte[] crcUids2;
    public PrivilegeMessage message;

    public int expiredTs;

    public AccessToken(String appId, String appCertificate, String channelName, String uid, int ts, int salt) {
        this.appId = appId;
        this.appCertificate = appCertificate;
        this.channelName = channelName;
        this.uid = uid;
        this.crcChannelName = 0;
        this.crcUid = 0;
        if (this.message == null) {
            this.message = new PrivilegeMessage(salt, ts, new TreeMap<Short, Integer>());
        }
        this.message.salt = salt;
        this.message.ts = ts;
    }

    public String build() {
        this.messageRawContent = Utils.pack(this.message);
        this.signature = generateSignature(appCertificate
                , appId
                , channelName
                , uid
                , messageRawContent);
        this.crcChannelName = crc32(this.channelName) & 0xffffffff;
        byte[] crcChannelNames = longToBytes(this.crcChannelName);
        this.crcChannelNames2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            crcChannelNames2[i] = crcChannelNames[i];
        }
        this.crcUid = crc32(this.uid) & 0xffffffff;
        byte[] crcUids = longToBytes(this.crcUid);
        this.crcUids2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            crcUids2[i] = crcUids[i];
        }
        PackContent packContent = new PackContent(signature, crcChannelNames2, crcUids2, this.messageRawContent);
        byte[] content = Utils.pack(packContent);
        String result = getVersion() + this.appId + Utils.base64Encode(content);
        return result;
    }

    public byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES).order(ByteOrder.LITTLE_ENDIAN);
        buffer.putLong(x);
        return buffer.array();
    }

    public void addPrivilege(Privileges privilege, int timeoutFromNow) {
        message.messages.put(privilege.intValue, timeoutFromNow);
    }

    public static String getVersion() {
        return "006";
    }

    public static byte[] generateSignature(String appCertificate
            , String appID
            , String channelName
            , String uid
            , byte[] message) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            baos.write(appID.getBytes());
            baos.write(channelName.getBytes());
            baos.write(uid.getBytes());
            baos.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Utils.hmacSign(appCertificate, baos.toByteArray());
    }

    public boolean fromString(String channelKeyString) {
        if (channelKeyString.substring(0, Utils.VERSION_LENGTH) != getVersion()) {
            return false;
        }
        try {
            this.appId = channelKeyString.substring(Utils.VERSION_LENGTH, Utils.APP_ID_LENGTH);
            PackContent packContent = new PackContent();
            Utils.unpack(channelKeyString.substring(Utils.VERSION_LENGTH + Utils.APP_ID_LENGTH, channelKeyString.length()).getBytes(), packContent);
            this.signature = packContent.signature;
            this.crcChannelNames2 = packContent.crcChannelName;
            this.crcUids2 = packContent.crcUid;
            this.messageRawContent = packContent.rawMessage;
            Utils.unpack(this.messageRawContent, this.message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public class PrivilegeMessage implements Packable2 {
        public int salt;
        public int ts;
        public TreeMap<Short, Integer> messages;

        public PrivilegeMessage(int salt, int ts, TreeMap<Short, Integer> messages) {
            this.salt = salt;
            this.ts = ts;
            this.messages = messages;
        }

        @Override
        public ByteBuf marshall(ByteBuf out) {
            return out.put(salt).put(ts).putTreeMap(messages);
        }

        @Override
        public void unmrshall(ByteBuf in) {
            this.salt = in.readInt();
            this.ts = in.readInt();
            this.messages = in.readTreeMap();
        }
    }

    public class PackContent implements Packable2 {
        public byte[] signature;
        public byte[] crcChannelName;
        public byte[] crcUid;
        public byte[] rawMessage;

        public PackContent() {
        }

        public PackContent(byte[] signature, byte[] crcChannelName, byte[] crcUid, byte[] rawMessage) {
            this.signature = signature;
            this.crcChannelName = crcChannelName;
            this.crcUid = crcUid;
            this.rawMessage = rawMessage;
        }

        @Override
        public ByteBuf marshall(ByteBuf out) {
            return out.put(signature).put2(crcChannelName).put2(crcUid).put(rawMessage);
        }

        @Override
        public void unmrshall(ByteBuf in) {
            this.signature = in.readBytes2();
            this.crcChannelName = in.readBytes3();
            this.crcUid = in.readBytes3();
            this.rawMessage = in.readBytes2();
        }
    }
}
