package com.optd.common.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryStatusDto {

    @JsonProperty("delivery_status_id")
    private Short deliveryStatusId;

    @JsonProperty("status_name")
    private String statusName;
}
