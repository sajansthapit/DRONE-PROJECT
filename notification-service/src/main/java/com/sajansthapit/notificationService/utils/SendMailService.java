package com.sajansthapit.notificationService.utils;

import com.sajansthapit.notificationService.constants.Messages;
import com.sajansthapit.notificationService.constants.NotificationConstants;
import com.sajansthapit.notificationService.dto.ClientDto;
import com.sajansthapit.notificationService.dto.GetClientByIdResponseDto;
import com.sajansthapit.notificationService.models.ClientVerification;
import com.sajansthapit.notificationService.utils.enums.DroneState;
import com.sajansthapit.notificationService.utils.http.HttpClientWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;


@Service
public class SendMailService {

    private final HttpClientWrapper httpClientWrapper;
    private final JavaMailSender javaMailSender;

    @Value("${url.client}")
    private String clientUrl;

    public SendMailService(HttpClientWrapper httpClientWrapper, JavaMailSender javaMailSender) {
        this.httpClientWrapper = httpClientWrapper;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailOtp(ClientVerification clientVerification) {
        //check status
        if (clientVerification.getDroneState().equals(DroneState.DELIVERED.getState())) {
            //check client if exists
            GetClientByIdResponseDto getClientByIdResponseDto = httpClientWrapper.get(
                    clientUrl.concat(NotificationConstants.CHECK_CLIENT_URL).concat(clientVerification.getClientId().toString()),
                    null,
                    GetClientByIdResponseDto.class,
                    MessageFormat.format(Messages.FAILED_TO_CALL_SERVICE, "Client"),
                    "Client");
            ClientDto clientDto = getClientByIdResponseDto.getClientDto();
            String body = "Use the Opt ".concat(clientVerification.getOtpCode()).concat(" for your verification.");
            sendEmail(clientDto.getEmail(), body, "OTP verification");

        }
    }

    private void sendEmail(String email, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sajansthapit99@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
}
