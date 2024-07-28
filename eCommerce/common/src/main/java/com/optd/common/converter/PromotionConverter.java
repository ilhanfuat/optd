package com.optd.common.converter;

import com.optd.common.dto.PromotionDto;
import com.optd.entity.Promotion;
import org.springframework.beans.BeanUtils;

public class PromotionConverter {

    public static PromotionDto convertToPromotionDto(Promotion promotion){
        PromotionDto promotionDto = new PromotionDto();
        BeanUtils.copyProperties(promotion,promotionDto);
        return promotionDto;
    }

    public static Promotion convertToPromotionEntity(PromotionDto promotionDto){
        Promotion promotion = new Promotion();
        BeanUtils.copyProperties(promotionDto,promotion);
        return promotion;
    }
}
