package com.black.myshop.service;

import com.black.myshop.entity.BaseEntity;
import com.black.myshop.entity.Result;

/**
 * 通用的Service层
 * @param <T>
 */

public interface BaseService<T extends BaseEntity> {


    Result save(T t);

    Result delete(T t);

    Result update(T t);

    Result findPage(T t);

    Result getById(T t);
}
