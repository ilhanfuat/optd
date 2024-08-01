package com.optd.service.impl;

import com.optd.common.dto.OrderDisplayDto;
import com.optd.common.dto.ProductOrderDto;
import com.optd.common.enumeration.DeliveryStatusEnum;
import com.optd.entity.Cart;
import com.optd.entity.ProductOrder;
import com.optd.entity.Promotion;
import com.optd.entity.User;
import com.optd.repository.CartRepository;
import com.optd.repository.DeliveryStatusRepository;
import com.optd.repository.ProductOrderRepository;
import com.optd.repository.PromotionRepository;
import com.optd.service.OrderService;
import com.optd.service.security.MainService;
import com.optd.service.validator.OrderValidator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl extends MainService implements OrderService {
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
        List<Cart> cartList = cartRepository.retrieveCartList(getUserId());
        List<ProductOrder> productOrderList = new ArrayList<>();
        for (Cart cart : cartList) {
            ProductOrder order = new ProductOrder();
            order.setOrderNo(productOrderRepository.getMaxOrderNo());
            order.setQuantity(cart.getProductQuantity());
            order.setTranscriptName(orderDto.getTranscriptName());
            order.setTranscriptSurname(orderDto.getTranscriptSurname());
            order.setPhoneNo(orderDto.getPhoneNo());
            order.setMailAddress(orderDto.getMailAddress());
            order.setShipmentAddress(orderDto.getShipmentAddress());
            order.setDeliveryStatus(deliveryStatusRepository.findByStatusName(DeliveryStatusEnum.SİPARİŞİNİZ_ALINDI.getValue()));
            order.setUser(User.builder().userId(getUserId()).build());
            order.setProduct(cart.getProduct());
            if (orderDto.getPromotionCode() != null)
                order.setPromotion(promotionRepository.findPromotionByPromotionCode(orderDto.getPromotionCode()));
            productOrderList.add(order);
        }
        productOrderRepository.saveAll(productOrderList);
        cartRepository.deleteCartByUserId(getUserId());
    }

    @Override
    public List<OrderDisplayDto> retrieveProductOrderList(Integer orderNo) {
        List<Object[]> productOrderList = productOrderRepository.retrieveOrderListByUserIdAndOrderNo(getUserId(), orderNo);
        List<OrderDisplayDto> dtoList = new ArrayList<>();


        double sum = 0;
        for (Object[] obj : productOrderList) {
            sum += ((Double) obj[6] * (Integer) obj[7]);
        }

        Object[] obj = productOrderList.get(0);
        if (obj[8] != null)
            sum = sum - (Double) obj[8];

        for (Object[] item : productOrderList) {
            OrderDisplayDto dto = new OrderDisplayDto();
            int i = -1;
            dto.setOrderNo(Integer.valueOf(ObjectUtils.getDisplayString(item[++i])));
            dto.setTransactionName(ObjectUtils.getDisplayString(item[++i]));
            dto.setTransactionSurname(ObjectUtils.getDisplayString(item[++i]));
            dto.setPhoneNo(ObjectUtils.getDisplayString(item[++i]));
            dto.setShipmentAddress(ObjectUtils.getDisplayString(item[++i]));
            dto.setProductName(ObjectUtils.getDisplayString(item[++i]));
            dto.setProductPrice(Double.valueOf(ObjectUtils.getDisplayString(item[++i])));
            dto.setQuantity(Integer.valueOf(ObjectUtils.getDisplayString(item[++i])));
            if (item[++i] != null)
                dto.setDiscountValue(Double.valueOf(ObjectUtils.getDisplayString(item[i])));
            dto.setFinalPrice(sum);
            dtoList.add(dto);

        }
        return dtoList;
    }
}
