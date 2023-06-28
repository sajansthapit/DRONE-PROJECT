package com.sajansthapit.notificationService.service.impl;

import com.sajansthapit.notificationService.models.ClientVerification;
import com.sajansthapit.notificationService.repository.ClientVerificationRepository;
import com.sajansthapit.notificationService.service.ClientVerificationService;
import com.sajansthapit.notificationService.utils.ClientVerificationStatus;
import com.sajansthapit.notificationService.utils.SendMailService;
import com.sajansthapit.notificationService.utils.mq.dto.NotificationDto;
import org.springframework.stereotype.Service;

@Service
public class ClientVerificationServiceImpl implements ClientVerificationService {

    private final ClientVerificationRepository clientVerificationRepository;
    private final SendMailService sendMailService;

    public ClientVerificationServiceImpl(ClientVerificationRepository clientVerificationRepository, SendMailService sendMailService) {
        this.clientVerificationRepository = clientVerificationRepository;
        this.sendMailService = sendMailService;
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
        ClientVerification savedClientVerification = clientVerificationRepository.save(clientVerification);
        sendMailService.sendEmailOtp(savedClientVerification);
        return savedClientVerification;
    }


    private String otpGenerator() {
        int randomPin = (int) (Math.random() * 9000) + 1000;
        return String.valueOf(randomPin);
    }
}
