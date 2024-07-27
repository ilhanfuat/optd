package com.optd.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

    @JsonProperty("cart_id")
    private Integer cartId;

    @JsonProperty("product_quantity")
    private Integer productQuantity;

    @JsonProperty("product")
    private ProductDto productDto;

}
