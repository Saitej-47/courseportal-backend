package com.saitej.courseportal.controller;

import com.saitej.courseportal.repository.StudentRepository;
import com.saitej.courseportal.repository.CourseRepository;
import com.saitej.courseportal.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @GetMapping("/stats")
    public Map<String, Long> getStats() {

        Map<String, Long> stats = new HashMap<>();

        long students = studentRepository.count();

        // ⭐ FIXED: unique course names
        long courses = courseRepository.findAll()
                .stream()
                .map(c -> c.getCourseName())
                .distinct()
                .count();

        long enrollments = enrollmentRepository.count();

        // ⭐ distinct faculty
        long faculty = courseRepository.findAll()
                .stream()
                .map(c -> c.getFaculty())
                .distinct()
                .count();

        stats.put("students", students);
        stats.put("courses", courses);
        stats.put("faculty", faculty);
        stats.put("enrollments", enrollments);

        return stats;
    }
}