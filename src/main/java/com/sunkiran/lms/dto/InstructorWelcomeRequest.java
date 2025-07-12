package com.sunkiran.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for sending welcome emails to instructors
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorWelcomeRequest {
    private String name;
    private String email;
}