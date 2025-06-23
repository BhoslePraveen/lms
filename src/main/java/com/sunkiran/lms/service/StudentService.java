package com.sunkiran.lms.service;

import com.sunkiran.lms.dto.request.AssignmentSubmissionDto;
import com.sunkiran.lms.dto.request.EnrollRequestDto;
import com.sunkiran.lms.dto.request.StudentDto;
import com.sunkiran.lms.model.Assignment;
import com.sunkiran.lms.model.Course;
import com.sunkiran.lms.model.Module;
import com.sunkiran.lms.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    /**
     * Register a new student
     * @param studentDto the student data
     * @return the created student
     */
    Student registerStudent(StudentDto studentDto);

    /**
     * Get all available courses
     * @return list of all courses
     */
    List<Course> getAllCourses();

    /**
     * Enroll a student in a course
     * @param studentId the ID of the student
     * @param enrollRequestDto the enrollment request data
     * @return the updated student
     */
    Student enrollInCourse(Long studentId, EnrollRequestDto enrollRequestDto);

    /**
     * Get all modules for a course that a student is enrolled in
     * @param studentId the ID of the student
     * @param courseId the ID of the course
     * @return list of modules for the course
     */
    List<Module> getCourseModules(Long studentId, Long courseId);

    /**
     * Submit an assignment
     * @param studentId the ID of the student
     * @param assignmentId the ID of the assignment
     * @param submissionDto the submission data
     * @return the updated assignment
     */
    Assignment submitAssignment(Long studentId, Long assignmentId, AssignmentSubmissionDto submissionDto);

    /**
     * Get all grades for a student
     * @param studentId the ID of the student
     * @return list of assignments with grades
     */
    List<Assignment> getStudentGrades(Long studentId);

    /**
     * Get a student by ID
     * @param studentId the ID of the student
     * @return the student
     */
    Student getStudentById(Long studentId);

    /**
     * Get all students
     * @return list of all students
     */
    List<Student> getAllStudents();

    /**
     * Update a student
     * @param studentId the ID of the student to update
     * @param studentDto the updated student data
     * @return the updated student
     */
    Student updateStudent(Long studentId, StudentDto studentDto);


    /**
     * Delete a student
     * @param studentId the ID of the student to delete
     * @return true if the student was deleted, false otherwise
     */
    boolean deleteStudent(Long studentId);
}
