package com.black.myshop;


import com.black.myshop.utils.EmailConfig;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class EmailTest {

    @Test
    public void emailTest(){
        Email email = EmailConfig.getEmail();
        try {
            email.addTo("1165421248@qq.com");
            email.setFrom("291724140@qq.com");
            email.setSubject("测试,hahaha");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
