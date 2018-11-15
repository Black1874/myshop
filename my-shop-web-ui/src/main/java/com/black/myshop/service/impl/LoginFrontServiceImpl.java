package com.black.myshop.service.impl;

import com.alibaba.fastjson.JSON;
import com.black.myshop.entity.Result;
import com.black.myshop.entity.User;
import com.black.myshop.service.LoginFrontService;
import com.black.myshop.utils.HttpClientUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginFrontServiceImpl implements LoginFrontService {

    @Override
    public Result loginFront(String email, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("email",email);
        map.put("password", password);
        String str = HttpClientUtils.post("http://localhost:8081/login/frontLogin", map);
        if(str==null || "".equals(str)){
            return Result.fail("登录失败");
        }else{
            User user = JSON.parseObject(str, User.class);
            return Result.success("",user);
        }

    }
}
