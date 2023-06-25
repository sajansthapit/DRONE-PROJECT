package com.sajansthapit.clientservice.service;

import com.sajansthapit.clientservice.dto.ClientDto;
import com.sajansthapit.clientservice.dto.response.GetClientByIdResponseDto;
import com.sajansthapit.clientservice.dto.response.SaveClientResponseDto;
import com.sajansthapit.clientservice.models.Client;

public interface ClientService {

    /**
     * Method to save the client Information
     *
     * @param clientDto: Client Dto
     * @return: SaveClientResponseDto
     */
    SaveClientResponseDto saveClient(ClientDto clientDto);

    /**
     * Method to find Client by id if it exits
     * @param id Long
     * @return Client
     */
    Client findById(Long id);

    /**
     * Method to get client by id if it exits
     * @param id Long
     * @return GetClientByIdResponseDto
     */
     GetClientByIdResponseDto getClientById(Long id);
}
