package com.black.myshop.service;

import com.black.myshop.entity.Result;
import com.black.myshop.entity.User;

public interface RegisterWebUIService {

    Result registerUser(User user,String repassword);

    Result checkUser(User user);
}
