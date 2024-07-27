package com.optd.repository;

import com.optd.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("select c from Cart c where c.cartId=:cartId")
    Cart retrieveCartByCartId(@Param("cartId") Integer cartId);

    @Query("select c from Cart c where c.product.productId=:productId")
    Cart retrieveCartByProductId(@Param("productId") Integer productId);

    @Query("select c from Cart c")
    List<Cart> retrieveCartList();

    @Modifying
    @Query(value = "delete from cart c where c.cart_id =:cartId",nativeQuery = true)
    void deleteByCartId(@Param("cartId")Integer cartId);

    @Modifying
    @Query(value = " update Cart set productQuantity = productQuantity + :quantity where cartId=:cartId")
    void updateProductQuantity(@Param("cartId") Integer cartId,@Param("quantity") Integer quantity);

}
