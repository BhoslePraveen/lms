package com.sunkiran.lms.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private String path;
    private String errorCode;
    private String message;

    public ErrorResponse(HttpStatus status, String path, ErrorCode errorCode, String message) {
        this.timestamp = LocalDateTime.now().toString();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.path = path;
        this.errorCode = errorCode.getCode();
        this.message = message;
    }
}
