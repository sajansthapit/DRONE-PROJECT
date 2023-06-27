package com.sajansthapit.droneservice.conroller;

import com.sajansthapit.droneservice.constants.Messages;
import com.sajansthapit.droneservice.dto.BaseResponse;
import com.sajansthapit.droneservice.dto.CheckDroneStateDto;
import com.sajansthapit.droneservice.dto.DroneUpdateDto;
import com.sajansthapit.droneservice.exceptionhandler.exceptions.DroneUpdateFailedException;
import com.sajansthapit.droneservice.models.Drone;
import com.sajansthapit.droneservice.service.DroneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drone")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable(value = "id") Long id, @RequestBody DroneUpdateDto droneUpdateDto) {
        Drone drone = droneService.updateDrone(droneUpdateDto, id);
        if (drone != null) {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK, Messages.DRONE_UPDATED));
        } else throw new DroneUpdateFailedException(Messages.DRONE_UPDATE_FAILED);
    }

    @GetMapping("/check-state/{id}")
    public ResponseEntity<CheckDroneStateDto> checkDroneState(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(droneService.checkDroneState(id));
    }


}
