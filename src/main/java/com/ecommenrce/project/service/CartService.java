package com.ecommenrce.project.service;

import com.ecommenrce.project.dto.CartDTO;
import org.springframework.stereotype.Service;


public interface CartService {

    CartDTO addProductToCart(Long productId, Integer quantity);
}

