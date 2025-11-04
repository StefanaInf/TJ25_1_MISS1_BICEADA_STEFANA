package com.example.Homework_3.service;

import com.example.Homework_3.domain.Course;
import com.example.Homework_3.domain.Student;
import com.example.Homework_3.domain.StudentPreference;
import com.example.Homework_3.dto.StudentPreferenceDTO;
import com.example.Homework_3.exception.ResourceNotFoundException;
import com.example.Homework_3.repository.CourseRepository;
import com.example.Homework_3.repository.StudentPreferenceRespository;
import com.example.Homework_3.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentPreferenceService {

    private final StudentPreferenceRespository preferenceRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentPreferenceService(StudentPreferenceRespository preferenceRespository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.preferenceRepository = preferenceRespository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<StudentPreference> getAll(){
        return preferenceRepository.findAll();
    }

    public Optional<StudentPreference> getById(long id){
        return preferenceRepository.findById(id);
    }

    public StudentPreference create(StudentPreferenceDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        StudentPreference pref = new StudentPreference();
        pref.setStudent(student);
        pref.setCourse(course);
        pref.setRank(dto.getRank());

        return preferenceRepository.save(pref);
    }

    public StudentPreference update(StudentPreferenceDTO dto, Long id) {
        StudentPreference pref = preferenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Preference not found"));

        if (dto.getStudentId() != null) {
            Student student = studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
            pref.setStudent(student);
        }

        if (dto.getCourseId() != null) {
            Course course = courseRepository.findById(dto.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
            pref.setCourse(course);
        }

        pref.setRank(dto.getRank());

        return preferenceRepository.save(pref);
    }

    public void delete(Long id) {
        if (!preferenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Preference not found");
        }
        preferenceRepository.deleteById(id);
    }
}
