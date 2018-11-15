package com.black.myshop.entity;

import lombok.Data;

@Data
public class Category extends BaseEntity<Category>{
    private Integer id;

    private String name;

    private Category parent;

    private Integer status;

    private Integer order;

    private Integer isParent;
}
