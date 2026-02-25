package com.saitej.courseportal.service;

import com.saitej.courseportal.entity.Course;
import com.saitej.courseportal.entity.Student;
import com.saitej.courseportal.repository.CourseRepository;
import com.saitej.courseportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // ✅ REGISTER
    public Student register(Student student) {

        if(studentRepository.findByEmail(student.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists!");
        }

        student.setRole("STUDENT");
        return studentRepository.save(student);
    }

    // ✅ LOGIN
    public Student login(String email, String password){

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!student.getPassword().equals(password)){
            throw new RuntimeException("Invalid password");
        }

        return student;
    }

    // 🔥 ENROLL COURSE (FIXED)
    public Student enrollCourse(Long studentId, Long courseId){

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if(student.getCourses() == null){
            student.setCourses(new ArrayList<>());
        }

        student.getCourses().add(course);

        return studentRepository.save(student);
    }
}
