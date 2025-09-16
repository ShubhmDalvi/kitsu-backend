package com.example.urlshortener.exception;

import java.time.OffsetDateTime;
import java.util.List;

public class ApiError {
    private String message;
    private OffsetDateTime timestamp;
    private List<String> details;

    public ApiError() {}

    public ApiError(String message, List<String> details) {
        this.message = message;
        this.details = details;
        this.timestamp = OffsetDateTime.now();
    }

    // getters and setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public OffsetDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(OffsetDateTime timestamp) { this.timestamp = timestamp; }
    public List<String> getDetails() { return details; }
    public void setDetails(List<String> details) { this.details = details; }
}
