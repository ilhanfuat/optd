package com.optd.common.converter;

import com.optd.common.dto.ProductDto;
import com.optd.entity.Product;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductConverter {
    public static ProductDto convertToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        return productDto;
    }
    public static Product convertToProductEntity(ProductDto productDto){
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        return product;
    }

    public static List<ProductDto> convertToProductDtoList(List<Product> productList){
        List<ProductDto> productDtoList = new ArrayList<>();
        productList.forEach(item -> productDtoList.add(convertToProductDto(item)));
        return productDtoList;
    }
}
