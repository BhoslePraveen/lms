package com.sunkiran.lms.service;

import com.sunkiran.lms.dto.request.AssignmentDto;
import com.sunkiran.lms.dto.request.CourseDto;
import com.sunkiran.lms.dto.request.ModuleDto;
import com.sunkiran.lms.model.Assignment;
import com.sunkiran.lms.model.Course;
import com.sunkiran.lms.model.Module;

import java.util.List;
import java.util.Map;

public interface CourseService {
    /**
     * Add a module to a course
     * @param courseId the ID of the course
     * @param moduleDto the module data
     * @return the created module
     */
    com.sunkiran.lms.model.Module addModuleToCourse(Long courseId, ModuleDto moduleDto);

    /**
     * Create an assignment for a course
     * @param courseId the ID of the course
     * @param assignmentDto the assignment data
     * @return the created assignment
     */
    Assignment createAssignment(Long courseId, AssignmentDto assignmentDto);

    /**
     * Get all courses
     * @return list of all courses
     */
    List<Course> getAllCourses();

    /**
     * Get a course by ID
     * @param courseId the ID of the course
     * @return the course
     */
    Course getCourseById(Long courseId);

    /**
     * Update a course
     * @param courseId the ID of the course to update
     * @param courseDto the updated course data
     * @return the updated course
     */
    Course updateCourse(Long courseId, CourseDto courseDto);


    /**
     * Delete a course
     * @param courseId the ID of the course to delete
     * @return true if the course was deleted, false otherwise
     */
    boolean deleteCourse(Long courseId);

    /**
     * Get a module by ID
     * @param moduleId the ID of the module
     * @return the module
     */
    Module getModuleById(Long moduleId);

    /**
     * Update a module
     * @param moduleId the ID of the module to update
     * @param moduleDto the updated module data
     * @return the updated module
     */
    Module updateModule(Long moduleId, ModuleDto moduleDto);

    /**
     * Delete a module
     * @param moduleId the ID of the module to delete
     * @return true if the module was deleted, false otherwise
     */
    boolean deleteModule(Long moduleId);

    /**
     * Get an assignment by ID
     * @param assignmentId the ID of the assignment
     * @return the assignment
     */
    Assignment getAssignmentById(Long assignmentId);

    /**
     * Update an assignment
     * @param assignmentId the ID of the assignment to update
     * @param assignmentDto the updated assignment data
     * @return the updated assignment
     */
    Assignment updateAssignment(Long assignmentId, AssignmentDto assignmentDto);

    /**
     * Partially update an assignment
     * @param assignmentId the ID of the assignment to update
     * @param fields the fields to update
     * @return the updated assignment
     */
    Assignment partialUpdateAssignment(Long assignmentId, Map<String, Object> fields);

    /**
     * Delete an assignment
     * @param assignmentId the ID of the assignment to delete
     * @return true if the assignment was deleted, false otherwise
     */
    boolean deleteAssignment(Long assignmentId);
}
