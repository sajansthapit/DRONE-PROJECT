package com.sajansthapit.shipmentservice.util;


import com.google.gson.Gson;
import com.sajansthapit.shipmentservice.constants.Messages;
import com.sajansthapit.shipmentservice.constants.ShipmentConstants;
import com.sajansthapit.shipmentservice.dto.BaseResponse;
import com.sajansthapit.shipmentservice.dto.ShipmentUpdateDto;
import com.sajansthapit.shipmentservice.models.ShipmentLog;
import com.sajansthapit.shipmentservice.service.ShipmentLogService;
import com.sajansthapit.shipmentservice.util.dto.DroneUpdateDto;
import com.sajansthapit.shipmentservice.util.dto.ShipmentMessageDto;
import com.sajansthapit.shipmentservice.util.enums.DroneState;
import com.sajansthapit.shipmentservice.util.http.HttpClientWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Slf4j
@Service
public class ShipmentMessageListener {

    private final ShipmentLogService shipmentLogService;
    private final HttpClientWrapper httpClientWrapper;

    @Value("${url.drone}")
    private String droneUrl;


    public ShipmentMessageListener(ShipmentLogService shipmentLogService, HttpClientWrapper httpClientWrapper) {
        this.shipmentLogService = shipmentLogService;
        this.httpClientWrapper = httpClientWrapper;
    }

    @RabbitListener(queues = "shipment-queue")
    public void getShipmentMessage(ShipmentMessageDto shipmentMessageDto) {
        //save log to db
        ShipmentLog shipmentLog = shipmentLogService.saveShipmentLog(shipmentMessageDto);
        log.info("From shipment queue: {}", shipmentMessageDto);
        handleLoadedDrone(shipmentLog);
    }

    private void handleLoadedDrone(ShipmentLog shipmentLog) {
        if (shipmentLog.getDroneState().equals(DroneState.LOADED.getState())) {
            log.info("Handling Log: {}", shipmentLog);

            //change to Delivering
            ShipmentLog updatedLog = shipmentLogService.updateShipmentLog(new ShipmentUpdateDto(DroneState.DELIVERING.getState(), shipmentLog.getBattery()), shipmentLog.getId());

            DroneUpdateDto droneUpdateDto = DroneUpdateDto
                    .builder()
                    .state(DroneState.DELIVERING.getState())
                    .battery(updatedLog.getBattery())
                    .build();
            httpClientWrapper.put(droneUrl.concat(ShipmentConstants.DRONE_UPDATE_URL).concat(updatedLog.getDroneId().toString())
                    , new Gson().toJson(droneUpdateDto), null, BaseResponse.class, MessageFormat.format(Messages.FAILED_TO_CALL_SERVICE, "Drone"));
            handleDeliveredDrone(updatedLog);
        }
    }

    private void handleDeliveredDrone(ShipmentLog shipmentLog) {
        if (shipmentLog.getDroneState().equals(DroneState.DELIVERING.getState())) {
            double timeWait = shipmentLog.getDistance() / 100;
            Thread deliveredThread = new Thread(() -> {
                try {
                    Thread.sleep((long) (timeWait * 1000));

                    ShipmentLog updatedLog = shipmentLogService.updateShipmentLog(new ShipmentUpdateDto(DroneState.DELIVERED.getState(), shipmentLog.getBattery() - timeWait), shipmentLog.getId());
                    DroneUpdateDto droneUpdateDto = DroneUpdateDto
                            .builder()
                            .state(DroneState.DELIVERED.getState())
                            .battery(shipmentLog.getBattery() - timeWait)
                            .build();
                    httpClientWrapper.put(droneUrl.concat(ShipmentConstants.DRONE_UPDATE_URL).concat(updatedLog.getDroneId().toString())
                            , new Gson().toJson(droneUpdateDto), null, BaseResponse.class, MessageFormat.format(Messages.FAILED_TO_CALL_SERVICE, "Drone"));
                    handleReturningDrone(updatedLog);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            deliveredThread.start();
        }
    }

    private void handleReturningDrone(ShipmentLog shipmentLog) {
        if (shipmentLog.getDroneState().equals(DroneState.DELIVERED.getState())) {
            double timeWait = shipmentLog.getDistance() / 100;
//            try {
//                Thread.sleep((long) (timeWait * 1000));

            ShipmentLog updatedLog = shipmentLogService.updateShipmentLog(new ShipmentUpdateDto(DroneState.RETURNING.getState(), shipmentLog.getBattery()), shipmentLog.getId());
            DroneUpdateDto droneUpdateDto = DroneUpdateDto
                    .builder()
                    .state(DroneState.RETURNING.getState())
//                        .battery(updatedLog.getBattery() - timeWait)
                    .build();
            httpClientWrapper.put(droneUrl.concat(ShipmentConstants.DRONE_UPDATE_URL).concat(updatedLog.getDroneId().toString())
                    , new Gson().toJson(droneUpdateDto), null, BaseResponse.class, MessageFormat.format(Messages.FAILED_TO_CALL_SERVICE, "Drone"));
            handleIdleDrone(updatedLog);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }


    private void handleIdleDrone(ShipmentLog shipmentLog) {
        if (shipmentLog.getDroneState().equals(DroneState.RETURNING.getState())) {
            double timeWait = shipmentLog.getDistance() / 100;
            Thread returningThread = new Thread(() -> {
                try {
                    Thread.sleep((long) (timeWait * 1000));

                    ShipmentLog updatedLog = shipmentLogService.updateShipmentLog(new ShipmentUpdateDto(DroneState.IDLE.getState(), shipmentLog.getBattery() - timeWait), shipmentLog.getId());
                    DroneUpdateDto droneUpdateDto = DroneUpdateDto
                            .builder()
                            .state(DroneState.IDLE.getState())
                            .battery(updatedLog.getBattery() - timeWait)
                            .build();
                    httpClientWrapper.put(droneUrl.concat(ShipmentConstants.DRONE_UPDATE_URL).concat(updatedLog.getDroneId().toString())
                            , new Gson().toJson(droneUpdateDto), null, BaseResponse.class, MessageFormat.format(Messages.FAILED_TO_CALL_SERVICE, "Drone"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            returningThread.start();
        }
    }





}
