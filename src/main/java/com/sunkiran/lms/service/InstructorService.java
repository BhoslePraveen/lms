package com.sunkiran.lms.service;

import com.sunkiran.lms.dto.request.CourseDto;
import com.sunkiran.lms.dto.request.InstructorDto;
import com.sunkiran.lms.model.Course;
import com.sunkiran.lms.model.Instructor;

import java.util.List;

public interface InstructorService {

    // Register a new Instructor
    Instructor registerInstructor(InstructorDto instructorDto);

    // Create a course by instructor
    Course createCourse(Long instructorId, CourseDto courseDto);

    // Update Instructor
    Instructor updateInstructor(Long instructorId, InstructorDto instructorDto);

    // Remove a particular Instructor
    boolean removeInstructor(Long instructorId);

    // Fetch a particular Instructor by name
    Instructor getInstructorByName(String name);

    // Fetch a particular Instructor by Id
    Instructor getInstructorById(Long id);

    // Fetch all Instructors
    List<Instructor> getAllInstructors();
}
