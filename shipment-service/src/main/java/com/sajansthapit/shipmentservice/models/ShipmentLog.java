package com.sajansthapit.shipmentservice.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipment_log")
public class ShipmentLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_log_generator")
    @SequenceGenerator(name = "shipment_log_generator", sequenceName = "shipment_log_sequence", allocationSize = 1)
    private Long id;

    private Long droneId;

    private Double distance;

    private String droneState;

    private Double battery;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    private Date updatedDate;
}
