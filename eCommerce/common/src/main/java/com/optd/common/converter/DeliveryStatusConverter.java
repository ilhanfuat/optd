package com.optd.common.converter;

import com.optd.common.dto.DeliveryStatusDto;
import com.optd.entity.DeliveryStatus;
import org.springframework.beans.BeanUtils;

public class DeliveryStatusConverter {

    public static DeliveryStatusDto convertToDeliveryStatusDto(DeliveryStatus deliveryStatus){
        DeliveryStatusDto deliveryStatusDto = new DeliveryStatusDto();
        BeanUtils.copyProperties(deliveryStatus,deliveryStatusDto);
        return deliveryStatusDto;
    }

    public static DeliveryStatus convertToDeliveryStatusEntity(DeliveryStatusDto deliveryStatusDto){
        DeliveryStatus deliveryStatus = new DeliveryStatus();
        BeanUtils.copyProperties(deliveryStatusDto,deliveryStatus);
        return deliveryStatus;
    }
}
