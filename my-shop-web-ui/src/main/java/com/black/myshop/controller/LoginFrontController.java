package com.black.myshop.controller;

import com.black.myshop.entity.Result;
import com.black.myshop.service.LoginFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginFrontController {

    @Autowired
    private LoginFrontService loginFrontService;

    @RequestMapping(value="/frontLogin")
    public String frontLogin(String email, String password, Model model, HttpSession session){

        Result result = loginFrontService.loginFront(email, password);
        if(result.getStatus()==Result.SUCCESS_STATUS){
            session.setAttribute("user",result.getData());
            result.setMessage("登录成功!");
            //有待修改,登陆成功需要去后台请求数据到index吗??
            return "redirect:/getSlideShow";
        }else{
            model.addAttribute("result",result);
            return "frontLogin/login";
        }
    }


    @RequestMapping(value="/getLoginForm")
    public String getLoginForm(){
        return "frontLogin/login";
    }
}
