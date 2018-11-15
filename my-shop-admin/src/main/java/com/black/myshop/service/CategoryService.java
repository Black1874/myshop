package com.black.myshop.service;

import com.black.myshop.entity.Category;
import com.black.myshop.entity.Result;


public  interface CategoryService extends BaseService<Category> {

    Result getChildren(Category category);

    void setIsParentTrue(Integer id);

    void setIsParentFalse(Integer id);

    Result getCategoryTreeTable(Category category);
}
