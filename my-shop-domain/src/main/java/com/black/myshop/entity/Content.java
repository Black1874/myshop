package com.black.myshop.entity;

import lombok.Data;


@Data
public class Content extends BaseEntity<Content>{
    private Category category;

    private String title;

    private String subTitle;

    private String titleDesc;

    private String url;

    private String pic1;

    private String pic2;

    private String detail;


}
