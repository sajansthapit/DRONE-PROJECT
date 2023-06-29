package com.sajansthapit.droneservice.dto.response;

import com.sajansthapit.droneservice.dto.BaseResponse;
import com.sajansthapit.droneservice.dto.DroneDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public class GetAvailableDroneResponse extends BaseResponse {

    private List<DroneDto> drones;

    public GetAvailableDroneResponse(HttpStatus status, String message, List<DroneDto> drones) {
        super(status, message);
        this.drones = drones;
    }

    public GetAvailableDroneResponse(List<DroneDto> drones) {
        this.drones = drones;
    }

    public List<DroneDto> getDrones() {
        return drones;
    }

    public void setDrones(List<DroneDto> drones) {
        this.drones = drones;
    }
}
