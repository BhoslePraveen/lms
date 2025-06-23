package com.sunkiran.lms.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
@Getter
@Setter
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "spec")
    private String specialization;

    @OneToMany( mappedBy = "instructor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Course> courses = new ArrayList<>();
}
