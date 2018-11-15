package com.black.myshop.controller;

import com.black.myshop.entity.PageResult;
import com.black.myshop.entity.Result;
import com.black.myshop.entity.User;
import com.black.myshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User, UserService> {
    @Autowired
    private UserService userService;


    @RequestMapping("/getUserPage")
    public String getUserPage(User user,PageResult<User> pageResult, Model model, @ModelAttribute(name = "result") Result result1){
        user.setPageResult(pageResult);
        Result result = userService.findPage(user);
        if (result.getStatus() == Result.SUCCESS_STATUS) {
            result.setMessage(result1.getMessage());
            if (result1.getStatus() == Result.ERROR_STATUS) {
                result.setStatus(Result.ERROR_STATUS);
            }
            model.addAttribute("result", result);
            return "user/userList";

        } else {
            model.addAttribute("result", result);
            return "user/userList";
        }

    }


    /**
     *  获取form页面
     * @return
     */
    @RequestMapping(value="/getForm",method= RequestMethod.GET)
    public String getForm(User user,Model model){
        if(user.getId()==null){
             return "user/form";
        }else{
            return "redirect:/user/getUserById?id="+user.getId() ;
        }
    }

    /**
     *将用户数据存入数据库
     * @param user
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="/saveUser",method= RequestMethod.POST)
    public String saveUser(User user, Model model, RedirectAttributes redirectAttributes){
        Result result = userService.save(user);
        if(result.getStatus()==Result.SUCCESS_STATUS){
            result.setMessage("添加用户成功!!");
            redirectAttributes.addFlashAttribute("result",result);
             return "redirect:/user/getUserPage";
        }else{
            model.addAttribute("result",result);
            return "user/form";
        }
    }

    /**
     * 根据ID获取user信息
     * @param user
     * @param model
     * @return
     */

    @RequestMapping(value="/getUserById",method= RequestMethod.GET)
    public String getUserById(User user,Model model,RedirectAttributes redirectAttributes){
        Result result = userService.getById(user);
            if(result.getStatus()==Result.SUCCESS_STATUS){
                model.addAttribute("result",result);
                return "user/form";
            }else{
                redirectAttributes.addFlashAttribute("msg",result.getMessage());
                return "redirect:/user/getUserPage";
            }
    }

    /**
     * 编辑用户信息
     */
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public String updateUser(User user,Model model,RedirectAttributes redirectAttributes){
        Result result = userService.update(user);
        if(result.getStatus()==Result.SUCCESS_STATUS){
            result.setMessage("更新成功!");
            redirectAttributes.addFlashAttribute("result",result);
            return "redirect:/user/getUserPage";
        }else{
            model.addAttribute("result",result);
            return "user/form";
        }
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    public String deleteUser(User user,RedirectAttributes redirectAttributes){
        Result result = userService.delete(user);
        if(result.getStatus()==Result.SUCCESS_STATUS){
            result.setMessage("删除成功!");
            redirectAttributes.addFlashAttribute("result",result);
            return "redirect:/user/getUserPage";
        }else{
            redirectAttributes.addFlashAttribute("result",result);
            return "redirect:/user/getUserPage";
        }
    }

    @RequestMapping(value = "deleteMutil",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteMutil(String ids, HttpServletResponse response){
        Result result = userService.deleteMutil(ids);
        if(result.getStatus()==Result.SUCCESS_STATUS){
            return "删除成功!";
        }else{
            return result.getMessage();
        }
    }
}
