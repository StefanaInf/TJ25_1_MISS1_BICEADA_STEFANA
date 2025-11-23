package com.example.Homework_3.repository;

import com.example.Homework_3.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAcademicYear(Integer academicYear);

    @Query("SELECT s FROM Student s WHERE s.email = :email")
    Student findStudentByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.name = :name WHERE s.code = :code")
    int updateStudentNameByCode(String code, String name);
}
