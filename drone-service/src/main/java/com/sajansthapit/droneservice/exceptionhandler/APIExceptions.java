package com.sajansthapit.droneservice.exceptionhandler;

import com.sajansthapit.droneservice.dto.BaseResponse;
import com.sajansthapit.droneservice.exceptionhandler.exceptions.InvalidDroneModelException;
import com.sajansthapit.droneservice.exceptionhandler.exceptions.UniqueViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler(value = UniqueViolationException.class)
    public ResponseEntity<BaseResponse> handleUniqueViolationException(UniqueViolationException exception) {
        BaseResponse response = new BaseResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(value = InvalidDroneModelException.class)
    public ResponseEntity<BaseResponse> handleInvalidDroneModelException(InvalidDroneModelException exception) {
        BaseResponse response = new BaseResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }

}
