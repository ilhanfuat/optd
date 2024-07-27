package com.optd.service.impl;

import com.optd.common.converter.CartConverter;
import com.optd.common.dto.CartDto;
import com.optd.common.dto.CartSaveDto;
import com.optd.common.dto.ProductDto;
import com.optd.entity.Cart;
import com.optd.entity.Product;
import com.optd.repository.CartRepository;
import com.optd.repository.ProductRepository;
import com.optd.service.CartService;
import com.optd.service.validator.CartValidator;
import jakarta.transaction.Transactional;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartValidator cartValidator;


    @Override
    public void addToCart(Integer productId) {
        Product product = productRepository.retrieveProductByProductId(productId);
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setProductQuantity(1);
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void removeFromCart(Integer cartId) {
        cartRepository.deleteByCartId(cartId);
    }

    @Override
    @Transactional
    public void updateProductQuantity(Integer cartId, Integer quantity) {
        cartValidator.checkQuantity(cartId, quantity);
        Cart cart = cartRepository.retrieveCartByCartId(cartId);
        if (cart.getProductQuantity() + quantity == 0)
            cartRepository.deleteByCartId(cartId);
        else
            cartRepository.updateProductQuantity(cartId, quantity);

    }

    @Override
    public List<CartDto> retrieveCartDtoList() {
        return CartConverter.convertToCartDtoList(cartRepository.retrieveCartList());
    }

    @Override
    @Transactional
    public void addMultipleProductToCart(List<CartSaveDto> dtoList) {
        List<Cart> dbCartList = cartRepository.retrieveCartList();
        List<Cart> cartList = new ArrayList<>();
        for (CartSaveDto item : dtoList) {
            if (CollectionUtils.isEmpty(dbCartList)) {
                cartValidator.initialQuantity(item.getProductQuantity());
                CartDto cartDto = new CartDto();
                cartDto.setProductQuantity(item.getProductQuantity());
                cartDto.setProductDto(ProductDto.builder().productId(item.getProductId()).build());
                cartList.add(CartConverter.convertToCartEntity(cartDto));
            } else {
                for (Cart dbCart : dbCartList) {
                    if (dbCart.getProduct().getProductId().equals(item.getProductId())) {
                        updateProductQuantity(dbCart.getCartId(), item.getProductQuantity());
                    }
                    else if (ObjectUtils.isEmpty(cartRepository.retrieveCartByProductId(item.getProductId()))) {
                        CartDto cartDto = new CartDto();
                        cartDto.setProductQuantity(item.getProductQuantity());
                        cartDto.setProductDto(ProductDto.builder().productId(item.getProductId()).build());
                        cartList.add(CartConverter.convertToCartEntity(cartDto));
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(cartList))
            cartRepository.saveAll(cartList);
    }

}
