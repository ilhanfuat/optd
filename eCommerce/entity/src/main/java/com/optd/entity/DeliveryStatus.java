package com.optd.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "delivery_status")
public class DeliveryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "deliveryStatusSequenceGenerator")
    @SequenceGenerator(name = "deliveryStatusSequenceGenerator", sequenceName = "seq_delivery_status", allocationSize = 1)
    @Column(name = "delivery_status_id", nullable = false)
    private Short deliveryStatusId;

    @Column(name = "status_name")
    private String statusName;

}
