package com.sunkiran.lms.controller;

import com.sunkiran.lms.dto.request.InstructorDto;
import com.sunkiran.lms.model.Instructor;
import com.sunkiran.lms.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    @PostMapping("/register")
    public ResponseEntity<Instructor> registerInstructor(@RequestBody InstructorDto instructorDto) {
        Instructor instructor = instructorService.registerInstructor(instructorDto);
        return new ResponseEntity<>(instructor, HttpStatus.CREATED);
    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<Instructor> getInstructor(@PathVariable Long instructorId) {
        Instructor instructor = instructorService.getInstructorById(instructorId);
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    @DeleteMapping("/{instructorId}")
    public ResponseEntity<Boolean> removeInstructor(@PathVariable Long instructorId) {
        boolean isRemoved = instructorService.removeInstructor(instructorId);
        return new ResponseEntity<>(isRemoved, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        List<Instructor> instructors = instructorService.getAllInstructors();
        return new ResponseEntity<>(instructors, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Instructor> getInstructorByName(@PathVariable String name) {
        Instructor instructor = instructorService.getInstructorByName(name);
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

}
