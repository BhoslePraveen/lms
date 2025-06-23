package com.sunkiran.lms.repo;

import com.sunkiran.lms.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment,Long> {
}
