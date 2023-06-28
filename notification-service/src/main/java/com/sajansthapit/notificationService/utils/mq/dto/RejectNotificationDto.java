package com.sajansthapit.notificationService.utils.mq.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RejectNotificationDto {
    private Long clientId;
}
