package com.phegondev.AlekEcommerce.repository;

import com.phegondev.AlekEcommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
