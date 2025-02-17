package com.optd.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product_order")
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "orderSequenceGenerator")
    @SequenceGenerator(name = "orderSequenceGenerator", sequenceName = "seq_product_order", allocationSize = 1)
    @Column(name = "product_order_id", nullable = false)
    private Integer productOrderId;

    @Column(name = "order_no", nullable = false)
    private Integer orderNo;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    @Column(name = "transcript_name", nullable = false)
    private String transcriptName;

    @Column(name = "transcript_surname", nullable = false)
    private String transcriptSurname;

    @Column(name = "phone_no", nullable = false)
    private String phoneNo;

    @Column(name = "mail_address", nullable = false)
    private String mailAddress;

    @Column(name = "shipment_address", nullable = false)
    private String shipmentAddress;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "delivery_status_id",nullable = false)
    private DeliveryStatus deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
