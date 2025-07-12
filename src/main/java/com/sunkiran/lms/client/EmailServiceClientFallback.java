package com.sunkiran.lms.client;

import com.sunkiran.lms.dto.InstructorWelcomeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Fallback implementation for EmailServiceClient when the service is unavailable
 */
@Component
@Slf4j
public class EmailServiceClientFallback implements EmailServiceClient {

    @Override
    public ResponseEntity<String> welcomeInstructor(InstructorWelcomeRequest request) {
        log.error("Fallback triggered for welcomeInstructor. Email service is unavailable. Instructor: {}", 
                 request.getName());
        return ResponseEntity.internalServerError()
                .body("Email service is currently unavailable. Please try again later.");
    }
}