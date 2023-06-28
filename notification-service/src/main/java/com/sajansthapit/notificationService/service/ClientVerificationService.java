package com.sajansthapit.notificationService.service;

import com.sajansthapit.notificationService.dto.BaseResponse;
import com.sajansthapit.notificationService.dto.ValidateOtpDto;
import com.sajansthapit.notificationService.models.ClientVerification;
import com.sajansthapit.notificationService.utils.mq.dto.NotificationDto;

public interface ClientVerificationService {

    /**
     * Method to save client verification
     *
     * @param notificationDto NotificationDto
     * @return ClientVerification
     */
    ClientVerification save(NotificationDto notificationDto);

    /**
     * Method to validate the otp
     *
     * @param validateOtpDto ValidateOtp
     * @return BaseResponse
     */
    BaseResponse validateOtp(ValidateOtpDto validateOtpDto);
}
