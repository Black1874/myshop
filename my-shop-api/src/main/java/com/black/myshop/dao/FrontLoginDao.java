package com.black.myshop.dao;

import com.black.myshop.entity.User;
import org.springframework.stereotype.Repository;

@Repository(value="frontLoginDao1")
public interface FrontLoginDao{

    User getUserByEmailAndPassword(User user);

}
