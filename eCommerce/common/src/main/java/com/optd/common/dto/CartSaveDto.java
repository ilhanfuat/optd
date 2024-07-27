package com.optd.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartSaveDto {

    @JsonProperty("product_id")
    private Integer productId;

    @JsonProperty("product_quantity")
    private Integer productQuantity;

}
