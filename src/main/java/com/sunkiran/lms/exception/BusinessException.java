package com.sunkiran.lms.exception;

public class BusinessException extends RuntimeException{
    private ErrorCode errorCode;
    private String errorMessage;

    public ErrorCode getErrorCode() {
        return errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public BusinessException(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BusinessException(String errorMessage) {
       this(ErrorCode.PROCESSING_ERROR,errorMessage);
    }

    public BusinessException(ErrorCode errorCode) {
        this(errorCode,errorCode.getErrorMessage());
    }
}
