package com.sunkiran.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunkiran.lms.dto.request.InstructorDto;
import com.sunkiran.lms.model.Instructor;
import com.sunkiran.lms.repo.InstructorRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class InstructorControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InstructorRepository instructorRepository;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        instructorRepository.deleteAll();
    }

    @Test
    @Order(1)
    void testRegisterInstructor() throws Exception {
        InstructorDto dto = new InstructorDto("Amit Kumar", "DevOps");

        given()
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(dto))
                .when()
                .post("/instructor/register")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("name", Matchers.equalTo("Amit Kumar"))
                .body("specialization", Matchers.equalTo("DevOps"));

        assertThat(instructorRepository.findAll()).hasSize(1);
    }

    @Test
    @Order(2)
    void testGetInstructorById() {
        Instructor instructor = new Instructor();
        instructor.setName("John");
        instructor.setSpecialization("Java");
        Instructor saved = instructorRepository.save(instructor);

        given()
                .when()
                .get("/instructor/" + saved.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", Matchers.equalTo("John"));
    }

    @Test
    @Order(3)
    void testUpdateInstructor() throws Exception {
        Instructor saved = instructorRepository.save(new Instructor(null, "Old Name", "C++", null));

        InstructorDto dto = new InstructorDto("Updated Name", "Python");

        given()
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(dto))
                .when()
                .put("/instructor/" + saved.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", Matchers.equalTo("Updated Name"))
                .body("specialization", Matchers.equalTo("Python"));
    }

    @Test
    @Order(4)
    void testGetAllInstructors() {
        instructorRepository.save(new Instructor(null, "A", "X", null));
        instructorRepository.save(new Instructor(null, "B", "Y", null));

        given()
                .when()
                .get("/instructor/all")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", Matchers.equalTo(2));
    }

    @Test
    @Order(5)
    void testDeleteInstructor() {
        Instructor instructor = new Instructor();
        instructor.setName("ToDelete");
        instructor.setSpecialization("Scala");
        Instructor saved = instructorRepository.save(instructor);

        given()
                .when()
                .delete("/instructor/" + saved.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(Matchers.equalTo("true"));

        assertThat(instructorRepository.existsById(saved.getId())).isFalse();
    }

    @Test
    @Order(6)
    void testGetInstructorByName() {
        instructorRepository.save(new Instructor(null, "Alok", "Rust", null));

        given()
                .when()
                .get("/instructor/name/Alok")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("specialization", Matchers.equalTo("Rust"));
    }


}
