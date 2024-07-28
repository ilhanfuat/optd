package com.optd.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "productSequenceGenerator")
    @SequenceGenerator(name = "productSequenceGenerator", sequenceName = "seq_product", allocationSize = 1)
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "product_code", nullable = false, length = 50)
    private String productCode;

    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private Double productPrice;

}
