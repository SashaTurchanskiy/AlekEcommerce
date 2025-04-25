package com.phegondev.AlekEcommerce.service.impl;

import com.phegondev.AlekEcommerce.dto.CategoryDto;
import com.phegondev.AlekEcommerce.dto.Response;
import com.phegondev.AlekEcommerce.entity.Category;
import com.phegondev.AlekEcommerce.exception.NotFoundException;
import com.phegondev.AlekEcommerce.mapper.EntityDtoMapper;
import com.phegondev.AlekEcommerce.repository.CategoryRepo;
import com.phegondev.AlekEcommerce.service.interf.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());

        categoryRepo.save(category);

        return Response.builder()
                .status(200)
                .message("Category created successfully")
                .build() ;
    }

    @Override
    public Response updateCategory(Long categoryId, CategoryDto categoryRequest) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->
                new NotFoundException("Category not found"));

        category.setName(categoryRequest.getName());

        categoryRepo.save(category);

        return Response.builder()
                .status(200)
                .message("Category updated successfully")
                .build();
    }

    @Override
    public Response getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                .map(entityDtoMapper::mapCategoryToDto).toList();

        return Response.builder()
                .status(200)
                .message("Categories retrieved successfully")
                .categoryList(categoryDtos)
                .build();
    }

    @Override
    public Response getCategoryById(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->
                new NotFoundException("Category not found"));
        CategoryDto categoryDto = entityDtoMapper.mapCategoryToDto(category);

        return Response.builder()
                .status(200)
                .category(categoryDto)
                .build();
    }

    @Override
    public Response deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->
                new NotFoundException("Category not found"));

        categoryRepo.delete(category);

        return Response.builder()
                .status(200)
                .message("Category deleted successfully")
                .build();
    }
}
