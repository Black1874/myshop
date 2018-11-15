package com.black.myshop.dao;

import com.black.myshop.entity.Content;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideShowAPIDao {
    List<Content> getPics(@Param("categoryId") int categoryId);
}
