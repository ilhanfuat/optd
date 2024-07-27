package com.optd.service;

import com.optd.common.dto.ProductDto;
import com.optd.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductDto> retrieveProductDtoList();
}
