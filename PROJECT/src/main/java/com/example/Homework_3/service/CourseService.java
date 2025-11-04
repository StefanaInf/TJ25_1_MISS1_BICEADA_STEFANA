package com.example.Homework_3.service;

import com.example.Homework_3.domain.Course;
import com.example.Homework_3.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public List<Course> findByType(String type) {
        return courseRepository.findByType(type);
    }

    public List<Course> findByInstructorEmail(String email) {
        return courseRepository.findCoursesByInstructorEmail(email);
    }

    @Transactional
    public int updateGroupCount(String code, Integer groupCount) {
        return courseRepository.updateGroupCountByCode(code, groupCount);
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }
}
