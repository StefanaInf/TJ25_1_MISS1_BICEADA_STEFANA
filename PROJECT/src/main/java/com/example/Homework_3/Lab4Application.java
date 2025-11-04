package com.example.Homework_3;

import com.example.Homework_3.domain.Student;
import com.example.Homework_3.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab4Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab4Application.class, args);
    }

    @Bean
    public CommandLineRunner runTest(StudentService studentService) {
        return args -> {

            Student student1 = new Student("S001", "Alice Smith", "alice.s@university.edu", 1);
            Student savedStudent1 = studentService.save(student1);
            System.out.println("Saved student: " + savedStudent1);

            Student student2 = new Student("S002", "Bob Johnson", "bob.j@university.edu", 2);
            studentService.save(student2);

            studentService.findById(savedStudent1.getId())
                    .ifPresent(s -> System.out.println("Retrieved student by ID: " + s));

            System.out.println("\nAll students:");
            studentService.findAll().forEach(s ->
                    System.out.println("- " + s.getName() + " (" + s.getAcademicYear() + ")"));
        };
    }
}
