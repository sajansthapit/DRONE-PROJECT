package com.sajansthapit.droneservice.constants;

public interface Messages {

    String MODEL_REQUIRED = "Drone model is required";
    String SERIAL_REQUIRED = "Serial Number is required";
    String STATE_REQUIRED = "Drone state is required";
    String UNIQUE_SERIAL_NUMBER_REQUIRED = "Unique Serial Number is required";
    String INVALID_DRONE_MODEL = "Given Drone model not found";
    String DRONE_SAVED = "Drone list saved successfully";
    String INVALID_MIN_WEIGHT = "Total weight must be greater than 0";
    String INVALID_MAX_WEIGHT = "Total weight must be smaller than 500";
    String DRONE_NOT_FOUND = "Drone with id {0} not found";
    String DRONE_UPDATED = "Drone updated successfully";
    String DRONE_UPDATE_FAILED = "Failed to update drone";
    String NO_AVAILABLE_DRONES = "No drones available";
    String DRONES_FETCHED = "Drones fetched successfully";
    String GET_DRONE_BATTERY_RESPONSE = "Drone battery level fetched successfully";

}
