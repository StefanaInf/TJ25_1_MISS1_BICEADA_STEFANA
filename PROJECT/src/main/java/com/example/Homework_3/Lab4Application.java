package com.example.Homework_3;

import com.example.Homework_3.domain.Role;
import com.example.Homework_3.domain.Student;
import com.example.Homework_3.domain.UserEntity;
import com.example.Homework_3.repository.UserRepository;
import com.example.Homework_3.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Lab4Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab4Application.class, args);
    }

    @Bean
    public CommandLineRunner seedAdminUser(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            String adminEmail = "admin@lab.com";
            if (userRepository.existsByEmail(adminEmail)) {
                System.out.println("Admin user already exists: " + adminEmail);
                return;
            }

            UserEntity admin = new UserEntity();
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("admin123")); // change later!
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);

            System.out.println("Created admin user:");
            System.out.println("   Email: " + adminEmail);
            System.out.println("   Password: admin123");
            System.out.println("   Role: ADMIN");
            System.out.println("   Login at: POST /api/auth/login");
        };
    }
}
