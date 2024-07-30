package com.optd.api;

import com.optd.common.dto.ApiSuccess;
import com.optd.common.dto.CartSaveDto;
import com.optd.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/optd/cart")
public class CartAPI {

    @Autowired
    CartService cartService;

    @GetMapping("/list")
    ResponseEntity<?> retrieveCartList() {
        return new ResponseEntity<>(new ApiSuccess("Sepet Listelendi", cartService.retrieveCartDtoList()), HttpStatus.OK);
    }

    @PostMapping("/add-to-cart/{productId}")
    ResponseEntity<?> addToCart(@PathVariable Integer productId) {
        cartService.addToCart(productId);
        return new ResponseEntity<>(new ApiSuccess("Kayıt Başarılı", null), HttpStatus.OK);
    }

    @PostMapping("/add-multiple-product-to-cart")
    ResponseEntity<?> addMultipleToCart(@RequestBody List<CartSaveDto> dtoList) {
        cartService.addMultipleProductToCart(dtoList);
        return new ResponseEntity<>(new ApiSuccess("Kayıt Başarılı", null), HttpStatus.OK);
    }

    @PutMapping("/update-quantity/{cartId}/{quantity}")
    ResponseEntity<?> removeFromCart(@PathVariable Integer cartId, @PathVariable Integer quantity) {
        cartService.updateProductQuantity(cartId, quantity);
        return new ResponseEntity<>(new ApiSuccess("Güncellendi", null), HttpStatus.OK);
    }

    @DeleteMapping("/remove-from-cart/{cartId}")
    ResponseEntity<?> removeFromCart(@PathVariable Integer cartId) {
        cartService.removeFromCart(cartId);
        return new ResponseEntity<>(new ApiSuccess("Kayıt Silindi", null), HttpStatus.OK);
    }
}
