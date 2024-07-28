package com.optd.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "promotionSequenceGenerator")
    @SequenceGenerator(name = "promotionSequenceGenerator", sequenceName = "seq_promotion", allocationSize = 1)
    @Column(name = "promotion_id", nullable = false)
    private Short promotionId;

    @Column(name = "promotion_code")
    private String promotionCode;

    @Column(name = "min_value")
    private Double minValue;

    @Column(name = "discount_value")
    private Double discountValue;

}
