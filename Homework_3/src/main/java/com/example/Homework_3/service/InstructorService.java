package com.example.Homework_3.service;

import com.example.Homework_3.domain.Instructor;
import com.example.Homework_3.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor save(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Optional<Instructor> findById(Long id) {
        return instructorRepository.findById(id);
    }

    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    public Instructor findByEmail(String email) {
        return instructorRepository.findByEmail(email);
    }

    public List<Instructor> findByNameContaining(String substring) {
        return instructorRepository.findByNameContaining(substring);
    }

    public void deleteById(Long id) {
        instructorRepository.deleteById(id);
    }
}
