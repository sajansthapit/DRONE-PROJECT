package com.sajansthapit.droneservice.util.enumns;

import com.sajansthapit.droneservice.constants.Messages;
import com.sajansthapit.droneservice.exceptionhandler.exceptions.InvalidDroneModelException;

import java.util.Arrays;
import java.util.Objects;

public enum DroneModel {
    LIGHT_WEIGHT("Lightweight", 125),
    MIDDLE_WEIGHT("Middleweight", 250),
    CRUISER_WEIGHT("Cruiserweight", 375),
    HEAVY_WEIGHT("Heavyweight", 500);

    private final String model;

    private final Integer maxWeight;

    DroneModel(String model, Integer maxWeight) {
        this.model = model;
        this.maxWeight = maxWeight;
    }

    public String getModel() {
        return model;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public static void checkDroneModel(String value) {
        Arrays.stream(values())
                .filter(model -> Objects.equals(model.model, value))
                .findFirst().orElseThrow(() -> new InvalidDroneModelException(Messages.INVALID_DRONE_MODEL));
    }
}
