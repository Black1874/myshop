package com.black.myshop.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

//abstract是因为不想让他实例化
public abstract class HttpClientUtils {
    public static String get(String url){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse httpResponse=null;
        String result=null;
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/18.17763");
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



    public static String post(String url, Map<String,String> ParamMap){
        Set<Map.Entry<String, String>> entrySet = ParamMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
        ArrayList<BasicNameValuePair> list = new ArrayList<>();
        String result=null;
        while(iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            BasicNameValuePair basicNameValuePair = new BasicNameValuePair(next.getKey(), next.getValue());
            list.add(basicNameValuePair);
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/18.17763");
        try {
//        将参数加到post请求中
            httpPost.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
//        执行post请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            result=EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
