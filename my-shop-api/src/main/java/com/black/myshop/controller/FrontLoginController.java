package com.black.myshop.controller;

import com.alibaba.fastjson.JSON;
import com.black.myshop.entity.User;
import com.black.myshop.service.FrontLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/login")
public class FrontLoginController {

    @Autowired
    private FrontLoginService frontLoginService;

    @RequestMapping(value = "/frontLogin",produces = "application/json;charset=utf-8",method = RequestMethod.POST)
    @ResponseBody
    public String frontLogin(User user, HttpSession session){
        User user1 = frontLoginService.frontLogin(user);
        if(user1==null){
            return "";
        }else{
            return JSON.toJSONString(user1);
        }
    }
}
