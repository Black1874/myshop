package com.black.myshop.service.impl;

import com.black.myshop.dao.SlideShowAPIDao;
import com.black.myshop.entity.Content;
import com.black.myshop.service.SlideShowAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SlideShowAPIServiceImpl implements SlideShowAPIService {

    @Autowired
    private SlideShowAPIDao slideShowAPIDao;
    @Override
    public List<Content> getPics(int categoryId) {
        List<Content> list = slideShowAPIDao.getPics(categoryId);
        return list;
    }
}
