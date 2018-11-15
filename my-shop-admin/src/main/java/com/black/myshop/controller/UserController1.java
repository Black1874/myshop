package com.black.myshop.controller;


import com.black.myshop.entity.Result;
import com.black.myshop.entity.User;
import com.black.myshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping(value="/test")
public class UserController1  {

    @Autowired
    private UserService userService;
    /**
     *  获取form页面,用于添加用户
     * @return
     */
    @RequestMapping(value="/getForm1",method= RequestMethod.GET)
    public String getForm(User user,Model model){
        model.addAttribute("user",user);
        return "user/form1";
    }

    /**
     * getByID 用于获取到用户信息,再进行修改
     */
    @RequestMapping("/getById")
    public String getById(User user,Model model){
        Result result = userService.getById(user);
        if(result.getStatus()==Result.SUCCESS_STATUS){
            model.addAttribute("user",result.getData());
            return "user/form1";
        }else{
            return "";
        }
    }


    /**
     * 编辑用户信息
     */
    @RequestMapping(value = "/updateUser",method = RequestMethod.GET)
    public String updateUser(User user, Model model){
//        Result result = userService.update(user);
        Result result = new Result();
        result.setStatus(500);
        if(result.getStatus()==Result.SUCCESS_STATUS){
            return "";
//            model.addAttribute("user",)
        }else{
            model.addAttribute("user",user);
            return "user/form1";
        }
    }

    @RequestMapping("/dispatcher")
    public String dispatcher(User user, RedirectAttributes redirectAttributes){
        if(user.getId()==null){
            redirectAttributes.addFlashAttribute("user",user);
            return "redirect:/test/addUser";
        }else{
            redirectAttributes.addFlashAttribute("user",user);
            return "redirect:/test/updateUser";
        }
    }

    @RequestMapping("/addUser")
    public String addUser(User user,Model model){
        Result result = Result.fail("注册失败!!");
        if(result.getStatus()==Result.SUCCESS_STATUS){
            return "";
        }else{
            model.addAttribute("user", user);
            return "/user/form1";
        }
    }




}
