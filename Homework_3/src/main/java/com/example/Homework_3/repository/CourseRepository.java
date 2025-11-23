package com.example.Homework_3.repository;

import com.example.Homework_3.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByType(String type);

    @Query("SELECT c FROM Course c WHERE c.instructor.email = :email")
    List<Course> findCoursesByInstructorEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Course c SET c.groupCount = :groupCount WHERE c.code = :code")
    int updateGroupCountByCode(String code, Integer groupCount);
}
