package com.example.Homework_3.controller;

import com.example.Homework_3.domain.Pack;
import com.example.Homework_3.repository.PackRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/packs")
@RequiredArgsConstructor
public class PackController {

    private final PackRepository packRepository;

    @Operation(summary = "Get all packs", description = "Returns list of all academic packs")
    @GetMapping
    public List<Pack> getAllPacks() {
        return packRepository.findAll();
    }

    @Operation(summary = "Get pack by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Pack> getPackById(@PathVariable Long id) {
        Pack pack = packRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pack not found with id: " + id));
        return ResponseEntity.ok(pack);
    }

    @Operation(summary = "Get packs by academic year")
    @GetMapping("/year/{academicYear}")
    public List<Pack> getPacksByAcademicYear(@PathVariable Integer academicYear) {
        return packRepository.findByAcademicYear(academicYear);
    }

    @Operation(summary = "Get packs by semester")
    @GetMapping("/semester/{semester}")
    public List<Pack> getPacksBySemester(@PathVariable Integer semester) {
        return packRepository.findBySemester(semester);
    }

    @Operation(summary = "Create a new pack")
    @PostMapping
    public ResponseEntity<Pack> createPack(@Valid @RequestBody Pack pack) {
        pack.setId(null);
        Pack saved = packRepository.save(pack);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "Update entire pack by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Pack> updatePack(@PathVariable Long id, @Valid @RequestBody Pack packDetails) {
        Pack pack = packRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pack not found with id: " + id));

        pack.setAcademicYear(packDetails.getAcademicYear());
        pack.setSemester(packDetails.getSemester());
        pack.setName(packDetails.getName());

        Pack updated = packRepository.save(pack);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Update only the name of a pack")
    @PatchMapping("/{id}/name")
    public ResponseEntity<String> updatePackName(@PathVariable Long id, @RequestBody String newName) {
        newName = newName.replace("\"", "").trim();
        int updated = packRepository.updatePackNameById(id, newName);

        if (updated == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pack not found or name not changed");
        }
        return ResponseEntity.ok("Pack name updated successfully");
    }

    @Operation(summary = "Delete a pack by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePack(@PathVariable Long id) {
        if (!packRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pack not found with id: " + id);
        }
        packRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}