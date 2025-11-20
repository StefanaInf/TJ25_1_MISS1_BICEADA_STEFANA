package com.example.Homework_3.controller;

import com.example.Homework_3.domain.Instructor;
import com.example.Homework_3.domain.Role;
import com.example.Homework_3.domain.Student;
import com.example.Homework_3.domain.UserEntity;
import com.example.Homework_3.dto.auth.AuthRequestDto;
import com.example.Homework_3.dto.auth.AuthResponseDto;
import com.example.Homework_3.dto.auth.RegisterDto;
import com.example.Homework_3.repository.UserRepository;
import com.example.Homework_3.security.JwtService;
import com.example.Homework_3.service.InstructorService;
import com.example.Homework_3.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto request) throws Exception {
        AuthenticationManager authManager = authenticationConfiguration.getAuthenticationManager();
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        UserEntity user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String jwt = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponseDto(jwt, user.getEmail(), user.getRole()));
    }

    @PostMapping("/register/instructor")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerInstructor(@RequestBody RegisterDto request)
    {
        if(userRepository.existsByEmail(request.email())){
            return  ResponseEntity.badRequest().body("Email is already registered");
        }

        Instructor instructor = new Instructor();
        instructor.setEmail(request.email());
        instructor.setName(request.name());
        instructor = instructorService.save(instructor);

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(request.email());
        userEntity.setPassword(passwordEncoder.encode(request.password()));
        userEntity.setRole(Role.INSTRUCTOR);
        userEntity = userRepository.save(userEntity);

        String jwt = jwtService.generateToken(userEntity);
        return  ResponseEntity.ok(new AuthResponseDto(jwt, userEntity.getEmail(), userEntity.getRole()));
    }

    @PostMapping("/register/student")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerStudent(@RequestBody RegisterDto request)
    {
        if(userRepository.existsByEmail(request.email())){
            return  ResponseEntity.badRequest().body("Email is already registered");
        }

        Student student = new Student();
        student.setEmail(request.email());
        student.setName(request.name());
        student.setAcademicYear(1);
        student.setCode("15678`");
        student = studentService.save(student);

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(request.email());
        userEntity.setPassword(passwordEncoder.encode(request.password()));
        userEntity.setRole(Role.STUDENT);
        userEntity = userRepository.save(userEntity);

        String jwt = jwtService.generateToken(userEntity);
        return  ResponseEntity.ok(new AuthResponseDto(jwt, userEntity.getEmail(), userEntity.getRole()));
    }
}
