package com.black.myshop.controller;

import com.alibaba.fastjson.JSON;
import com.black.myshop.entity.Result;
import com.black.myshop.entity.User;
import com.black.myshop.service.RegisterWebUIService;
import com.black.myshop.utils.EmailConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterWebUIController {
    @Autowired
    private RegisterWebUIService registerWebUIService;



    @RequestMapping("/getRegisterForm")
    public String getRegisterForm(){
        return "/register/register";
    }

    @RequestMapping(value="/checkForm",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkForm(User user, Model model){
        Result result = registerWebUIService.checkUser(user);
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    public String registerUser(User user, String vcode,String repassword, Model model, HttpSession session){
        String nvcode =(String)session.getAttribute("KAPTCHA_SESSION_KEY");
        if(!StringUtils.equals(nvcode,vcode)){
            Result result = Result.fail("验证码不正确!!");
            model.addAttribute("result", result);
            return "register/register";
        }
        Result result = registerWebUIService.registerUser(user,repassword);
        if(result.getStatus()==Result.SUCCESS_STATUS){
            Email email = EmailConfig.getEmail();
            try {
                email.addTo(user.getEmail());
                email.setSubject("恭喜你,"+user.getUsername()+"注册成功!");
                email.setMsg("美好的一天!感谢注册,祝您生活愉快!!!");
                email.send();
            } catch (EmailException e) {
                e.printStackTrace();
            }
            model.addAttribute("result", result);
            result.setMessage("注册成功!!");
            return "frontLogin/login";
        }else{
            model.addAttribute("result", result);
            return "register/register";
        }
    }
}





