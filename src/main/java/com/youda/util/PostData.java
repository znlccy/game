package com.youda.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.youda.response.api.AttestationResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostData {

    /*public static void main(String[] args) {
        AttestationResponse attestationResponse = new AttestationResponse();
        attestationResponse.setResponseTime(new Date());
        attestationResponse.setResult("验签成功!");
        attestationResponse.setGoodName("坦克大战");
        attestationResponse.setTotalAmount("6.00");
        attestationResponse.setUserId("22");
        PostData.sendData("http://47.91.106.109/ntank/plat/arab/ios.php",attestationResponse);
    }*/

    public static String sendData(String SEND_URL, AttestationResponse attestationResponse) {
        JSONObject jsonParam = new JSONObject();
        StringBuffer sb=new StringBuffer();
        try {
            // 创建url资源
            URL url = new URL(SEND_URL);
            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);
            // 设置允许输入
            conn.setDoInput(true);
            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            // 转换为字节数组
            byte[] data = (jsonParam.toString()).getBytes();
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "application/json");
            // 开始连接请求
            conn.connect();
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            //发送请求参数
            jsonParam.put("outTradeNo", attestationResponse.getOutTradeNo());
            jsonParam.put("userId", attestationResponse.getUserId());
            jsonParam.put("result", attestationResponse.getResult());
            jsonParam.put("goodName", attestationResponse.getGoodName());
            jsonParam.put("responseTime", attestationResponse.getResponseTime());

            // 写入请求的字符串
            out.write((jsonParam.toString()).getBytes());
            out.flush();
            out.close();

            System.out.println(conn.getResponseCode());

            // 请求返回的状态
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()){
                System.out.println("连接成功");
                // 请求返回的数据
                InputStream in1 = conn.getInputStream();
                try {
                    String readLine=new String();
                    BufferedReader responseReader=new BufferedReader(new InputStreamReader(in1,"UTF-8"));
                    while((readLine=responseReader.readLine())!=null){
                        sb.append(readLine).append("\n");
                    }
                    responseReader.close();
                    System.out.println(sb.toString());

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                System.out.println("error++");

            }

        } catch (Exception e) {

        }

        return sb.toString();

    }

}
