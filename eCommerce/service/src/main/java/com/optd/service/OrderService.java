package com.optd.service;

import com.optd.common.dto.ProductOrderDto;

public interface OrderService {

    void finishOrder(ProductOrderDto orderDto);
}
