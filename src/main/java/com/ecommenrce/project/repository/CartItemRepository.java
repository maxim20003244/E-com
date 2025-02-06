package com.ecommenrce.project.repository;

import com.ecommenrce.project.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem ,Long> {
}
