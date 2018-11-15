package com.black.myshop.service;

import com.black.myshop.entity.User;

public interface RegisterAPIService {
    int RegisterUser(User user);

    int checkUser(User user);

}
