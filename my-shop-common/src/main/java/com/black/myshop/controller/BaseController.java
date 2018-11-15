package com.black.myshop.controller;

import com.black.myshop.entity.BaseEntity;
import com.black.myshop.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController <T extends BaseEntity,S extends BaseService<T>>{

    @Autowired
    private S s;


    }


