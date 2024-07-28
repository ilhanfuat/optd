package com.optd.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductOrderDto {


    @JsonProperty("product_order_id")
    private Integer productOrderId;

    @JsonProperty("transcript_name")
    private String transcriptName;

    @JsonProperty("transcript_surname")
    private String transcriptSurname;

    @JsonProperty("phone_no")
    private String phoneNo;

    @JsonProperty("mail_address")
    private String mailAddress;

    @JsonProperty("shipment_address")
    private String shipmentAddress;


    @JsonProperty("promotion_code")
    private String promotionCode;

    @JsonProperty("delivery_status")
    private DeliveryStatusDto deliveryStatusDto;

}
