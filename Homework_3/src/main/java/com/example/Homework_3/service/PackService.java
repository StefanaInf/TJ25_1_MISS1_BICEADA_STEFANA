package com.example.Homework_3.service;

import com.example.Homework_3.domain.Pack;
import com.example.Homework_3.repository.PackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PackService {

    private final PackRepository packRepository;

    public PackService(PackRepository packRepository) {
        this.packRepository = packRepository;
    }

    public Pack save(Pack pack) {
        return packRepository.save(pack);
    }

    public Optional<Pack> findById(Long id) {
        return packRepository.findById(id);
    }

    public List<Pack> findAll() {
        return packRepository.findAll();
    }

    public List<Pack> findByAcademicYear(Integer year) {
        return packRepository.findByAcademicYear(year);
    }

    public List<Pack> findBySemester(Integer semester) {
        return packRepository.findBySemester(semester);
    }

    @Transactional
    public int updatePackName(Long id, String name) {
        return packRepository.updatePackNameById(id, name);
    }

    public void deleteById(Long id) {
        packRepository.deleteById(id);
    }
}
