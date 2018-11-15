package com.black.myshop.entity;

import java.util.Date;

public abstract class BaseEntity<T>{
    private Integer id;
    private Date created;
    private Date updated;
    private PageResult<T> pageResult;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public PageResult<T> getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult<T> pageResult) {
        this.pageResult = pageResult;
    }
}
