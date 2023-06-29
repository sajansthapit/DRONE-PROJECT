package com.sajansthapit.shipmentservice.service.impl;

import com.sajansthapit.shipmentservice.constants.Messages;
import com.sajansthapit.shipmentservice.constants.ShipmentConstants;
import com.sajansthapit.shipmentservice.dto.GetDroneMedications;
import com.sajansthapit.shipmentservice.dto.ShipmentUpdateDto;
import com.sajansthapit.shipmentservice.dto.medication.GetAllMedicationsOfRequestDto;
import com.sajansthapit.shipmentservice.models.ShipmentLog;
import com.sajansthapit.shipmentservice.repository.ShipmentLogRepository;
import com.sajansthapit.shipmentservice.service.ShipmentLogService;
import com.sajansthapit.shipmentservice.util.dto.ShipmentMessageDto;
import com.sajansthapit.shipmentservice.util.enums.DroneState;
import com.sajansthapit.shipmentservice.util.http.HttpClientWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.MessageFormat;

@Service
public class ShipmentLogServiceImpl implements ShipmentLogService {
    private final ShipmentLogRepository shipmentLogRepository;
    private final HttpClientWrapper httpClientWrapper;

    @Value("${url.medication}")
    private String medicationUrl;
    public ShipmentLogServiceImpl(ShipmentLogRepository shipmentLogRepository, HttpClientWrapper httpClientWrapper) {
        this.shipmentLogRepository = shipmentLogRepository;
        this.httpClientWrapper = httpClientWrapper;
    }

    @Override
    public ShipmentLog saveShipmentLog(ShipmentMessageDto shipmentMessageDto) {
        ShipmentLog shipmentLog = ShipmentLog.builder()
                .droneId(shipmentMessageDto.getDroneId())
                .distance(shipmentMessageDto.getDistance())
                .droneState(shipmentMessageDto.getDroneState())
                .battery(shipmentMessageDto.getBattery())
                .clientId(shipmentMessageDto.getClientId())
                .build();
        return shipmentLogRepository.save(shipmentLog);
    }

    @Override
    public ShipmentLog findById(Long id) {
        return shipmentLogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(Messages.SHIPMENT_NOT_FOUND, id)));
    }

    @Override
    public ShipmentLog updateShipmentLog(ShipmentUpdateDto shipmentUpdateDto, Long id) {
        ShipmentLog shipmentLog = findById(id);
        if (shipmentUpdateDto.getBattery() != null) {
            shipmentLog.setBattery(shipmentUpdateDto.getBattery());
        }
        if (shipmentUpdateDto.getDroneState() != null) {
            shipmentLog.setDroneState(shipmentUpdateDto.getDroneState());
        }
        return shipmentLogRepository.save(shipmentLog);
    }

    @Override
    public GetDroneMedications getDroneMedication(Long droneId) {
        //TODO: check if drone exits

        ShipmentLog shipmentLog = shipmentLogRepository.findByDroneIdAndDroneState(droneId, DroneState.DELIVERING.getState())
                .orElseThrow(() -> new EntityNotFoundException(Messages.DRONE_NOT_FOUND));

        GetAllMedicationsOfRequestDto getAllMedicationsOfRequestDto = httpClientWrapper.get(
                medicationUrl.concat(ShipmentConstants.MEDICATION_GET_BY_REQUEST_URL.concat(shipmentLog.getRequestId())),
                null, GetAllMedicationsOfRequestDto.class, MessageFormat.format(Messages.FAILED_TO_CALL_SERVICE, "Medication"), "Medication"
        );
        return new GetDroneMedications(HttpStatus.OK, Messages.MEDICATION_FETCHED, getAllMedicationsOfRequestDto.getMedicationList());
    }
}
