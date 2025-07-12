package com.sunkiran.lms.controller;

import com.sunkiran.lms.dto.InstructorWelcomeRequest;
import com.sunkiran.lms.service.InstructorEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for instructor email operations
 */
@RestController
@RequestMapping("/api/instructors/email")
@RequiredArgsConstructor
@Slf4j
public class InstructorEmailController {

    private final InstructorEmailService instructorEmailService;

    /**
     * Endpoint to send a welcome email to an instructor
     *
     * @param request The instructor welcome request containing name and email
     * @return ResponseEntity with success or error message
     */
    @PostMapping("/welcome")
    public ResponseEntity<String> sendWelcomeEmail(@RequestBody InstructorWelcomeRequest request) {
        log.info("Received request to send welcome email to instructor: {}", request.getName());
        
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Instructor name is required");
        }
        
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Instructor email is required");
        }
        
        boolean emailSent = instructorEmailService.sendWelcomeEmail(request.getName(), request.getEmail());
        
        if (emailSent) {
            return ResponseEntity.ok("Welcome email sent successfully to " + request.getName());
        } else {
            return ResponseEntity.internalServerError().body("Failed to send welcome email");
        }
    }
}