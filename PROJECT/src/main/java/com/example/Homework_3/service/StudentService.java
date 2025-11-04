package com.example.Homework_3.service;

import com.example.Homework_3.domain.Student;
import com.example.Homework_3.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findByAcademicYear(Integer academicYear) {
        return studentRepository.findByAcademicYear(academicYear);
    }

    public Student findByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }

    @Transactional
    public int updateNameByCode(String code, String name) {
        return studentRepository.updateStudentNameByCode(code, name);
    }

    public Student update(Long id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setCode(updatedStudent.getCode());
                    existingStudent.setName(updatedStudent.getName());
                    existingStudent.setEmail(updatedStudent.getEmail());
                    existingStudent.setAcademicYear(updatedStudent.getAcademicYear());
                    return studentRepository.save(existingStudent);
                })
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
    }

    public Student patch(Long id, Student updatedFields) {
        return studentRepository.findById(id)
                .map(existing -> {
                    if (updatedFields.getCode() != null) existing.setCode(updatedFields.getCode());
                    if (updatedFields.getName() != null) existing.setName(updatedFields.getName());
                    if (updatedFields.getEmail() != null) existing.setEmail(updatedFields.getEmail());
                    if (updatedFields.getAcademicYear() != null) existing.setAcademicYear(updatedFields.getAcademicYear());
                    return studentRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
    }

    public boolean deleteById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
