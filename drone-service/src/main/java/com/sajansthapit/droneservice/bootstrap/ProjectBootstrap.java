package com.sajansthapit.droneservice.bootstrap;

import com.sajansthapit.droneservice.dto.BaseResponse;
import com.sajansthapit.droneservice.dto.DroneDto;
import com.sajansthapit.droneservice.service.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class ProjectBootstrap implements CommandLineRunner {

    private final DroneService droneService;

    public ProjectBootstrap(DroneService droneService) {
        this.droneService = droneService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadDrones();
    }

    private void loadDrones() {
        if (droneService.getDroneCounts() == 0) {
            String line = "";
            String splitBy = ",";
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        Objects.requireNonNull(this.getClass().getResourceAsStream("/csv/drones.csv"))));
                List<DroneDto> droneDtoList = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    String[] droneList = line.split(splitBy);
                    if (droneList.length == 4) {
                        droneDtoList.add(DroneDto.builder()
                                .serialNumber(droneList[0].trim())
                                .model(droneList[1].trim())
                                .state(droneList[2].trim())
                                .battery(Double.valueOf(droneList[3].trim()))
                                .build());
                    }
                }
                BaseResponse response = droneService.saveAllDrone(droneDtoList);
                log.info(response.getMessage());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
}
