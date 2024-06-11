package com.task.travel_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSuccess {
    private String status;
    private String message;

    private int code;

    public ResponseSuccess(String status, String message, int code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
