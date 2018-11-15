package com.black.myshop.controller;


import com.black.myshop.entity.Result;
import com.black.myshop.entity.User;
import com.black.myshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController extends BaseController<User,UserService>{
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String index() {
        System.out.println("index");
        return "index";
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(String email, String password, Model model, HttpServletRequest request, HttpSession session) {
        Result result = userService.login(email, password);
        request.getSession().setAttribute("UserLogin",result.getData());
        if (result.getStatus() == Result.SUCCESS_STATUS) {
            session.setAttribute("user",result.getData());
            model.addAttribute("result",result);
            return "main";
        }else{
            model.addAttribute("result",result);
            return "index";
        }

    }
}
