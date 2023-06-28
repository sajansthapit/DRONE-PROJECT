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
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "battery_log")
public class BatteryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "battery_log_generator")
    @SequenceGenerator(name = "battery_log_generator", sequenceName = "battery_log_sequence", allocationSize = 1)
    private Long id;

    private Long droneId;

    private Double batteryLevel;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    private Date updatedDate;
}
