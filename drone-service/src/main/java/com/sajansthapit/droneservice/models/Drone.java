package com.sajansthapit.droneservice.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

import static com.sajansthapit.droneservice.constants.Messages.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drone")
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drone_id_generator")
    @SequenceGenerator(name = "drone_id_generator", sequenceName = "drone_id_sequence", allocationSize = 1)
    private Long id;

    @Column(unique = true, length = 100)
    @NotBlank(message = SERIAL_REQUIRED)
    private String serialNumber;

    @NotBlank(message = MODEL_REQUIRED)
    private String model;

    @NotBlank(message = STATE_REQUIRED)
    private String state;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    private Date updatedDate;


}
