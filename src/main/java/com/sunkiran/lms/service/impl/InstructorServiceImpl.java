package com.sunkiran.lms.service.impl;

import com.sunkiran.lms.dto.request.CourseDto;
import com.sunkiran.lms.dto.request.InstructorDto;
import com.sunkiran.lms.model.Course;
import com.sunkiran.lms.model.Instructor;
import com.sunkiran.lms.repo.CourseRepository;
import com.sunkiran.lms.repo.InstructorRepository;
import com.sunkiran.lms.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    @Override
    public Instructor registerInstructor(InstructorDto instructorDto) {
        Instructor instructor = new Instructor();
        instructor.setName(instructorDto.getName());
        instructor.setSpecialization(instructorDto.getSpecialization());
        return instructorRepository.save(instructor);
    }

    @Override
    public Course createCourse(Long instructorId, CourseDto courseDto) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new RuntimeException("Instructor with given Id is not found"));
        Course course = new Course();
        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setDuration(courseDto.getDuration());
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    @Override
    public Instructor updateInstructor(Long instructorId, InstructorDto instructorDto) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new RuntimeException("Instructor with given Id is not found"));
        instructor.setName(instructorDto.getName());
        instructor.setSpecialization(instructorDto.getSpecialization());
        return instructorRepository.save(instructor);
    }

    @Override
    public boolean removeInstructor(Long instructorId) {
        if(instructorRepository.existsById(instructorId)){
            instructorRepository.deleteById(instructorId);
            return true;
        }
        return false;
    }

    @Override
    public Instructor getInstructorByName(String name) {
        Instructor instructor = instructorRepository.findByNameNativeQuery(name);
        return instructor;
    }

    @Override
    public Instructor getInstructorById(Long id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new RuntimeException("Instructor with given Id is not found"));
        return instructor;
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }
}
