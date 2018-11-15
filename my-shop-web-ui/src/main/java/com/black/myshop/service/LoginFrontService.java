package com.black.myshop.service;

import com.black.myshop.entity.Result;
import com.black.myshop.entity.User;

public interface LoginFrontService {
    Result loginFront(String email, String password);
}
