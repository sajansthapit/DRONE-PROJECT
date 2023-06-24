package com.sajansthapit.medicationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MedicationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicationServiceApplication.class);
    }
}
