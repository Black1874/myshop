package com.black.myshop.service.impl;

import com.black.myshop.dao.RegisterAPIDao;
import com.black.myshop.entity.User;
import com.black.myshop.service.RegisterAPIService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class RegisterAPIServiceImpl implements RegisterAPIService {
    @Autowired
    private RegisterAPIDao registerAPIDao;


    @Override
    public int RegisterUser(User user) {
        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);
        String password = user.getPassword();
        if(StringUtils.isNotBlank(password)){
            String npassword = DigestUtils.md5DigestAsHex(password.getBytes());
            user.setPassword(npassword);
        }

        int num = registerAPIDao.insert(user);
        if (num != 0) {
            return num;
        } else {
            return 0;
        }
    }

    @Override
    public int checkUser(User user) {
        if(StringUtils.isBlank(user.getUsername()) && StringUtils.isBlank(user.getPhone()) && StringUtils.isBlank(user.getEmail())){
            return -1;
        }
        int num = registerAPIDao.query(user);
        if (num != 0) {
            return num;
        }else{
            return  0;
        }
    }
}
