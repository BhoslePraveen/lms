package com.sunkiran.lms.repo;

import com.sunkiran.lms.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module,Long> {
}
