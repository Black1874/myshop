package com.black.myshop.dao;

import com.black.myshop.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends BaseDao<Category>{

    List<Category>  getChildren(Category category);

    void setIsParentTrue(@Param(value="id") Integer id);

    void setIsParentFalse(@Param(value="id") Integer id);

    List<Category> getTreeTable(Category category);
}
