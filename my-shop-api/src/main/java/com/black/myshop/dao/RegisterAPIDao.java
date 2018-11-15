package com.black.myshop.dao;

import com.black.myshop.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface  RegisterAPIDao {
    /**
     * 用来
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 根据用户名|phone|email查询用户记录数
     * @param user
     * @return
     */
    int query(User user);
}
