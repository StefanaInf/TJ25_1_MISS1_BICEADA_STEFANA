package com.example.quickgrade2.api.event;

public record GradeEvent(String studentCode, String courseCode, Double grade) {
}
