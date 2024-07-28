package com.optd.common.converter;

import com.optd.common.dto.ProductOrderDto;
import com.optd.entity.ProductOrder;

import org.springframework.beans.BeanUtils;

public class OrderConverter {

    public static ProductOrder convertToCartEntity(ProductOrderDto orderDto){
        ProductOrder order = new ProductOrder();
        BeanUtils.copyProperties(orderDto,order);

        if(orderDto.getDeliveryStatusDto()!=null && orderDto.getDeliveryStatusDto().getDeliveryStatusId()!=null )
            order.setDeliveryStatus(DeliveryStatusConverter.convertToDeliveryStatusEntity(orderDto.getDeliveryStatusDto()));

        return order;
    }

}
