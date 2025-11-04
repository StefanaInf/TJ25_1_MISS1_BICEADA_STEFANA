package com.example.Homework_3.controller;

import com.example.Homework_3.domain.StudentPreference;
import com.example.Homework_3.dto.StudentPreferenceDTO;
import com.example.Homework_3.service.StudentPreferenceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/preferences")
public class StudentPreferenceController {
    private final StudentPreferenceService service;

    public StudentPreferenceController(StudentPreferenceService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<StudentPreference> getById(
            @PathVariable Long id,
            @RequestHeader(value = "If-None-Match", required = false) String ifNoneMatch) {

        Optional<StudentPreference> optionalPref = service.getById(id);
        if (optionalPref.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        StudentPreference pref = optionalPref.get();

        String eTag = "\"" + (pref.getId() + "-" + pref.getRank()) + "\"";

        if (ifNoneMatch != null && ifNoneMatch.equals(eTag)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).eTag(eTag).build();
        }

        return ResponseEntity.ok()
                .eTag(eTag)
                .body(pref);
    }

    @GetMapping
    public List<StudentPreference> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<StudentPreference> create(@Valid @RequestBody StudentPreferenceDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentPreference> update(
            @PathVariable Long id,
            @Valid @RequestBody StudentPreferenceDTO dto) {
        return ResponseEntity.ok(service.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
