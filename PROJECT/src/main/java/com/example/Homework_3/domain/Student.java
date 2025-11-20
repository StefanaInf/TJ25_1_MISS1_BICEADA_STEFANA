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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public Student(String code, String name, String email, Integer academicYear, UserEntity user) {
        super(name, email);
        this.code = code;
        this.academicYear = academicYear;
        this.user = user;
    }
}
