package com.sajansthapit.notificationService.models;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client_verification")
public class ClientVerification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_verification_id_generator")
    @SequenceGenerator(name = "client_verification_id_generator", sequenceName = "client_verification_id_sequence", allocationSize = 1)
    private Long id;

    private Long clientId;

    private Long droneId;

    private String droneState;

    private String otpCode;

    private String status;

}
