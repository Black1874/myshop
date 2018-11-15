package com.black.myshop.service.impl;

import com.alibaba.fastjson.JSON;
import com.black.myshop.entity.Content;
import com.black.myshop.entity.Result;
import com.black.myshop.service.SlideShowWebUIService;
import com.black.myshop.utils.HttpClientUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideShowWebUIServiceImpl implements SlideShowWebUIService {

    @Override
    public Result getSildeShow() {
        String s = HttpClientUtils.get("http://localhost:8081/homeDataAPI/getSlideShow");
        Result result=null;
        if(s!=null && !"".equals(s)){
            List<Content> list = JSON.parseArray(s, Content.class);
                result = Result.success("", list);
            return result;
        }else{
                result = Result.fail("查询失败,网页出现错误!");
                return result;
        }

    }

}
