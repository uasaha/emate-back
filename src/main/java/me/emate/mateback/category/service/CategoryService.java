package me.emate.mateback.category.service;

import me.emate.mateback.category.dto.CategoryListResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryListResponseDto> findAllCategories();

    void createCategory(String name);

    void deleteCategory(String name);
}
