package com.sajansthapit.clientservice.dto.response;

import com.sajansthapit.clientservice.dto.BaseResponse;
import org.springframework.http.HttpStatus;

public class SaveClientResponseDto extends BaseResponse {
    private Long id;

    public SaveClientResponseDto(HttpStatus status, String message, Long id) {
        super(status, message);
        this.id = id;
    }

    public SaveClientResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
