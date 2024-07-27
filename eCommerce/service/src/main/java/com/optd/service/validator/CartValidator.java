package com.optd.service.validator;

import com.optd.common.exception.ErrorMessageUtil;
import com.optd.common.exception.MetaMessageUtil;
import com.optd.entity.Cart;
import com.optd.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartValidator {

    @Autowired
    CartRepository cartRepository;

    private MetaMessageUtil metaMessageUtil = new MetaMessageUtil();

    public void checkQuantity(Integer cartId, Integer quantity){
        metaMessageUtil = new MetaMessageUtil();
        Cart cart = cartRepository.retrieveCartByCartId(cartId);
        if(cart.getProductQuantity() + quantity< 0)
            metaMessageUtil.addMetaMessageWarning("Sepetinizde bulunan ürün adetinden fazla ürün çıkarmak istiyorsunuz. Çıkarmak istediğiniz ürün adetini kontrol ediniz");
        ErrorMessageUtil.checkBusinessException(metaMessageUtil);
    }

    public void initialQuantity(Integer quantity){
        metaMessageUtil = new MetaMessageUtil();
        if(quantity< 0)
            metaMessageUtil.addMetaMessageWarning("Sepetinize eklemek istediğiniz ürünün adeti 0'dan küçük olamaz");
        ErrorMessageUtil.checkBusinessException(metaMessageUtil);
    }
}
