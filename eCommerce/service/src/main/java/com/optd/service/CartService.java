package com.optd.service;

import com.optd.common.dto.CartDto;
import com.optd.common.dto.CartSaveDto;
import com.optd.common.dto.ProductDto;
import com.optd.entity.Cart;

import java.util.List;

public interface CartService {
    void addToCart(Integer productId);
    void removeFromCart(Integer cartId);
    void updateProductQuantity(Integer cartId, Integer quantity);
    List<CartDto> retrieveCartDtoList();
    void addMultipleProductToCart(List<CartSaveDto> dtoList);


}
