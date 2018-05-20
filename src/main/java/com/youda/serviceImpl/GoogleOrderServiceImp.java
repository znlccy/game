package com.youda.serviceImpl;

import com.youda.model.GoogleOrder;
import com.youda.model.GoogleToken;
import com.youda.model.Purchase;
import com.youda.service.GoogleOrderService;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleOrderServiceImp implements GoogleOrderService {
    private String access_token;

    @Override
    public PayStatus isPay(GoogleOrder googleOrder, boolean isFirst) {
        if (access_token == null || access_token.length() == 0) {
            access_token = getToken();
        }
        if (access_token == null || access_token.length() == 0) {
            return PayStatus.NO_SUPPORT;
        }
        try {
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(5000);
            factory.setReadTimeout(5000);
            ResponseEntity<Purchase> responseEntity = new RestTemplate(factory).exchange("https://www.googleapis.com/androidpublisher/v3/applications/" +
                    googleOrder.getPackageName()
                    + "/purchases/products/"
                    + googleOrder.getProductId() +
                    "/tokens/" +
                    googleOrder.getPurchaseToken()
                    + "?access_token=" + access_token, HttpMethod.GET, null, Purchase.class);
            System.out.println("=====>Purchase:"+responseEntity.toString());

            if (responseEntity.getBody().isPay()) {
                return PayStatus.SUCCESS;
            } else {
                return PayStatus.ERROR;
            }
        } catch (Exception e) {
            System.out.println("=====>PurchaseException:"+e.toString());
            if (e instanceof HttpClientErrorException && isFirst) {
                if (((HttpClientErrorException) e).getRawStatusCode() == 401) {
                    access_token = getToken();
                    return isPay(googleOrder, false);
                }
            }
            return PayStatus.NO_SUPPORT;

        }
    }

    private String getToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "refresh_token");
        map.add("client_id", "78788825629-m921fq05pt9qs2i0oecg2vgqaho1qggb.apps.googleusercontent.com");
        map.add("client_secret", "Av6S0jS5nK7KD8MRshhLRcXB");
        map.add("refresh_token", "1/lSj2d1O6F-1iQhYG48Mw5mR-e5i0i5-pVTSpumJu8FU");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(
                map, headers);
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        RestTemplate tokenTemplate = new RestTemplate(factory);
        GoogleToken googleToken;
        try {
            googleToken = tokenTemplate.postForObject("https://accounts.google.com/o/oauth2/token", request, GoogleToken.class);
            System.out.println("=====>token:"+googleToken.getAccess_token());
        } catch (Exception e) {
            System.out.println("=====>tokenException:"+e.toString());

            return "";
        }
        return googleToken.getAccess_token();

    }


}
