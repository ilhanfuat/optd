package com.optd.common.converter;

import com.optd.common.dto.CartDto;
import com.optd.entity.Cart;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CartConverter {

    public static CartDto convertToCartDto(Cart cart){
        CartDto cartDto = new CartDto();
        BeanUtils.copyProperties(cart,cartDto);
        if(cart.getProduct()!=null && cart.getProduct().getProductId()!=null )
            cartDto.setProductDto(ProductConverter.convertToProductDto(cart.getProduct()));
        return cartDto;
    }

    public static Cart convertToCartEntity(CartDto cartDto){
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartDto,cart);
        if(cartDto.getProductDto()!=null && cartDto.getProductDto().getProductId()!=null )
            cart.setProduct(ProductConverter.convertToProductEntity(cartDto.getProductDto()));
        return cart;
    }

    public static List<CartDto> convertToCartDtoList(List<Cart> cartList){
        List<CartDto> cartDtoList = new ArrayList<>();
        cartList.forEach(item -> cartDtoList.add(convertToCartDto(item)));
        return cartDtoList;
    }

}
