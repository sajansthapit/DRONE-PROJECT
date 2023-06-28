package com.sajansthapit.droneservice.service.impl;

import com.sajansthapit.droneservice.constants.DroneConstants;
import com.sajansthapit.droneservice.constants.Messages;
import com.sajansthapit.droneservice.dto.BaseResponse;
import com.sajansthapit.droneservice.dto.CheckDroneStateDto;
import com.sajansthapit.droneservice.dto.DroneDto;
import com.sajansthapit.droneservice.dto.DroneUpdateDto;
import com.sajansthapit.droneservice.exceptionhandler.exceptions.UniqueViolationException;
import com.sajansthapit.droneservice.models.Drone;
import com.sajansthapit.droneservice.models.DroneRequest;
import com.sajansthapit.droneservice.repository.DroneRepository;
import com.sajansthapit.droneservice.service.DroneService;
import com.sajansthapit.droneservice.util.enumns.DroneModel;
import com.sajansthapit.droneservice.util.enumns.DroneState;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

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
                    .battery(droneDto.getBattery())
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

    @Override
    public Optional<Drone> checkRequestAndAssignDrone(DroneRequest droneRequest) {

        Double requiredBatteryPercentage = requiredBatteryPercentage(droneRequest.getDistance());

        List<Drone> droneList = droneRepository.findAllByStateAndBatteryGreaterThan(DroneState.IDLE.getState(), requiredBatteryPercentage);
        if (droneList.isEmpty()) return Optional.empty();


        if (droneRequest.getTotalWeight() <= DroneModel.LIGHT_WEIGHT.getMaxWeight()) {
            for (Drone drone : droneList) {
                if (drone.getModel().equals(DroneModel.LIGHT_WEIGHT.getModel())) {
                    return Optional.of(drone);
                }
            }
        }
        if (droneRequest.getTotalWeight() <= DroneModel.MIDDLE_WEIGHT.getMaxWeight()) {
            for (Drone drone : droneList) {
                if (drone.getModel().equals(DroneModel.MIDDLE_WEIGHT.getModel())) {
                    return Optional.of(drone);
                }
            }
        }
        if (droneRequest.getTotalWeight() <= DroneModel.CRUISER_WEIGHT.getMaxWeight()) {
            for (Drone drone : droneList) {
                if (drone.getModel().equals(DroneModel.CRUISER_WEIGHT.getModel())) {
                    return Optional.of(drone);
                }
            }
        }
        if (droneRequest.getTotalWeight() <= DroneModel.HEAVY_WEIGHT.getMaxWeight()) {
            for (Drone drone : droneList) {
                if (drone.getModel().equals(DroneModel.HEAVY_WEIGHT.getModel())) {
                    return Optional.of(drone);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Drone findById(Long id) {
        return droneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(Messages.DRONE_NOT_FOUND, id)));
    }

    @Override
    public Drone updateDrone(DroneUpdateDto droneUpdateDto, Long id) {
        Drone drone = findById(id);
        if (droneUpdateDto.getBattery() != null) {
            drone.setBattery(droneUpdateDto.getBattery());
        }

        //updating the drone checking if the drone is returning or idle
        if (droneUpdateDto.getState() != null) {
            if(droneUpdateDto.getState().equals(DroneState.IDLE.getState())){
                if(drone.getState().equals(DroneState.RETURNING.getState())){
                    drone.setState(DroneState.IDLE.getState());
                }else{
                    if(drone.getState().equals(DroneState.IDLE.getState()))
                        drone.setState(DroneState.IDLE.getState());
                }
            }else
                drone.setState(droneUpdateDto.getState());
        }

        return droneRepository.save(drone);
    }

    @Override
    public CheckDroneStateDto checkDroneState(Long droneId) {
        Drone drone = findById(droneId);
        return new CheckDroneStateDto(drone.getState());
    }

    private boolean isSerialNumberUnique(String serialNumber) {
        return droneRepository.findBySerialNumber(serialNumber)
                .isPresent();
    }

    private Double requiredBatteryPercentage(Double distance) {
        double battery = (distance / 100) * 2;

        if (battery < DroneConstants.MAX_REQUIRED_BATTERY)
            return DroneConstants.MAX_REQUIRED_BATTERY;
        else
            return battery;
    }
}
