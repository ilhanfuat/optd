package com.optd.service.impl;

import com.optd.common.dto.ProductOrderDto;
import com.optd.common.enumeration.DeliveryStatusEnum;
import com.optd.entity.Cart;
import com.optd.entity.ProductOrder;
import com.optd.entity.Promotion;
import com.optd.repository.CartRepository;
import com.optd.repository.DeliveryStatusRepository;
import com.optd.repository.ProductOrderRepository;
import com.optd.repository.PromotionRepository;
import com.optd.service.OrderService;
import com.optd.service.validator.OrderValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    DeliveryStatusRepository deliveryStatusRepository;
    @Autowired
    ProductOrderRepository productOrderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    PromotionRepository promotionRepository;
    @Autowired
    OrderValidator orderValidator;

    @Override
    @Transactional
    public void finishOrder(ProductOrderDto orderDto) {
        if (orderDto.getPromotionCode() != null)
            orderValidator.checkPromotionCode(orderDto);

        ProductOrder order = new ProductOrder();
        order.setTranscriptName(orderDto.getTranscriptName());
        order.setTranscriptSurname(orderDto.getTranscriptSurname());
        order.setPhoneNo(orderDto.getPhoneNo());
        order.setMailAddress(orderDto.getMailAddress());
        order.setShipmentAddress(orderDto.getShipmentAddress());
        order.setDeliveryStatus(deliveryStatusRepository.findByStatusName(DeliveryStatusEnum.SİPARİŞİNİZ_ALINDI.getValue()));
        List<Cart> cartList = cartRepository.retrieveCartList();
        double sum = 0;
        for (Cart cart : cartList) {
            sum +=(cart.getProductQuantity() * cart.getProduct().getProductPrice());
        }
        if (orderDto.getPromotionCode() != null) {
            Promotion promotion = promotionRepository.findPromotionByPromotionCode(orderDto.getPromotionCode());
            order.setTotalPrice(sum - (promotion.getDiscountValue()));
            order.setPromotion(promotion);
        } else
            order.setTotalPrice(sum);

        productOrderRepository.save(order);
        cartRepository.deleteAllCart();
    }
}
