package com.sunkiran.lms.service;

import com.sunkiran.lms.client.EmailServiceClient;
import com.sunkiran.lms.dto.InstructorWelcomeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service for sending emails to instructors using the email-service
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class InstructorEmailService {

    private final EmailServiceClient emailServiceClient;

    /**
     * Sends a welcome email to a newly registered instructor
     *
     * @param name  The instructor's name
     * @param email The instructor's email address
     * @return true if the email was sent successfully, false otherwise
     */
    public boolean sendWelcomeEmail(String name, String email) {
        try {
            log.info("Sending welcome email to instructor: {}", name);
            
            InstructorWelcomeRequest request = new InstructorWelcomeRequest(name, email);
            ResponseEntity<String> response = emailServiceClient.welcomeInstructor(request);
            
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Welcome email sent successfully to instructor: {}", name);
                return true;
            } else {
                log.error("Failed to send welcome email to instructor: {}. Status code: {}", 
                          name, response.getStatusCode());
                return false;
            }
        } catch (Exception e) {
            log.error("Error sending welcome email to instructor: {}", name, e);
            return false;
        }
    }
}