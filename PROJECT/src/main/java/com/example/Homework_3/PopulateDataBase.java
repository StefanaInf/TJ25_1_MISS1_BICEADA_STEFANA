package com.example.Homework_3;

import com.example.Homework_3.domain.Course;
import com.example.Homework_3.domain.Instructor;
import com.example.Homework_3.domain.Pack;
import com.example.Homework_3.domain.Student;
import com.example.Homework_3.dto.StudentPreferenceDTO;
import com.example.Homework_3.service.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Locale;
import java.util.Random;

@Configuration
public class PopulateDataBase {

    @Bean
    public CommandLineRunner seedDatabase(
            StudentService studentService,
            InstructorService instructorService,
            PackService packService,
            CourseService courseService,
            StudentPreferenceService preferenceService) {

        return args -> {
            Faker faker = new Faker(new Locale("en"));
            Random random = new Random();

            System.out.println("Seeding database...");

            Instructor instructor1 = new Instructor();
            instructor1.setName(faker.name().fullName());
            instructor1.setEmail(faker.internet().emailAddress());
            instructor1 = instructorService.save(instructor1);

            Instructor instructor2 = new Instructor();
            instructor2.setName(faker.name().fullName());
            instructor2.setEmail(faker.internet().emailAddress());
            instructor2 = instructorService.save(instructor2);

            Pack pack1 = new Pack();
            pack1.setName("Pack " + faker.educator().course());
            pack1.setAcademicYear(1);
            pack1.setSemester(1);
            pack1 = packService.save(pack1);

            Pack pack2 = new Pack();
            pack2.setName("Pack " + faker.educator().course());
            pack2.setAcademicYear(2);
            pack2.setSemester(2);
            pack2 = packService.save(pack2);

            for (int i = 0; i < 5; i++) {
                Course course = new Course();
                course.setName(faker.educator().course());
                course.setAbbr(faker.lorem().characters(3).toUpperCase());
                course.setCode("C" + faker.number().digits(3));
                course.setType(i % 2 == 0 ? "COMPULSORY" : "OPTIONAL");
                course.setGroupCount(random.nextInt(1, 4));
                course.setDescription(faker.lorem().sentence());

                course.setInstructor(i % 2 == 0 ? instructor1 : instructor2);
                course.setPack(i % 2 == 0 ? null : (i % 3 == 0 ? pack1 : pack2));

                courseService.save(course);
            }

            for (int i = 0; i < 10; i++) {
                Student student = new Student();
                student.setCode("S" + faker.number().digits(3));
                student.setName(faker.name().fullName());
                student.setEmail(faker.internet().emailAddress());
                student.setAcademicYear(random.nextInt(1, 4)); // Years 1-3
                studentService.save(student);
            }

            List<Student> students = studentService.findAll();
            List<Course> courses = courseService.findAll();

            for (Student student : students) {
                courses.stream()
                        .filter(c -> (c.getPack() == null || c.getPack().getAcademicYear() == student.getAcademicYear()))
                        .forEach(course -> {
                            int rank = random.nextInt(1, 6);
                            preferenceService.create(new StudentPreferenceDTO(
                                    student.getId(),
                                    course.getId(),
                                    rank
                            ));
                        });
            }

            System.out.println("\nAll students:");
            studentService.findAll().forEach(s ->
                    System.out.println(s.getCode() + " - " + s.getName() + " (Year " + s.getAcademicYear() + ")"));

            System.out.println("\nAll courses:");
            courseService.findAll().forEach(c ->
                    System.out.println(c.getCode() + " - " + c.getName() + " (" + c.getType() + ")"));

            System.out.println("\nUpdating group count for first course...");
            Course firstCourse = courseService.findAll().get(0);
            courseService.updateGroupCount(firstCourse.getCode(), 10);
            System.out.println("Updated course: " + firstCourse.getCode());

            System.out.println("\nDatabase seeding complete!");
        };
    }
}
