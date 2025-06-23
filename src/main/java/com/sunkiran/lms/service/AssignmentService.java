package com.sunkiran.lms.service;

import com.sunkiran.lms.dto.request.GradeDto;
import com.sunkiran.lms.model.Assignment;

import java.util.List;

public interface AssignmentService {
    /**
     * Grade a submitted assignment
     * @param assignmentId the ID of the assignment
     * @param gradeDto the grade data
     * @return the updated assignment
     */
    Assignment gradeAssignment(Long assignmentId, GradeDto gradeDto);

    /**
     * View submissions for an assignment
     * @param assignmentId the ID of the assignment
     * @return list of assignments submitted for this assignment
     */
    List<Assignment> getAssignmentSubmissions(Long assignmentId);
}
