package com.optd.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDisplayDto {

    @JsonProperty("order_no")
    private Integer orderNo;

    @JsonProperty("transaction_name")
    private String transactionName;

    @JsonProperty("transaction_surname")
    private String transactionSurname;

    @JsonProperty("phone_no")
    private String phoneNo;

    @JsonProperty("shipment_address")
    private String shipmentAddress;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("product_price")
    private Double productPrice;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("discount_value")
    private Double discountValue;

    @JsonProperty("final_price")
    private Double finalPrice;
    
}
