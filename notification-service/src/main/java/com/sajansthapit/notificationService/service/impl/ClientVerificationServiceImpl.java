package com.sajansthapit.notificationService.service.impl;

import com.sajansthapit.notificationService.models.ClientVerification;
import com.sajansthapit.notificationService.repository.ClientVerificationRepository;
import com.sajansthapit.notificationService.service.ClientVerificationService;
import com.sajansthapit.notificationService.utils.ClientVerificationStatus;
import com.sajansthapit.notificationService.utils.mq.dto.NotificationDto;
import org.springframework.stereotype.Service;

@Service
public class ClientVerificationServiceImpl implements ClientVerificationService {

    private final ClientVerificationRepository clientVerificationRepository;

    public ClientVerificationServiceImpl(ClientVerificationRepository clientVerificationRepository) {
        this.clientVerificationRepository = clientVerificationRepository;
    }

    @Override
    public ClientVerification save(NotificationDto notificationDto) {
        String otp = otpGenerator();
        ClientVerification clientVerification = ClientVerification.builder()
                .clientId(notificationDto.getClientId())
                .droneId(notificationDto.getDroneId())
                .droneState(notificationDto.getDroneState())
                .status(ClientVerificationStatus.PENDING.getStatus())
                .otpCode(otp)
                .build();
        return clientVerificationRepository.save(clientVerification);
    }


    private String otpGenerator() {
        int randomPin = (int) (Math.random() * 9000) + 1000;
        return String.valueOf(randomPin);
    }
}
