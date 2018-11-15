package com.black.myshop;

import com.black.myshop.entity.User;
import com.black.myshop.utils.BeanValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class ValidatorTest {
    @Autowired
    private BeanValidator beanValidator;

    @Test
    public void test(){
        User user = new User();
        user.setUsername("11111");
        user.setPassword("1");
        user.setPhone("13566672224");
        user.setEmail("www@qq.com");
//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
        String str = beanValidator.validator(user);
        System.out.println(str);
    }
}
