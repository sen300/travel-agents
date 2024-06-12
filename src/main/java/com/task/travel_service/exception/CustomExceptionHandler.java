package com.task.travel_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.NotAcceptableStatusException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {

    private final String failed = "Failed";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleBadRequestException(MethodArgumentNotValidException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Asia/Jakarta")),
                failed,
                400
        );

        return new ResponseEntity<>(apiException, status);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(DataNotFoundException e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Asia/Jakarta")),
                failed,
                500
        );

        return new ResponseEntity<>(apiException, status);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(value = {NotAcceptableStatusException.class})
    public ResponseEntity<Object> handleNotAcceptableException(NotAcceptableStatusException e) {
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        ApiException apiException = new ApiException(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Asia/Jakarta")),
                failed,
                406
        );

        return new ResponseEntity<>(apiException, status);
    }
}
