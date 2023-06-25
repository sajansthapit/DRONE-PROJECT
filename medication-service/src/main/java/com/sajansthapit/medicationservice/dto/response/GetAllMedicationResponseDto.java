package com.sajansthapit.medicationservice.dto.response;

import com.sajansthapit.medicationservice.dto.BaseResponse;
import com.sajansthapit.medicationservice.dto.MedicationDto;
import com.sajansthapit.medicationservice.dto.PaginationDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public class GetAllMedicationResponseDto extends BaseResponse {
    private List<MedicationDto> medications;
    private PaginationDto pagination;

    public GetAllMedicationResponseDto(HttpStatus status, String message, List<MedicationDto> medications, PaginationDto pagination) {
        super(status, message);
        this.medications = medications;
        this.pagination = pagination;
    }

    public GetAllMedicationResponseDto(List<MedicationDto> medications, PaginationDto pagination) {
        this.medications = medications;
        this.pagination = pagination;
    }

    public List<MedicationDto> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationDto> medications) {
        this.medications = medications;
    }

    public PaginationDto getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDto pagination) {
        this.pagination = pagination;
    }
}
