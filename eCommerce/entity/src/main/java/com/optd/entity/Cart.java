package com.optd.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "cartSequenceGenerator")
    @SequenceGenerator(name = "cartSequenceGenerator", sequenceName = "seq_cart", allocationSize = 1)
    @Column(name = "cart_id", nullable = false)
    private Integer cartId;

    @Column(name = "product_quantity")
    private Integer productQuantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
