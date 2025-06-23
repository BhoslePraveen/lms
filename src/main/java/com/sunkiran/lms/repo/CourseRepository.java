package com.sunkiran.lms.repo;

import com.sunkiran.lms.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
