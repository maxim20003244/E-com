package com.ecommenrce.project.service;

import com.ecommenrce.project.dto.CategoryDTO;
import com.ecommenrce.project.dto.CategoryResponse;
import com.ecommenrce.project.model.Category;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder);
    CategoryDTO createCategories(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO category, Long categoryId);
}
