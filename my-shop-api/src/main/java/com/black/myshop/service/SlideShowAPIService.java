package com.black.myshop.service;

import com.black.myshop.entity.Content;

import java.util.List;

public interface SlideShowAPIService {
    List<Content> getPics(int categoryId);
}
