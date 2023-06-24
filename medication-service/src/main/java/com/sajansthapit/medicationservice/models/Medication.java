package com.sajansthapit.medicationservice.models;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

import static com.sajansthapit.medicationservice.constants.MedicationConstants.MEDICATION_CODE_REGEX;
import static com.sajansthapit.medicationservice.constants.MedicationConstants.MEDICATION_NAME_REGEX;
import static com.sajansthapit.medicationservice.constants.Messages.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medication_id_generator")
    @SequenceGenerator(name = "medication_id_generator", sequenceName = "medication_id_sequence", allocationSize = 1)
    private Long id;

    @NotBlank(message = NAME_REQUIRED)
    @Pattern(regexp = MEDICATION_NAME_REGEX, message = INVALID_NAME)
    private String name;

    @NotBlank(message = WEIGHT_REQUIRED)
    private Double weight;

    @NotBlank(message = CODE_REQUIRED)
    @Pattern(regexp = MEDICATION_CODE_REGEX, message = INVALID_CODE)
    private String code;

    @NotBlank(message = IMAGE_REQUIRED)
    private String image;

    @NotBlank(message = QUANTITY_REQUIRED)
    private Integer quantity;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    private Date updatedDate;
}
