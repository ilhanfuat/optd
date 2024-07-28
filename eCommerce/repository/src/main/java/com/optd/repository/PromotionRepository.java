package com.optd.repository;

import com.optd.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Short> {

    @Query("select p from Promotion p where p.promotionCode=:promotionCode")
    Promotion findPromotionByPromotionCode(String promotionCode);
}
