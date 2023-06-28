package com.sajansthapit.notificationService.service.impl;

import com.sajansthapit.notificationService.constants.Messages;
import com.sajansthapit.notificationService.dto.BaseResponse;
import com.sajansthapit.notificationService.dto.ValidateOtpDto;
import com.sajansthapit.notificationService.exceptionhandler.exceptions.InvalidOtpException;
import com.sajansthapit.notificationService.models.ClientVerification;
import com.sajansthapit.notificationService.repository.ClientVerificationRepository;
import com.sajansthapit.notificationService.service.ClientVerificationService;
import com.sajansthapit.notificationService.utils.ClientVerificationStatus;
import com.sajansthapit.notificationService.utils.SendMailService;
import com.sajansthapit.notificationService.utils.mq.dto.NotificationDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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

    @Override
    public BaseResponse validateOtp(ValidateOtpDto validateOtpDto) {
        ClientVerification clientVerification = clientVerificationRepository.findByClientIdAndDroneIdAndStatus(validateOtpDto.getClientId(), validateOtpDto.getDroneId(), ClientVerificationStatus.PENDING.getStatus())
                .orElseThrow(() -> new EntityNotFoundException(Messages.VERIFICATION_NOT_FOUND));
        if (clientVerification.getOtpCode().equals(validateOtpDto.getOtp()))
            return new BaseResponse(HttpStatus.OK, Messages.OTP_VALIDATED);
        throw new InvalidOtpException(Messages.INVALID_OTP);
    }

    private String otpGenerator() {
        int randomPin = (int) (Math.random() * 9000) + 1000;
        return String.valueOf(randomPin);
    }
}
