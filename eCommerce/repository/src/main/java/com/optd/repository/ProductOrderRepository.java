package com.optd.repository;

import com.optd.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {

    @Query(value = "select coalesce(max(po.order_no+1),1) from product_order po",nativeQuery = true)
    Integer getMaxOrderNo();

    @Query(value = " select po.order_no,po.transcript_name,po.transcript_surname,po.phone_no,po.shipment_address,p.product_name,p.product_price,po.quantity,p2.discount_value from product_order po " +
                   " join product p on p.product_id =po.product_id  " +
                   " left join promotion p2 on p2.promotion_id = po.promotion_id  " +
                   " where po.user_id =:userId and po.order_no =:orderNo" ,nativeQuery = true)
    List<Object[]> retrieveOrderListByUserIdAndOrderNo(@Param("userId") Integer userId, @Param("orderNo") Integer orderNo);

}
