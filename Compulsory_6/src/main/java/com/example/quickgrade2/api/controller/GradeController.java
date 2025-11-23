package com.example.quickgrade2.api.controller;

import com.example.quickgrade2.api.event.GradeEvent;
import com.example.quickgrade2.api.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService service;

    @PostMapping
    public ResponseEntity<String> addGrade(@RequestBody GradeEvent event) {
        service.publishGrade(
                event.studentCode(),
                event.courseCode(),
                event.grade()
        );
        return ResponseEntity.ok("Grade added successfully");
    }
}
