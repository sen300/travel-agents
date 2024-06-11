package com.task.travel_service.dto;

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
}
