package com.black.myshop.controller;

import com.black.myshop.entity.Result;
import com.black.myshop.service.SlideShowWebUIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping(value="/homeDataWebUI")
public class HomeDataWebUIController {
    @Autowired
    private SlideShowWebUIService slideShowWebUIService;
    @RequestMapping(value = "/getSlideShow")
    public String getSlideShow(Model model){
        Result result = slideShowWebUIService.getSildeShow();
        if (result.getStatus()==Result.SUCCESS_STATUS) {
            model.addAttribute("result",result);
            return "homeData/index";
        }else{
            model.addAttribute("result",result);
            return "homeData/index";
        }
    }
}
