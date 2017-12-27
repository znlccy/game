package com.youda.util;

import com.alibaba.fastjson.JSONObject;
import com.youda.response.api.AttestationResponse;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostData {

    public static void sendData(String SEND_URL, AttestationResponse attestationResponse) {
        try {
            URL url = new URL(SEND_URL);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
                                /* connection.setRequestProperty("Authorization", token);*/
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type","application/json");
            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
            JSONObject obj = new JSONObject();
            obj.put("out_trade_no", attestationResponse.getOutTradeNo());
            obj.put("responseTime", attestationResponse.getResponseTime());
            obj.put("result", attestationResponse.getResult());
            obj.put("goodName", attestationResponse.getGoodName());

            out.writeBytes(obj.toString());
            out.flush();
            out.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
