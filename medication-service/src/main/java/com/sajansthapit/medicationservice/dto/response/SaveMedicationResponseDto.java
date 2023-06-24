package com.sajansthapit.medicationservice.dto.response;

import com.sajansthapit.medicationservice.dto.BaseResponse;
import org.springframework.http.HttpStatus;

public class SaveMedicationResponseDto extends BaseResponse {
    private Long id;

    public SaveMedicationResponseDto(HttpStatus status, String message, Long id) {
        super(status, message);
        this.id = id;
    }

    public SaveMedicationResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
