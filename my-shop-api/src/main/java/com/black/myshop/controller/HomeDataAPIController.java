package com.black.myshop.controller;

import com.alibaba.fastjson.JSON;
import com.black.myshop.entity.Content;
import com.black.myshop.service.SlideShowAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value="/homeDataAPI")
public class HomeDataAPIController {

    @Autowired
    private SlideShowAPIService slideShowAPIService;

    @RequestMapping(value = "/getSlideShow",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getSlideShow(){
        //数据库中轮播图目录的id为111,此处以后可以动态配置
        List<Content> list = slideShowAPIService.getPics(111);
        if(list!=null && !list.isEmpty()){
             return JSON.toJSONString(list);
        }else{
            return "";
        }

    }

}
