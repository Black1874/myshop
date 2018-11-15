package com.black.myshop.dao;

import com.black.myshop.entity.BaseEntity;

import java.util.List;



public interface BaseDao<T extends BaseEntity> {

    /**
     * 按页查询
     * @return
     */
    List<T> queryPage(T t);

    /**
     * 获取记录总数
     * @return
     */
    int getCount();

    /**
     * 删除单个
     */
    int delete(T t);


    /**
     * 增加
     */
    int save(T t);


    /**
     * 修改全部
     */
    int update(T t);


    /**
     * 根据Id获取单个信息
     */
    T getById(T t);


}
