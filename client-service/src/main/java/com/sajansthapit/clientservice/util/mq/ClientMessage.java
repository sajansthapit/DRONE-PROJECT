package com.sajansthapit.clientservice.util.mq;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientMessage implements Serializable {
    private Long clientId;
    private String latitude;
    private String longitude;
    private String email;

}
