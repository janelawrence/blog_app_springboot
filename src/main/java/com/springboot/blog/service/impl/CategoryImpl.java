package com.springboot.blog.service.impl;


import com.springboot.blog.entity.Category;
import com.springboot.blog.exception.BlogAPIExeption;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper mapper;


    public CategoryImpl(CategoryRepository categoryRepository,
                        ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        // Use all lowercase as category name for consistency and dup prevention
        categoryDto.setName(categoryDto.getName().toLowerCase());
        if(categoryRepository.existsByName(categoryDto.getName())) {
            throw new BlogAPIExeption(HttpStatus.BAD_REQUEST, "Category name already exists");
        }
        Category category = mapToEntity(categoryDto);
        Category new_category = categoryRepository.save(category);
        return mapToDto(new_category);
    }

    @Override
    public CategoryDto getCategoryById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category", "id", id));
        return mapToDto(category);
    }


    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> listOfCats = categories.stream().map(cat -> mapToDto(cat))
                .collect(Collectors.toList());
        return listOfCats;
    }

    @Override
    public void deleteCategory(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto updateCategory(long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        if(categoryRepository.existsByName(categoryDto.getName()) && !categoryDto.getName().equalsIgnoreCase(category.getName())) {
            throw new BlogAPIExeption(HttpStatus.BAD_REQUEST, "New category name already exists");
        }
        category.setDescription(categoryDto.getDescription());
        category.setName(categoryDto.getName().toLowerCase());
        Category updated_category = categoryRepository.save(category);
        return mapToDto(updated_category);
    }

    private CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = mapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    private Category mapToEntity(CategoryDto categoryDto) {
        Category category = mapper.map(categoryDto, Category.class);
        return category;
    }
}
