package com.example.quickgrade2.api.service;

import com.example.quickgrade2.api.event.GradeEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final KafkaTemplate<String, GradeEvent> kafkaTemplate;
    private static final String TOPIC="student-grades";

    public void publishGrade(String studentCode, String courseCode, Double grade) {
        GradeEvent event = new GradeEvent(studentCode, courseCode, grade);
        try {
            kafkaTemplate.send(TOPIC, studentCode, event).get(10, TimeUnit.SECONDS);
            System.out.println("Successfully sent to Kafka: " + event);
        } catch (Exception e) {
            System.err.println("FAILED to send to Kafka: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Kafka send failed", e);
        }
    }
}
