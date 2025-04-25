package com.phegondev.AlekEcommerce.service.interf;

import com.phegondev.AlekEcommerce.dto.CategoryDto;
import com.phegondev.AlekEcommerce.dto.Response;

public interface CategoryService {

    Response createCategory(CategoryDto categoryDto);
    Response updateCategory(Long categoryId, CategoryDto categoryRequest);
    Response getAllCategories();
    Response getCategoryById(Long categoryId);
    Response deleteCategory(Long categoryId);
}
