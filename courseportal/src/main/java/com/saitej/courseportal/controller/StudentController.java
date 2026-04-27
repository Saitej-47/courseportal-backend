package com.saitej.courseportal.controller;

import com.saitej.courseportal.entity.Course;
import com.saitej.courseportal.entity.Student;
import com.saitej.courseportal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ✅ GET ALL STUDENTS (🔥 IMPORTANT FOR ADMIN PAGE)
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // ✅ DELETE STUDENT
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }

    // EXISTING METHODS 👇
    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable Long studentId){
        return studentService.getStudentById(studentId);
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public Student enrollCourse(@PathVariable Long studentId,
                                @PathVariable Long courseId){
        return studentService.enrollCourse(studentId, courseId);
    }

    @GetMapping("/{studentId}/courses")
    public List<Course> getStudentCourses(@PathVariable Long studentId){
        return studentService.getStudentCourses(studentId);
    }
}