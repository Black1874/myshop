package com.black.myshop.service;

import com.black.myshop.entity.Result;
import com.black.myshop.entity.User;

public interface UserService extends BaseService<User> {

    Result login(String email, String password);

    Result deleteMutil(String ids);
}
