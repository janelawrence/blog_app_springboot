package com.springboot.blog.service;

import com.springboot.blog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(long id);

    List<CategoryDto> getAllCategory();

    void deleteCategory(long id);

    CategoryDto updateCategory(long id, CategoryDto categoryDto);
}
