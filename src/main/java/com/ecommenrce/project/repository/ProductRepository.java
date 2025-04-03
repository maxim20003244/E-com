package com.ecommenrce.project.repository;

import com.ecommenrce.project.model.Category;
import com.ecommenrce.project.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    /*List<Product> findByCategoryOrderByPriceAsc(Category category);*/
    Page<Product> findByCategoryOrderByPriceAsc(Category category, Pageable pageDetails);

    Page<Product> findByProductNameLikeIgnoreCase(String keyWord,Pageable pageDetails);



}

