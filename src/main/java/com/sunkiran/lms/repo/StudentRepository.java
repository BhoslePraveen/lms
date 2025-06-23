package com.sunkiran.lms.repo;

import com.sunkiran.lms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}

