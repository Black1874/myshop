package com.black.myshop.entity;


import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    public PageResult() {
    }

    public PageResult(int current, int pageSize) {
        this.current = current;
        this.pageSize = pageSize;
    }

    private int current=1;

    private int pageSize=10;

    private int count;

    private List<T> list;

    private int start;

    public void calStart(){
        this.setStart((current-1)*pageSize);
    }




}
