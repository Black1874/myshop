package com.black.myshop.controller;

import com.alibaba.fastjson.JSON;
import com.black.myshop.entity.User;
import com.black.myshop.service.RegisterAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/registerAPI")
public class RegisterAPIController {

    @Autowired
    private RegisterAPIService registerAPIService;

    @RequestMapping(value = "/registerUser",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String registerUser(User user){
        int i = registerAPIService.RegisterUser(user);
        String msg=i+"";
        return JSON.toJSONString(msg);
    }


    @RequestMapping(value="/checkUser",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkUser(User user){
        int i = registerAPIService.checkUser(user);
//        String str=i+"";
        return JSON.toJSONString(i);
    }
}
