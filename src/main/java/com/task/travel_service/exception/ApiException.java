package com.task.travel_service.exception;

import java.time.ZonedDateTime;

public class ApiException {

    private final String message;
    private final ZonedDateTime timestamp;
    private final String status;
    private final int code;

    public ApiException(String message, ZonedDateTime timestamp, String status, int code) {
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }
}
