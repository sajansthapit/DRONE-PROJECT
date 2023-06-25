package com.sajansthapit.medicationservice.exceptionhandler;

import com.sajansthapit.medicationservice.constants.Messages;
import com.sajansthapit.medicationservice.dto.BaseResponse;
import com.sajansthapit.medicationservice.exceptionhandler.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

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

    @ExceptionHandler(value = UniqueViolationException.class)
    public ResponseEntity<BaseResponse> handleUniqueViolationException(UniqueViolationException exception) {
        BaseResponse response = new BaseResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(value = UnsupportedContentTypeException.class)
    public ResponseEntity<BaseResponse> handleUnsupportedContentTypeException(UnsupportedContentTypeException exception) {
        BaseResponse response = new BaseResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
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

    @ExceptionHandler(value = MultipartException.class)
    public ResponseEntity<BaseResponse> handleMultipartException(MultipartException exception) {
        BaseResponse response = new BaseResponse(HttpStatus.PAYLOAD_TOO_LARGE, Messages.EXCEEDS_SIZE_LIMIT);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(value = ImageNotFoundException.class)
    public ResponseEntity<BaseResponse> handleImageNotFoundException(ImageNotFoundException exception) {
        BaseResponse response = new BaseResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(value = WeightOverLimitException.class)
    public ResponseEntity<BaseResponse> handleWeightOverLimitException(WeightOverLimitException exception) {
        BaseResponse response = new BaseResponse(HttpStatus.FORBIDDEN, exception.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(value = InsufficientMedicationQuantityException.class)
    public ResponseEntity<BaseResponse> handleInsufficientMedicationQuantityException(InsufficientMedicationQuantityException exception) {
        BaseResponse response = new BaseResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(value = EmptyMedicationException.class)
    public ResponseEntity<BaseResponse> handleEmptyMedicationException(EmptyMedicationException exception) {
        BaseResponse response = new BaseResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(value = HttpFailedException.class)
    public ResponseEntity<BaseResponse> handleHttpFailedException(HttpFailedException exception) {
        BaseResponse response = new BaseResponse(HttpStatus.SERVICE_UNAVAILABLE, exception.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }
}
