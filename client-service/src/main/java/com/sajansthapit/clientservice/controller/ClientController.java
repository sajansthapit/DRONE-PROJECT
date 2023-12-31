package com.sajansthapit.clientservice.controller;

import com.sajansthapit.clientservice.dto.ClientDto;
import com.sajansthapit.clientservice.dto.response.GetClientByIdResponseDto;
import com.sajansthapit.clientservice.dto.response.SaveClientResponseDto;
import com.sajansthapit.clientservice.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<SaveClientResponseDto> save(@RequestBody @Valid ClientDto clientDto) {
        SaveClientResponseDto response = clientService.saveClient(clientDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<GetClientByIdResponseDto> checkIfClientExists(@PathVariable(value = "id") Long id) {
        GetClientByIdResponseDto response = clientService.getClientById(id);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
