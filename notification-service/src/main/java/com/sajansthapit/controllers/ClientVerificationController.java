package com.sajansthapit.controllers;

import com.sajansthapit.notificationService.dto.BaseResponse;
import com.sajansthapit.notificationService.dto.ValidateOtpDto;
import com.sajansthapit.notificationService.service.ClientVerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class ClientVerificationController {

    private final ClientVerificationService clientVerificationService;

    public ClientVerificationController(ClientVerificationService clientVerificationService) {
        this.clientVerificationService = clientVerificationService;
    }

    @PostMapping("/otp")
    public ResponseEntity<BaseResponse> validateOtp(@RequestBody ValidateOtpDto validateOtpDto) {
        BaseResponse response = clientVerificationService.validateOtp(validateOtpDto);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
