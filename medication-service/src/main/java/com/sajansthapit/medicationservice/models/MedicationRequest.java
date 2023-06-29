package com.sajansthapit.medicationservice.models;

import com.sajansthapit.medicationservice.constants.Messages;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medication_request")
public class MedicationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medication_request_id_generator")
    @SequenceGenerator(name = "medication_request_id_generator", sequenceName = "medication_request_id_sequence", allocationSize = 1)
    private Long id;

    private Long clientId;

    private String uniqueId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Medication medication;

    @Min(value = 1, message = Messages.QUANTITY_MIN)
    private Integer quantity;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    private Date updatedDate;


}
