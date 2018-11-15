package com.black.myshop.entity;

import com.black.myshop.utils.PattenUtil;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class User extends BaseEntity<User> {

    @Length(min = 2,max=10,message = "用户名必须在3个到10之内!!")
    private String username;

    @Length(min=6,max=20,message = "密码必须在3个到10之内!")
    private String password;

    @NotBlank(message = "号码不能为空!!")
    @Pattern(regexp = PattenUtil.PHONE_PATTEN,message="电话号码格式不正确!!")
    private String phone;

    @NotBlank(message = "邮箱不能为空!!")
    @Pattern(regexp = PattenUtil.EMAIL_PATTEN,message = "邮箱格式不正确!!")
    private String email;

    public User() {
    }

    public User(String username, String password, String phone, String email) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

}
