package com.optd.api;

import com.optd.common.dto.ApiSuccess;
import com.optd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/optd/product")
public class ProductAPI {
    @Autowired
    ProductService productService;

    @GetMapping("/list")
    ResponseEntity<?> retrieveProductList() {
        return new ResponseEntity<>(new ApiSuccess("Ürünler Listelendi", productService.retrieveProductDtoList()), HttpStatus.OK);
    }
}
