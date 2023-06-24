package com.sajansthapit.droneservice.service.impl;

import com.sajansthapit.droneservice.constants.Messages;
import com.sajansthapit.droneservice.dto.BaseResponse;
import com.sajansthapit.droneservice.dto.DroneDto;
import com.sajansthapit.droneservice.exceptionhandler.exceptions.UniqueViolationException;
import com.sajansthapit.droneservice.models.Drone;
import com.sajansthapit.droneservice.repository.DroneRepository;
import com.sajansthapit.droneservice.service.DroneService;
import com.sajansthapit.droneservice.util.DroneModel;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    public DroneServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public BaseResponse saveAllDrone(List<DroneDto> droneDtoList) {
        for (DroneDto droneDto : droneDtoList) {
            DroneModel.checkDroneModel(droneDto.getModel());
            Drone drone = Drone.builder()
                    .serialNumber(droneDto.getSerialNumber())
                    .model(droneDto.getModel())
                    .state(droneDto.getState())
                    .build();
            try {
                droneRepository.save(drone);
            } catch (DataIntegrityViolationException exception) {
                if (isSerialNumberUnique(droneDto.getSerialNumber()))
                    throw new UniqueViolationException(Messages.UNIQUE_SERIAL_NUMBER_REQUIRED);
                throw exception;
            }
        }
        return new BaseResponse(HttpStatus.CREATED, Messages.DRONE_SAVED);
    }

    @Override
    public long getDroneCounts() {
        return droneRepository.count();
    }

    private boolean isSerialNumberUnique(String serialNumber) {
        return droneRepository.findBySerialNumber(serialNumber)
                .isPresent();
    }
}