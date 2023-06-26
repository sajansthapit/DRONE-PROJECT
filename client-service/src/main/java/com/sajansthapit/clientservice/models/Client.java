package com.sajansthapit.clientservice.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

import static com.sajansthapit.clientservice.constants.ClientConstants.EMAIL_REGEX;
import static com.sajansthapit.clientservice.constants.Messages.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_generator")
    @SequenceGenerator(name = "client_id_generator", sequenceName = "client_id_sequence", allocationSize = 1)
    private Long id;

    @NotBlank(message = NAME_REQUIRED)
    private String name;

    @Column(unique = true)
    @NotBlank(message = EMAIL_REQUIRED)
    @Pattern(regexp = EMAIL_REGEX, message = INVALID_EMAIL)
    private String email;

    //@NotBlank(message = LATITUDE_REQUIRED)
//    @Pattern(regexp = LOCATION_REGEX)
    private Double latitude;

    //@NotBlank(message = LONGITUDE_REQUIRED)
//    @Pattern(regexp = LOCATION_REGEX)
    private Double longitude;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    private Date updatedDate;

}

