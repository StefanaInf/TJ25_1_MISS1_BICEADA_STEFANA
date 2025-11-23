package com.example.Homework_3.repository;

import com.example.Homework_3.domain.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PackRepository extends JpaRepository<Pack, Long> {

    List<Pack> findByAcademicYear(Integer academicYear);

    @Query("SELECT p FROM Pack p WHERE p.semester = :semester")
    List<Pack> findBySemester(Integer semester);

    @Transactional
    @Modifying
    @Query("UPDATE Pack p SET p.name = :name WHERE p.id = :id")
    int updatePackNameById(Long id, String name);
}
