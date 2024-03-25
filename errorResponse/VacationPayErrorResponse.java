package com.kuznetsov.vacPayCalculator.errorResponse;

public class VacationPayErrorResponse {
    private String message;
    private Long timestamp;

    public VacationPayErrorResponse(String message, Long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
