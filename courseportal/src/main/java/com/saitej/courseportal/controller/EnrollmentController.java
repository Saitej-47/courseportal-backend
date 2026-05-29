package com.saitej.courseportal.controller;

import com.saitej.courseportal.entity.Enrollment;
import com.saitej.courseportal.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enroll")
@CrossOrigin(origins = {"https://courseportal-frontend.vercel.app", "http://localhost:3000"})
public class EnrollmentController {

    @Autowired
    private EnrollmentService service;

    @PostMapping("/{studentId}/{courseId}")
    public Enrollment enroll(@PathVariable Long studentId, @PathVariable Long courseId){
        return service.enroll(studentId, courseId);
    }

    @GetMapping("/{studentId}")
    public List<Enrollment> getEnrollments(@PathVariable Long studentId){
        return service.getStudentEnrollments(studentId);
    }

    // ⭐ Timetable API
    @GetMapping("/student/{studentId}/courses")
    public List<Map<String,Object>> getStudentCourses(@PathVariable Long studentId){
        return service.getStudentCourses(studentId);
    }

    // ⭐ NEW: Faculty vs student analytics API
    @GetMapping("/faculty-students")
    public List<Map<String,Object>> getFacultyStudentCounts(){
        return service.getFacultyStudentCounts();
    }
    @GetMapping("/all-details")
    public List<Map<String,Object>> getAllEnrollments(){
        return service.getAllEnrollments();
    }
}