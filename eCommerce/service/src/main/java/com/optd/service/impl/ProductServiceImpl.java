package com.optd.service.impl;

import com.optd.common.converter.ProductConverter;
import com.optd.common.dto.ProductDto;
import com.optd.repository.ProductRepository;
import com.optd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductDto> retrieveProductDtoList() {
        return ProductConverter.convertToProductDtoList(productRepository.retrieveProductList());
    }
}
