package com.optd.service;

import com.optd.common.dto.OrderDisplayDto;
import com.optd.common.dto.ProductOrderDto;


import java.util.List;

public interface OrderService {

    void finishOrder(ProductOrderDto orderDto);

    List<OrderDisplayDto> retrieveProductOrderList(Integer orderNo);

}
