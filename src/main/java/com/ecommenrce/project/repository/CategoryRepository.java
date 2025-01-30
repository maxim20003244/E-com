package com.ecommenrce.project.repository;

import com.ecommenrce.project.dto.CategoryDTO;
import com.ecommenrce.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String categoryName);
}

