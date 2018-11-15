package com.black.myshop.service.impl;

import com.black.myshop.dao.UserDao;
import com.black.myshop.entity.Result;
import com.black.myshop.entity.User;
import com.black.myshop.service.UserService;
import com.black.myshop.utils.BeanValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.validation.Validator;
import java.util.ArrayList;

@Service
public class UserServiceImpl extends BaseServiceImpl<User,UserDao> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public Result save(User user) {
        String str = BeanValidator.validator(user);
//        Validator validator= BeanValidator.validator;
        if(StringUtils.isNotBlank(str)){
            return Result.fail(str);
        }

        String s = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

        return super.save(user);
    }

    public Result login(String email, String password) {

        if (StringUtils.isBlank("password")) {//包括null 和 字符串
            return Result.fail("密码不能为空!");
        }
        String rePassword = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userDao.queryUserByEmailAndPassword(email, rePassword);
        if (user != null) {
            return Result.success(null, user);
        } else {
            return Result.fail("用户名或者密码错误!!");
        }
    }

    @Override
    @Transactional
    public Result deleteMutil(String ids) {
        String[] IDArray= ids.split(",");
        ArrayList<String> list = new ArrayList<>();
        Result result=null;
        int resutlNum=0;

        if(ids!=null){
            for(String id : IDArray){
                if(id!=null&&id!=""){
                list.add(id);
                }
            }
            resutlNum = userDao.deleteMutil(list);
        }else{
            result=Result.fail("选择为空!");
        }
        if(resutlNum>0){
            result=Result.success("",null);
        }else{
            result=Result.fail("删除失败");
        }
        return result;
    }
}
