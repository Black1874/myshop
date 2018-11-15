package com.black.myshop.service.impl;

import com.black.myshop.dao.FrontLoginDao;
import com.black.myshop.entity.User;
import com.black.myshop.service.FrontLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class FrontLoginServiceImpl implements FrontLoginService {

    @Autowired
    @Qualifier("frontLoginDao1")
    private FrontLoginDao frontLoginDao;

    @Override
    public User frontLogin(User user) {
        String password=user.getPassword();
        if(password!=null){
            String s = DigestUtils.md5DigestAsHex(password.getBytes());
            user.setPassword(s);
            return frontLoginDao.getUserByEmailAndPassword(user);
        }else{
            return null;
        }

    }
}
