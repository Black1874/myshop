package com.black.myshop;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class testEntity {

    private String username;
    private String email;
    private String[] hobby;
    private Map<String,List<String>> carts;
    private String gender;
    private List<String> favoriteNumber;
    private String choose;
    private String str;
}
