package com.example.Homework_3.repository;

import com.example.Homework_3.domain.StudentPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPreferenceRespository extends JpaRepository<StudentPreference, Long> {
}
