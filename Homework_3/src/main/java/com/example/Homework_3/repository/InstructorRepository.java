package com.example.Homework_3.repository;

import com.example.Homework_3.domain.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Instructor findByEmail(String email);

    @Query("SELECT i FROM Instructor i WHERE i.name LIKE %:name%")
    java.util.List<Instructor> findByNameContaining(String name);
}
