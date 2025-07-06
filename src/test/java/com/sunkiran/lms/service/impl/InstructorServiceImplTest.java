package com.sunkiran.lms.service.impl;

import com.sunkiran.lms.dto.request.CourseDto;
import com.sunkiran.lms.dto.request.InstructorDto;
import com.sunkiran.lms.exception.BusinessException;
import com.sunkiran.lms.model.Course;
import com.sunkiran.lms.model.Instructor;
import com.sunkiran.lms.repo.CourseRepository;
import com.sunkiran.lms.repo.InstructorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorServiceImplTest {

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private InstructorServiceImpl instructorService;

    private InstructorDto instructorDto;
    private Instructor instructor;
    private CourseDto courseDto;
    private Course course;

    @BeforeEach
    void setUp() {
        // Setup test data
        instructorDto = new InstructorDto("John Doe", "Computer Science");

        instructor = new Instructor();
        instructor.setId(1L);
        instructor.setName("John Doe");
        instructor.setSpecialization("Computer Science");

        courseDto = new CourseDto();
        courseDto.setTitle("Java Programming");
        courseDto.setDescription("Learn Java Programming");
        courseDto.setDuration(3);

        course = new Course();
        course.setId(1L);
        course.setTitle("Java Programming");
        course.setDescription("Learn Java Programming");
        course.setDuration(3);
        course.setInstructor(instructor);
    }

    @Test
    void testRegisterInstructor_ShouldReturnSavedInstructor() {
        // Given
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        // When
        Instructor result = instructorService.registerInstructor(instructorDto);

        // Then
        assertNotNull(result);
        assertEquals(instructor.getId(), result.getId());
        assertEquals(instructor.getName(), result.getName());
        assertEquals(instructor.getSpecialization(), result.getSpecialization());
        verify(instructorRepository, times(1)).save(any(Instructor.class));
    }

    @Test
    void createCourse_ShouldReturnSavedCourse() {
        // Given
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        // When
        Course result = instructorService.createCourse(1L, courseDto);

        // Then
        assertNotNull(result);
        assertEquals(course.getId(), result.getId());
        assertEquals(course.getTitle(), result.getTitle());
        assertEquals(course.getDescription(), result.getDescription());
        assertEquals(course.getDuration(), result.getDuration());
        assertEquals(course.getInstructor().getId(), result.getInstructor().getId());
        verify(instructorRepository, times(1)).findById(1L);
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void createCourse_WhenInstructorNotFound_ShouldThrowException() {
        // Arrange
        when(instructorRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> instructorService.createCourse(1L, courseDto));
        verify(instructorRepository, times(1)).findById(1L);
        verify(courseRepository, never()).save(any(Course.class));
    }

    @Test
    void updateInstructor_ShouldReturnUpdatedInstructor() {
        // Arrange
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        // Act
        Instructor result = instructorService.updateInstructor(1L, instructorDto);

        // Assert
        assertNotNull(result);
        assertEquals(instructor.getId(), result.getId());
        assertEquals(instructor.getName(), result.getName());
        assertEquals(instructor.getSpecialization(), result.getSpecialization());
        verify(instructorRepository, times(1)).findById(1L);
        verify(instructorRepository, times(1)).save(any(Instructor.class));
    }

    @Test
    void updateInstructor_WhenInstructorNotFound_ShouldThrowException() {
        // Arrange
        when(instructorRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> instructorService.updateInstructor(1L, instructorDto));
        verify(instructorRepository, times(1)).findById(1L);
        verify(instructorRepository, never()).save(any(Instructor.class));
    }

    @Test
    void removeInstructor_WhenInstructorExists_ShouldReturnTrue() {
        // Arrange
        when(instructorRepository.existsById(1L)).thenReturn(true);
        doNothing().when(instructorRepository).deleteById(1L);

        // Act
        boolean result = instructorService.removeInstructor(1L);

        // Assert
        assertTrue(result);
        verify(instructorRepository, times(1)).existsById(1L);
        verify(instructorRepository, times(1)).deleteById(1L);
    }

    @Test
    void removeInstructor_WhenInstructorDoesNotExist_ShouldReturnFalse() {
        // Arrange
        when(instructorRepository.existsById(1L)).thenReturn(false);

        // Act
        boolean result = instructorService.removeInstructor(1L);

        // Assert
        assertFalse(result);
        verify(instructorRepository, times(1)).existsById(1L);
        verify(instructorRepository, never()).deleteById(any());
    }

    @Test
    void getInstructorByName_ShouldReturnInstructor() {
        // Arrange
        when(instructorRepository.findByNameNativeQuery("John Doe")).thenReturn(instructor);

        // Act
        Instructor result = instructorService.getInstructorByName("John Doe");

        // Assert
        assertNotNull(result);
        assertEquals(instructor.getId(), result.getId());
        assertEquals(instructor.getName(), result.getName());
        assertEquals(instructor.getSpecialization(), result.getSpecialization());
        verify(instructorRepository, times(1)).findByNameNativeQuery("John Doe");
    }

    @Test
    void getInstructorById_ShouldReturnInstructor() {
        // Arrange
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

        // Act
        Instructor result = instructorService.getInstructorById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(instructor.getId(), result.getId());
        assertEquals(instructor.getName(), result.getName());
        assertEquals(instructor.getSpecialization(), result.getSpecialization());
        verify(instructorRepository, times(1)).findById(1L);
    }

    @Test
    void getInstructorById_WhenInstructorNotFound_ShouldThrowBusinessException() {
        // Arrange
        when(instructorRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BusinessException.class, () -> instructorService.getInstructorById(1L));
        verify(instructorRepository, times(1)).findById(1L);
    }

    @Test
    void getAllInstructors_ShouldReturnListOfInstructors() {
        // Arrange
        List<Instructor> instructors = Arrays.asList(instructor);
        when(instructorRepository.findAll()).thenReturn(instructors);

        // Act
        List<Instructor> result = instructorService.getAllInstructors();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(instructor.getId(), result.get(0).getId());
        assertEquals(instructor.getName(), result.get(0).getName());
        assertEquals(instructor.getSpecialization(), result.get(0).getSpecialization());
        verify(instructorRepository, times(1)).findAll();
    }
}
