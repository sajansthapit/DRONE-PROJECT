package com.sajansthapit.medicationservice.dto.response.client;

import com.sajansthapit.medicationservice.dto.BaseResponse;
import org.springframework.http.HttpStatus;

public class GetClientByIdResponseDto extends BaseResponse {
    private ClientDto clientDto;

    public GetClientByIdResponseDto(HttpStatus status, String message, ClientDto clientDto) {
        super(status, message);
        this.clientDto = clientDto;
    }

    public GetClientByIdResponseDto(ClientDto clientDto) {
        this.clientDto = clientDto;
    }

    public ClientDto getClientDto() {
        return clientDto;
    }

    public void setClientDto(ClientDto clientDto) {
        this.clientDto = clientDto;
    }
}
