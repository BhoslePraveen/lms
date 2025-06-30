package com.sunkiran.lms.controller;

import com.sunkiran.lms.dto.request.InstructorDto;
import com.sunkiran.lms.exception.ErrorResponse;
import com.sunkiran.lms.model.Instructor;
import com.sunkiran.lms.service.InstructorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
@Slf4j
public class InstructorController {
    private final InstructorService instructorService;

    @PostMapping("/register")
    @Operation(summary = "Register a new Instructor"
    , description = "This method is used to register a new Instructor to Lms"
//    , tags = {"Instructor"}
    ,responses = {
            @ApiResponse(responseCode = "201", description = "Instructor registered successfully",content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Instructor.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request - Proper details Not given",content = {@Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "409", description = "Conflict - Instructor already exists"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Something went wrong- Pls contact support")
    })
    public ResponseEntity<Instructor> registerInstructor(@RequestBody InstructorDto instructorDto) {
        Instructor instructor = instructorService.registerInstructor(instructorDto);
        return new ResponseEntity<>(instructor, HttpStatus.CREATED);
    }

    @PutMapping("/{instructorId}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long instructorId, @RequestBody InstructorDto instructorDto) {
        Instructor instructor = instructorService.updateInstructor(instructorId, instructorDto);
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<Instructor> getInstructor(@PathVariable Long instructorId) {
        log.info("START: InstructorController --> getInstructor");
        Instructor instructor = instructorService.getInstructorById(instructorId);
        log.info("END: InstructorController --> getInstructor");
        return new ResponseEntity<>(instructor, HttpStatus.OK);
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

    @DeleteMapping("/{instructorId}")
    public ResponseEntity<Boolean> removeInstructor(@PathVariable Long instructorId) {
        boolean isRemoved = instructorService.removeInstructor(instructorId);
        return new ResponseEntity<>(isRemoved, HttpStatus.OK);
    }

}
