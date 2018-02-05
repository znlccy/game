package com.youda.util.media;

import java.util.Date;
import java.util.Random;
import java.util.TreeMap;

import static com.youda.util.media.SimpleTokenBuilder.Role.*;
import static com.youda.util.media.AccessToken.Privileges.*;
import static com.youda.util.media.SimpleTokenBuilder.Role.*;

public class SimpleTokenBuilder {
    private String appId;
    private String appCertificate;
    private String channelName;
    private String uid;

    private AccessToken mTokenCreator;

    public static TreeMap<Short, Integer> attendeePrivileges = new TreeMap<Short, Integer>() {
        {
            attendeePrivileges.put(kJoinChannel.intValue, 0);
            attendeePrivileges.put(kPublishAudioStream.intValue, 0);
            attendeePrivileges.put(kPublishVideoStream.intValue, 0);
            attendeePrivileges.put(kPublishDataStream.intValue, 0);
        }
    };

    public static TreeMap<Short, Integer> publisherPrivileges = new TreeMap<Short, Integer>() {
        {
            attendeePrivileges.put(kJoinChannel.intValue, 0);
            attendeePrivileges.put(kPublishAudioStream.intValue, 0);
            attendeePrivileges.put(kPublishVideoStream.intValue, 0);
            attendeePrivileges.put(kPublishDataStream.intValue, 0);
            attendeePrivileges.put(kPublishAudiocdn.intValue, 0);
            attendeePrivileges.put(kPublishVideoCdn.intValue, 0);
            attendeePrivileges.put(kInvitePublishAudioStream.intValue, 0);
            attendeePrivileges.put(kInvitePublishVideoStream.intValue, 0);
            attendeePrivileges.put(kInvitePublishDataStream.intValue, 0);
        }
    };

    public static TreeMap<Short, Integer> subscriberPrivileges = new TreeMap<Short, Integer>() {
        {
            attendeePrivileges.put(kJoinChannel.intValue, 0);
            attendeePrivileges.put(kRequestPublishAudioStream.intValue, 0);
            attendeePrivileges.put(kRequestPublishVideoStream.intValue, 0);
            attendeePrivileges.put(kRequestPublishDataStream.intValue, 0);
        }
    };

    public static TreeMap<Short, Integer> adminPrivileges = new TreeMap<Short, Integer>() {
        {
            attendeePrivileges.put(kJoinChannel.intValue, 0);
            attendeePrivileges.put(kPublishAudioStream.intValue, 0);
            attendeePrivileges.put(kPublishVideoStream.intValue, 0);
            attendeePrivileges.put(kPublishDataStream.intValue, 0);
            attendeePrivileges.put(kAdministrateChannel.intValue, 0);
        }
    };

    public static TreeMap<Integer, TreeMap<Short, Integer>> gRolePrivileges = new TreeMap<Integer, TreeMap<Short, Integer>>() {
        {
            gRolePrivileges.put(Role_Attendee.initValue, attendeePrivileges);
            gRolePrivileges.put(Role_Publisher.initValue, publisherPrivileges);
            gRolePrivileges.put(Role_Subscriber.initValue, subscriberPrivileges);
            gRolePrivileges.put(Role_Admin.initValue, adminPrivileges);
        }
    };

    enum Role {
        Role_Attendee(0),  // for communication
        Role_Publisher(1), // for live broadcast
        Role_Subscriber(2),  // for live broadcast
        Role_Admin(101);

        public int initValue;

        Role(int initValue) {
            this.initValue = initValue;
        }
    }

    public SimpleTokenBuilder(String appId, String appCertificate, String channelName, String uid) {
        this.appId = appId;
        this.appCertificate = appCertificate;
        this.channelName = channelName;
        this.uid = uid;
        int ts = (int) (new Date().getTime() / 1000);
        int salt = new Random().nextInt();
        mTokenCreator = new AccessToken(appId, appCertificate, channelName, uid, ts, salt);
    }

    public boolean initTokenBuilder(String originToken) {
        mTokenCreator.fromString(originToken);
        return true;
    }

    public boolean initPriviliges(Role role) {
        TreeMap<Short, Integer> value = gRolePrivileges.get(role.initValue);
        if (value == null) {
            return false;
        }
        mTokenCreator.message.messages = value;
        return true;
    }

    public void setPrivilege(AccessToken.Privileges privilege, int timeoutFromNow) {
        mTokenCreator.message.messages.put(privilege.intValue, timeoutFromNow);
    }

    public void removePrivilege(AccessToken.Privileges privilege) {
        mTokenCreator.message.messages.remove(privilege.intValue);
    }

    public String buildToken() {
        return mTokenCreator.build();
    }
}
