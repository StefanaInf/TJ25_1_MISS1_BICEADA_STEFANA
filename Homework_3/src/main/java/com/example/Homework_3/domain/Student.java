package com.example.Homework_3.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import com.example.Homework_3.domain.Person;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Student extends Person {

    @Column(unique = true, nullable = false)
    private String code;

    @Column(name = "academic_year", nullable = false)
    private Integer academicYear;

    public Student(String code, String name, String email, Integer academicYear) {
        super(name, email);
        this.code = code;
        this.academicYear = academicYear;
    }
}
