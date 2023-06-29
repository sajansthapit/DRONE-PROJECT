package com.sajansthapit.shipmentservice.controllers;

import com.sajansthapit.shipmentservice.dto.GetDroneMedications;
import com.sajansthapit.shipmentservice.service.ShipmentLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {

    private final ShipmentLogService shipmentLogService;

    public ShipmentController(ShipmentLogService shipmentLogService) {
        this.shipmentLogService = shipmentLogService;
    }

    @GetMapping("/medication/{droneId}")
    public ResponseEntity<GetDroneMedications> getDroneMedication(@PathVariable(value = "droneId") Long droneId) {
        GetDroneMedications response = shipmentLogService.getDroneMedication(droneId);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
