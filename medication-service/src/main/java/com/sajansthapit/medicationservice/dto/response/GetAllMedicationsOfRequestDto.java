package com.sajansthapit.medicationservice.dto.response;

import com.sajansthapit.medicationservice.dto.BaseResponse;
import com.sajansthapit.medicationservice.dto.MedicationDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public class GetAllMedicationsOfRequestDto extends BaseResponse {

    private List<MedicationDto> medicationList;

    public GetAllMedicationsOfRequestDto(HttpStatus status, String message, List<MedicationDto> medicationList) {
        super(status, message);
        this.medicationList = medicationList;
    }

    public GetAllMedicationsOfRequestDto(List<MedicationDto> medicationList) {
        this.medicationList = medicationList;
    }

    public List<MedicationDto> getMedicationList() {
        return medicationList;
    }

    public void setMedicationList(List<MedicationDto> medicationList) {
        this.medicationList = medicationList;
    }
}
