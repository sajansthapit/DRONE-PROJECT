package com.sajansthapit.shipmentservice.exceptionhandlers;

import com.sajansthapit.shipmentservice.dto.BaseResponse;
import com.sajansthapit.shipmentservice.exceptionhandlers.exceptions.HttpFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class APIExceptions {
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<BaseResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        String message = null;
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            message = violation.getMessage();
        }
        BaseResponse response = new BaseResponse(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(response, response.getStatus());
    }


    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<BaseResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        BaseResponse response = new BaseResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<BaseResponse> handleEntityNotFoundException(EntityNotFoundException exception) {
        BaseResponse response = new BaseResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(value = HttpFailedException.class)
    public ResponseEntity<BaseResponse> handleHttpFailedException(HttpFailedException exception) {
        BaseResponse response = new BaseResponse(HttpStatus.SERVICE_UNAVAILABLE, exception.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }
}
