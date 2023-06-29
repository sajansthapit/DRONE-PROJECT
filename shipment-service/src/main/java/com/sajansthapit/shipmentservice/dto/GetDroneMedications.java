package com.sajansthapit.shipmentservice.dto;


import com.sajansthapit.shipmentservice.dto.medication.MedicationDto;
import org.springframework.http.HttpStatus;

import java.util.List;


public class GetDroneMedications extends BaseResponse{
    private List<MedicationDto> medicationList;

    public GetDroneMedications(HttpStatus status, String message, List<MedicationDto> medicationList) {
        super(status, message);
        this.medicationList = medicationList;
    }

    public GetDroneMedications(List<MedicationDto> medicationList) {
        this.medicationList = medicationList;
    }

    public List<MedicationDto> getMedicationList() {
        return medicationList;
    }

    public void setMedicationList(List<MedicationDto> medicationList) {
        this.medicationList = medicationList;
    }
}
