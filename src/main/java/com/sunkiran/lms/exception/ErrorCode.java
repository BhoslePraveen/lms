package com.sunkiran.lms.exception;

public enum ErrorCode {
    RESOURCE_NOT_FOUND("SKL-100", "Resource not found"),
    INSTRUCTOR_NOT_FOUND("SKL-101", "Instructor not found"),
    COURSE_NOT_FOUND("SKL-102", "Course not found"),
    STUDENT_NOT_FOUND("SKL-103", "Student not found"),
    ASSIGNMENT_NOT_FOUND("SKL-104", "Assignment not found"),
    REQUIRED_FIELD_MISSING("SKL-105", "Required field missing"),
    PROCESSING_ERROR("SKL-106", "Processing error");

    private final String code;
    private final String errorMessage;

    ErrorCode(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
