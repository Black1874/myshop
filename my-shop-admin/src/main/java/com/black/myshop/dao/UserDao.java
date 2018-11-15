package com.black.myshop.dao;

import com.black.myshop.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseDao<User> {
     User queryUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

     int deleteMutil(@Param("ids") List<String> ids);
}
