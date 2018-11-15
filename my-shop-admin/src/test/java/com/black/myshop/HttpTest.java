package com.black.myshop;

import com.black.myshop.utils.HttpClientUtils;
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
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpTest {

    @Test
    public void httpGetTest(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse httpResponse=null;
        HttpGet httpGet = new HttpGet("https://www.jd.com/?cu=true&utm_source=baidu-pinzhuan&utm_medium=cpc&utm_campaign=t_288551095_baidupinzhuan&utm_term=0f3d30c8dba7459bb52f2eb5eba8ac7d_0_d86b251069a84a19924bc3ee603ef49f");
        httpGet.setHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/18.17763");
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            String s = EntityUtils.toString(entity);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void httpPostTest(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/user/edit");
        httpPost.setHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/18.17763");

//        设置参数
        BasicNameValuePair username = new BasicNameValuePair("username", "admin");
        BasicNameValuePair password = new BasicNameValuePair("password", "admin");
        BasicNameValuePair phone = new BasicNameValuePair("phone", "13567899876");
        BasicNameValuePair email = new BasicNameValuePair("email", "amdin@qq.com");
//      添加到集合中
        ArrayList<BasicNameValuePair> list = new ArrayList<>();
        list.add(username);
        list.add(password);
        list.add(phone);
        list.add(email);

        try {
//        将参数加到post请求中
            httpPost.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
//        执行post请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void HttpClientUtilsGetTest(){
        String s = HttpClientUtils.get("http://image.baidu.com/search/index?tn=baiduimage&ct=201326592&lm=-1&cl=2&ie=gb18030&word=%CD%BC%C6%AC&fr=ala&ala=1&alatpl=others&pos=0");
        String regex="<img>.*</img>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        while(matcher.find()){
            System.out.println(matcher.group());
            matcher.group("dsa");


        }
    }
}
