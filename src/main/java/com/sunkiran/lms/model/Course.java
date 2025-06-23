package com.sunkiran.lms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonBackReference
    private Instructor instructor;

    @OneToMany( mappedBy = "course" , cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Module> modules = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    @JsonBackReference
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Assignment> assignments = new ArrayList<>();
}
