package com.optd.service.impl;

import com.optd.common.converter.CartConverter;
import com.optd.common.dto.CartDto;
import com.optd.common.dto.CartSaveDto;
import com.optd.common.dto.ProductDto;
import com.optd.entity.Cart;
import com.optd.entity.Product;
import com.optd.entity.User;
import com.optd.repository.CartRepository;
import com.optd.repository.ProductRepository;
import com.optd.service.CartService;
import com.optd.service.security.MainService;
import com.optd.service.validator.CartValidator;
import javax.transaction.Transactional;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl extends MainService implements CartService {

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
        cart.setUser(User.builder().userId(getUserId()).build());
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
        return CartConverter.convertToCartDtoList(cartRepository.retrieveCartList(getUserId()));
    }

    @Override
    @Transactional
    public void addMultipleProductToCart(List<CartSaveDto> dtoList) {
        List<Cart> dbCartList = cartRepository.retrieveCartList(getUserId());
        List<Cart> cartList = new ArrayList<>();
        for (CartSaveDto item : dtoList) {
            if (CollectionUtils.isEmpty(dbCartList)) {
                cartValidator.initialQuantity(item.getProductQuantity());
                CartDto cartDto = new CartDto();
                cartDto.setProductQuantity(item.getProductQuantity());
                cartDto.setProductDto(ProductDto.builder().productId(item.getProductId()).build());
                Cart cart= CartConverter.convertToCartEntity(cartDto);
                cart.setUser(User.builder().userId(getUserId()).build());
                cartList.add(cart);
            } else {
                for (Cart dbCart : dbCartList) {
                    if (dbCart.getProduct().getProductId().equals(item.getProductId())) {
                        updateProductQuantity(dbCart.getCartId(), item.getProductQuantity());
                    }
                    else if (ObjectUtils.isEmpty(cartRepository.retrieveCartByProductId(item.getProductId()))) {
                        CartDto cartDto = new CartDto();
                        cartDto.setProductQuantity(item.getProductQuantity());
                        cartDto.setProductDto(ProductDto.builder().productId(item.getProductId()).build());
                        Cart cart= CartConverter.convertToCartEntity(cartDto);
                        cart.setUser(User.builder().userId(getUserId()).build());
                        cartList.add(cart);
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(cartList))
            cartRepository.saveAll(cartList);
    }

}
