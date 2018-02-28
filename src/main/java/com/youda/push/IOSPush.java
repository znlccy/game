package com.youda.push;

import javapns.Push;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateTime:2018/2/25 10:35
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: IOS推送
 */

@Component
public class IOSPush {

    public void sendPush() {
        String deviceToken = "d4b3c5f3d497554f56f6f9791872666ae06e3b4e7abad6f4792dcd030007db91";
        String alert = "给你发信息了";//push的内容
        int badge = 3;//图标小红圈的数值
        String sound = "default";//铃音

        List<String> tokens = new ArrayList<String>();
        tokens.add(deviceToken);
        String certificatePath = "D:/ZshPush.p12";
        String certificatePassword = "123456";//此处注意导出的证书密码不能为空因为空密码会报错
        boolean sendCount = true;

        try
        {
            PushNotificationPayload payLoad = new PushNotificationPayload();
            payLoad.addAlert(alert); // 消息内容
            payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值

            if (!StringUtils.isBlank(sound))
            {
                payLoad.addSound(sound);//铃音
            }
            PushNotificationManager pushManager = new PushNotificationManager();
            //true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(certificatePath, certificatePassword, false));
            List<PushedNotification> notifications = new ArrayList<PushedNotification>();
            // 发送push消息
            if (sendCount)
            {
                Device device = new BasicDevice();
                device.setToken(tokens.get(0));
                PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
                notifications.add(notification);
            }
            else
            {
                List<Device> device = new ArrayList<Device>();
                for (String token : tokens)
                {
                    device.add(new BasicDevice(token));
                }
                notifications = pushManager.sendNotifications(payLoad, device);
            }
            pushManager.stopConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
