package com.black.myshop.service.impl;

import com.black.myshop.dao.BaseDao;
import com.black.myshop.entity.PageResult;
import com.black.myshop.entity.Result;
import com.black.myshop.entity.BaseEntity;
import com.black.myshop.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 这是service层封装类的实现类,待完善
 *
 * @param <T>
 * @param <D>
 */

public abstract class BaseServiceImpl<T extends BaseEntity, D extends BaseDao> implements BaseService<T> {

    private T t;
    @Autowired
    private D dao;


    @Override
    @Transactional
    public Result save(T t) {
        int save = dao.save(t);
        if (save > 0) {
            return Result.success("", null);
        } else {
            return Result.fail("添加失败!!");
        }
    }
    @Transactional
    @Override
    public Result delete(T t) {
        int delete = dao.delete(t);
        if (delete > 0) {
            return Result.success("", null);
        } else {
            return Result.fail("删除失败!");
        }
    }
    @Transactional
    @Override
    public Result update(T t) {
        int update = dao.update(t);
        if (update > 0) {
            return Result.success("", null);
        } else {
            return Result.fail("修改失败!");
        }
    }

    @Override
    public Result findPage(T t) {
        t.getPageResult().calStart();
        List list = dao.queryPage(t);
        int count = dao.getCount();
        Result result = new Result();


        if (list !=null) {
            t.getPageResult().setList(list);
           t.getPageResult().setCount(count);
            return Result.success("", t);
        } else {
            return Result.fail("查询失败!");
        }
    }


    @Override
    public Result getById(T t) {
        T t1 = (T)dao.getById(t);
        Result result=null;
        if(t1!=null){
            result=Result.success(null,t1);
        }else{
           result= Result.fail("查询失败");
        }
        return result;
    }
}
