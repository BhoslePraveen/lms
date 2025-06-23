package com.sunkiran.lms.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * DTO for student progress analytics
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProgressDto {
    private Long studentId;
    private String studentName;
    private int totalAssignments;
    private int completedAssignments;
    private int pendingAssignments;
    private int overdueAssignments;
    private String completionRate;
    private String averageGrade;
    private List<Map<String, Object>> upcomingDeadlines;
}