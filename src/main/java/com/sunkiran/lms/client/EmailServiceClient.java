package com.sunkiran.lms.client;

import com.sunkiran.lms.dto.InstructorWelcomeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign client for communication with the email-service
 */
@FeignClient(name = "email-service", fallback = EmailServiceClientFallback.class)
public interface EmailServiceClient {

    /**
     * Sends a welcome email to a newly registered instructor
     * 
     * @param request The instructor welcome request containing name and email
     * @return ResponseEntity with success or error message
     */
    @PostMapping("/api/email/welcome-instructor")
    ResponseEntity<String> welcomeInstructor(@RequestBody InstructorWelcomeRequest request);
}
