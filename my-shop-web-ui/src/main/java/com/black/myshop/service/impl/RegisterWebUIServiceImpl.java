package com.black.myshop.service.impl;

import com.alibaba.fastjson.JSON;
import com.black.myshop.entity.Result;
import com.black.myshop.entity.User;
import com.black.myshop.service.RegisterWebUIService;
import com.black.myshop.utils.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterWebUIServiceImpl implements RegisterWebUIService {
    @Override
    public Result registerUser(User user,String repassword) {
        if(!StringUtils.equals(repassword,user.getPassword())){
            return Result.fail("两次密码不一致!!");
        }
        Map<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        map.put("email", user.getEmail());
        map.put("phone", user.getPhone());
        String s = HttpClientUtils.post("http://localhost:8081/registerAPI/registerUser", map);
        if (StringUtils.isNotBlank(s)) {
            String str = (String) JSON.parse(s);
            Result result = null;
            if ("0".equals(s)) {
                result = Result.fail("注册失败!!");

            } else {
                result = Result.success("", null);
            }
            return result;
        } else {
            return Result.fail("系统连接出现错误!!请稍后重试!!");
        }
    }

    @Override
    public Result checkUser(User user) {
        Map<String, String> map = new HashMap<>();
        String username = user.getUsername();
        map.put("username", username);
        String email = user.getEmail();
        map.put("email", email);
        String phone = user.getPhone();
        map.put("phone", phone);
        Result result=null;
        if(StringUtils.isBlank(username)&& StringUtils.isBlank(email) && StringUtils.isBlank(phone)){
            return Result.fail("请输入数据!!");
        }else{
            if (StringUtils.isNotBlank(username)) {
                String s = HttpClientUtils.post("http://localhost:8081/registerAPI/checkUser", map);
                result = check( s,"用户名已存在!!");
            }
            if (StringUtils.isNotBlank(email)) {
                String s = HttpClientUtils.post("http://localhost:8081/registerAPI/checkUser", map);
                result =check(s,"邮箱已存在!!");
            }
            if (StringUtils.isNotBlank(phone)) {
                String s = HttpClientUtils.post("http://localhost:8081/registerAPI/checkUser", map);
                result = check(s,"电话号码已存在!!");
            }
            return result;
        }

    }

    private Result check(String s ,String message) {
        if (StringUtils.isNotBlank(s)) {
            Integer num = (Integer) JSON.parse(s);
            Result result = null;
            if (0==num) {
                result = Result.success("", null);
            } else if(-1==num){
                Result.fail("请输入数据!!");
            }else{
                result = Result.fail(message);

            }
            return result;
        }else{
            return Result.fail("系统连接失败,请稍后失败!!");
        }
    }
}
