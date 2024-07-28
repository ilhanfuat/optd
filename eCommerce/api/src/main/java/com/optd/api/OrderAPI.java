package com.optd.api;

import com.optd.common.dto.ApiSuccess;
import com.optd.common.dto.ProductOrderDto;
import com.optd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/optd/order")
public class OrderAPI {

    @Autowired
    OrderService orderService;

    @PostMapping("/finish-order")
    ResponseEntity<?> finishOrder(@RequestBody ProductOrderDto orderDto) {
        orderService.finishOrder(orderDto);
        return new ResponseEntity<>(new ApiSuccess("İşlem Tamamlandı", null), HttpStatus.OK);
    }
}
