package com.optd.service.validator;

import com.optd.common.dto.ProductOrderDto;
import com.optd.common.enumeration.PromotionEnum;
import com.optd.common.exception.ErrorMessageUtil;
import com.optd.common.exception.MetaMessageUtil;
import com.optd.entity.Cart;
import com.optd.entity.Promotion;
import com.optd.repository.CartRepository;
import com.optd.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderValidator {

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    CartRepository cartRepository;

    private MetaMessageUtil metaMessageUtil = new MetaMessageUtil();

    public void checkPromotionCode(ProductOrderDto orderDto){
        metaMessageUtil = new MetaMessageUtil();
        Promotion promotion = promotionRepository.findPromotionByPromotionCode(orderDto.getPromotionCode());
        List<Cart> cartList = cartRepository.retrieveCartList();
        double sum = 0;
        for (Cart cart : cartList) {
            sum += (cart.getProductQuantity() * cart.getProduct().getProductPrice());
        }

        if(promotion.getPromotionId().equals(PromotionEnum.PROMO50.getId())) {
            if (PromotionEnum.PROMO50.getMinValue() > sum)
                metaMessageUtil.addMetaMessageWarning("Girmiş olduğunuz kodun minimum sepet tutarına ulaşamadınız. İsterseniz sepetinize başka ürün ekleyebilirsiniz");
        } else if (promotion.getPromotionId().equals(PromotionEnum.PROMO75.getId())){
            if(PromotionEnum.PROMO75.getMinValue() > sum)
                metaMessageUtil.addMetaMessageWarning("Girmiş olduğunuz kodun minimum sepet tutarına ulaşamadınız. İsterseniz sepetinize başka ürün ekleyebilirsiniz");
        } else {
            if(PromotionEnum.PROMO100.getMinValue() > sum)
                metaMessageUtil.addMetaMessageWarning("Girmiş olduğunuz kodun minimum sepet tutarına ulaşamadınız. İsterseniz sepetinize başka ürün ekleyebilirsiniz");

        }

        ErrorMessageUtil.checkBusinessException(metaMessageUtil);
    }
}
