package com.sajansthapit.medicationservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDto {
    private Integer currentPage;
    private Long totalItems;
    private Integer totalPages;
}
