package com.sajansthapit.droneservice.models;

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
@Table(name = "drone_shipment")
public class DroneShipment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drone_shipment_id_generator")
    @SequenceGenerator(name = "drone_shipment_id_generator", sequenceName = "drone_shipment_id_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Drone drone;

    @ManyToOne(cascade = CascadeType.ALL)
    private DroneRequest droneRequest;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    private Date updatedDate;
}
