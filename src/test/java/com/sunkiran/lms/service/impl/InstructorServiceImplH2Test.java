package com.sunkiran.lms.service.impl;

import com.sunkiran.lms.dto.request.CourseDto;
import com.sunkiran.lms.dto.request.InstructorDto;
import com.sunkiran.lms.exception.BusinessException;
import com.sunkiran.lms.model.Course;
import com.sunkiran.lms.model.Instructor;
import com.sunkiran.lms.repo.CourseRepository;
import com.sunkiran.lms.repo.InstructorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for InstructorServiceImpl using H2 in-memory database.
 * 
 * This test class uses a real H2 database instead of mocking the repository layer.
 * Key differences from InstructorServiceImplTest:
 * 1. Uses @DataJpaTest instead of @ExtendWith(MockitoExtension.class)
 * 2. Autowires actual repositories instead of using @Mock
 * 3. Imports the actual service implementation with @Import
 * 4. Tests interact with a real database, verifying actual persistence
 * 5. Each test includes additional assertions to verify database state
 * 
 * The H2 database is configured in test/resources/application.properties
 * and is automatically created and dropped for each test.
 */
@DataJpaTest
@ActiveProfiles("test")
@Import(InstructorServiceImpl.class)
class InstructorServiceImplH2Test {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorServiceImpl instructorService;

    private InstructorDto instructorDto;
    private CourseDto courseDto;

    @BeforeEach
    void setUp() {
        // Setup test data
        instructorDto = new InstructorDto("John Doe", "Computer Science");

        courseDto = new CourseDto();
        courseDto.setTitle("Java Programming");
        courseDto.setDescription("Learn Java Programming");
        courseDto.setDuration(3);

        // Clear repositories to ensure clean state for each test
        // This is important for tests that rely on specific database state
        courseRepository.deleteAll();
        instructorRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        // Clean up after each test to avoid affecting other tests
        // Although @DataJpaTest rolls back transactions by default,
        // this provides an extra layer of isolation
        courseRepository.deleteAll();
        instructorRepository.deleteAll();
    }

    @Test
    void registerInstructor_ShouldReturnSavedInstructor() {
        // When
        Instructor result = instructorService.registerInstructor(instructorDto);

        // Then
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(instructorDto.getName(), result.getName());
        assertEquals(instructorDto.getSpecialization(), result.getSpecialization());

        // Verify it's in the database
        Instructor savedInstructor = instructorRepository.findById(result.getId()).orElse(null);
        assertNotNull(savedInstructor);
        assertEquals(result.getId(), savedInstructor.getId());
    }

    @Test
    void createCourse_ShouldReturnSavedCourse() {
        // Arrange
        Instructor instructor = instructorService.registerInstructor(instructorDto);

        // Act
        Course result = instructorService.createCourse(instructor.getId(), courseDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(courseDto.getTitle(), result.getTitle());
        assertEquals(courseDto.getDescription(), result.getDescription());
        assertEquals(courseDto.getDuration(), result.getDuration());
        assertEquals(instructor.getId(), result.getInstructor().getId());

        // Verify it's in the database
        Course savedCourse = courseRepository.findById(result.getId()).orElse(null);
        assertNotNull(savedCourse);
        assertEquals(result.getId(), savedCourse.getId());
    }

    @Test
    void createCourse_WhenInstructorNotFound_ShouldThrowException() {
        // Act & Assert
        assertThrows(RuntimeException.class, () -> instructorService.createCourse(999L, courseDto));
    }

    @Test
    void updateInstructor_ShouldReturnUpdatedInstructor() {
        // Arrange
        Instructor instructor = instructorService.registerInstructor(instructorDto);
        InstructorDto updatedDto = new InstructorDto("Jane Doe", "Data Science");

        // Act
        Instructor result = instructorService.updateInstructor(instructor.getId(), updatedDto);

        // Assert
        assertNotNull(result);
        assertEquals(instructor.getId(), result.getId());
        assertEquals(updatedDto.getName(), result.getName());
        assertEquals(updatedDto.getSpecialization(), result.getSpecialization());

        // Verify it's updated in the database
        Instructor updatedInstructor = instructorRepository.findById(result.getId()).orElse(null);
        assertNotNull(updatedInstructor);
        assertEquals(updatedDto.getName(), updatedInstructor.getName());
        assertEquals(updatedDto.getSpecialization(), updatedInstructor.getSpecialization());
    }

    @Test
    void updateInstructor_WhenInstructorNotFound_ShouldThrowException() {
        // Act & Assert
        assertThrows(RuntimeException.class, () -> instructorService.updateInstructor(999L, instructorDto));
    }

    @Test
    void removeInstructor_WhenInstructorExists_ShouldReturnTrue() {
        // Arrange
        Instructor instructor = instructorService.registerInstructor(instructorDto);

        // Act
        boolean result = instructorService.removeInstructor(instructor.getId());

        // Assert
        assertTrue(result);

        // Verify it's removed from the database
        assertFalse(instructorRepository.existsById(instructor.getId()));
    }

    @Test
    void removeInstructor_WhenInstructorDoesNotExist_ShouldReturnFalse() {
        // Act
        boolean result = instructorService.removeInstructor(999L);

        // Assert
        assertFalse(result);
    }

    @Test
    void getInstructorByName_ShouldReturnInstructor() {
        // Arrange
        Instructor savedInstructor = instructorService.registerInstructor(instructorDto);

        // Act
        Instructor result = instructorService.getInstructorByName("John Doe");

        // Assert
        assertNotNull(result);
        assertEquals(savedInstructor.getId(), result.getId());
        assertEquals(savedInstructor.getName(), result.getName());
        assertEquals(savedInstructor.getSpecialization(), result.getSpecialization());
    }

    @Test
    void getInstructorById_ShouldReturnInstructor() {
        // Arrange
        Instructor savedInstructor = instructorService.registerInstructor(instructorDto);

        // Act
        Instructor result = instructorService.getInstructorById(savedInstructor.getId());

        // Assert
        assertNotNull(result);
        assertEquals(savedInstructor.getId(), result.getId());
        assertEquals(savedInstructor.getName(), result.getName());
        assertEquals(savedInstructor.getSpecialization(), result.getSpecialization());
    }

    @Test
    void getInstructorById_WhenInstructorNotFound_ShouldThrowBusinessException() {
        // Act & Assert
        assertThrows(BusinessException.class, () -> instructorService.getInstructorById(999L));
    }

    @Test
    void getAllInstructors_ShouldReturnListOfInstructors() {
        // Arrange
        Instructor instructor1 = instructorService.registerInstructor(instructorDto);
        Instructor instructor2 = instructorService.registerInstructor(
            new InstructorDto("Jane Doe", "Data Science"));

        // Act
        List<Instructor> result = instructorService.getAllInstructors();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(i -> i.getId().equals(instructor1.getId())));
        assertTrue(result.stream().anyMatch(i -> i.getId().equals(instructor2.getId())));
    }
}
