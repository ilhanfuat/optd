package com.optd.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionDto {

    @JsonProperty("promotion_id")
    private Short promotionId;

    @JsonProperty("promotion_code")
    private String promotionCode;

    @JsonProperty("min_value")
    private Double minValue;

    @JsonProperty("discount_value")
    private Double discountValue;

}
