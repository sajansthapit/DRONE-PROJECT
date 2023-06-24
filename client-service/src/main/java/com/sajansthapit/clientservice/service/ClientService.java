package com.sajansthapit.clientservice.service;

import com.sajansthapit.clientservice.dto.ClientDto;
import com.sajansthapit.clientservice.dto.response.SaveClientResponseDto;

public interface ClientService {

    /**
     * Method to save the client Information
     *
     * @param clientDto: Client Dto
     * @return: SaveClientResponseDto
     */
    SaveClientResponseDto saveClient(ClientDto clientDto);
}
