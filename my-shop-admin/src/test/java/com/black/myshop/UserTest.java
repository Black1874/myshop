package com.black.myshop;


import com.black.myshop.entity.PageResult;
import com.black.myshop.entity.Result;
import com.black.myshop.entity.User;
import com.black.myshop.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})

public class UserTest {
    @Autowired
    private UserServiceImpl userServiceImpl;


    @Test
    public void testUserService() {
        User user = new User();
        user.setPageResult(new PageResult<User>());
        Result result = userServiceImpl.findPage(user);
        System.out.println(result.getData());
    }
}
