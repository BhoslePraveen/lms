package com.sunkiran.lms.repo;

import com.sunkiran.lms.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    // Method Query / Derived Query
    Instructor findByName(String name);

    // Native Query
    // Database Table and columns names should be used.
    @Query(value = "SELECT * FROM instructors WHERE name = :Iname", nativeQuery = true)
    Instructor findByNameNativeQuery(@Param("Iname") String name);


    // JPQL Query
    // Java Variables and Class names
    @Query("SELECT i FROM Instructor i WHERE i.name = :Iname")
    Instructor findByNameJpaQuery(@Param("Iname") String name);

}
