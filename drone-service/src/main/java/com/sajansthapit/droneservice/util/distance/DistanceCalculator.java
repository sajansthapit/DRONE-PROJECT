package com.sajansthapit.droneservice.util.distance;

import com.sajansthapit.droneservice.constants.DroneConstants;

public class DistanceCalculator {
    public static double calculate(double latitude, double longitude) {

        double latDistance = Math.toRadians(latitude - DroneConstants.CURRENT_LATITUDE);
        double lonDistance = Math.toRadians(longitude - DroneConstants.CURRENT_LONGITUDE);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(DroneConstants.CURRENT_LATITUDE)) * Math.cos(Math.toRadians(latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = DroneConstants.EARTH_RADIUS * c * 1000; // convert to meters

        return Math.sqrt(distance);
    }
}
