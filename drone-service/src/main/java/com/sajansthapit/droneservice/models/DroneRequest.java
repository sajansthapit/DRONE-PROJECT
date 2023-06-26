package com.sajansthapit.droneservice.models;

import com.sajansthapit.droneservice.constants.DroneConstants;
import com.sajansthapit.droneservice.constants.Messages;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.util.Date;

import static com.sajansthapit.droneservice.constants.Messages.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drone_request")
public class DroneRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drone_request_id_generator")
    @SequenceGenerator(name = "drone_request_id_generator", sequenceName = "drone_request_id_sequence", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String requestId;

    @Min(value = 0, message = INVALID_MIN_WEIGHT)
    @Max(value = 500, message = INVALID_MAX_WEIGHT)
    private Double totalWeight;

    private Long clientId;

    private Double latitude;

    private Double longitude;

    private Double distance;

    private String status;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    private Date updatedDate;

}
