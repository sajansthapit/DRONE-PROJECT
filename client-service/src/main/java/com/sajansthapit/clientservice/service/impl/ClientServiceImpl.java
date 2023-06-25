package com.sajansthapit.clientservice.service.impl;

import com.sajansthapit.clientservice.constants.Messages;
import com.sajansthapit.clientservice.dto.BaseResponse;
import com.sajansthapit.clientservice.dto.ClientDto;
import com.sajansthapit.clientservice.dto.response.GetClientByIdResponseDto;
import com.sajansthapit.clientservice.dto.response.SaveClientResponseDto;
import com.sajansthapit.clientservice.exceptionhandler.exceptions.UniqueViolationException;
import com.sajansthapit.clientservice.models.Client;
import com.sajansthapit.clientservice.repository.ClientRepository;
import com.sajansthapit.clientservice.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.MessageFormat;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public SaveClientResponseDto saveClient(ClientDto clientDto) {
        Client client = Client.builder()
                .name(clientDto.getName())
                .email(clientDto.getEmail())
                .latitude(clientDto.getLatitude())
                .longitude(clientDto.getLongitude())
                .build();
        try {
            Client savedClient = clientRepository.save(client);
            return new SaveClientResponseDto(HttpStatus.CREATED, Messages.CLIENT_SAVED, savedClient.getId());
        } catch (DataIntegrityViolationException exception) {
            if (isEmailUnique(clientDto.getEmail())) {
                throw new UniqueViolationException(Messages.UNIQUE_EMAIL_VIOLATION);
            }
            throw exception;
        }
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(Messages.CLIENT_NOT_FOUND, id)));
    }

    @Override
    public GetClientByIdResponseDto getClientById(Long id) {
        Client client = findById(id);
        ClientDto clientDto = ClientDto.builder()
                .name(client.getName())
                .email(client.getEmail())
                .latitude(client.getLatitude())
                .longitude(client.getLongitude())
                .build();
        return new GetClientByIdResponseDto(HttpStatus.OK, MessageFormat.format(Messages.CLIENT_EXISTS, id), clientDto);
    }

    private boolean isEmailUnique(String email) {
        return clientRepository.findByEmail(email)
                .isPresent();
    }
}
