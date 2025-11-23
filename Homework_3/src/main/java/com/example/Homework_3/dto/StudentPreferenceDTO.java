package com.example.Homework_3.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentPreferenceDTO {

    @NotNull
    private Long studentId;

    @NotNull
    private Long courseId;

    @Min(value = 1)
    private Integer rank;
}
