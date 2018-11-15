package com.black.myshop.utils;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class EmailConfig implements ApplicationContextAware {
    @Value("${email.host.name}")
    private String HostName;

    @Value("${email.smtp.port}")
    private int port;

    @Value("${email.username}")
    private String username;

    @Value("${email.password}")
    private String password;

    private static ApplicationContext applicationContext;

    @Bean
    @Scope(value = "prototype")
    public Email createEmail(){
        Email email = new SimpleEmail();
        email.setHostName(HostName);
        email.setSmtpPort(port);
        email.setAuthentication(username,password);
        email.setSSLOnConnect(true);
        try {
            email.setFrom(username);
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return email;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        EmailConfig.applicationContext=applicationContext;
    }

    public static Email getEmail(){
        return applicationContext.getBean(Email.class);
    }
}
