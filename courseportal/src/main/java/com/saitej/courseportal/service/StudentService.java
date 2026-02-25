package com.saitej.courseportal.service;

import com.saitej.courseportal.entity.Course;
import com.saitej.courseportal.entity.Student;
import com.saitej.courseportal.repository.CourseRepository;
import com.saitej.courseportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // ⭐ GET STUDENT BY ID (🔥 ADD THIS)
    public Student getStudentById(Long studentId){
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // ⭐ ENROLL COURSE (no duplicates)
    public Student enrollCourse(Long studentId, Long courseId){

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        List<Course> courses = student.getCourses();

        // ⭐ prevent duplicate registration
        boolean alreadyEnrolled = courses.stream()
                .anyMatch(c -> c.getId().equals(courseId));

        if(alreadyEnrolled){
            throw new RuntimeException("Already enrolled in this course");
        }

        courses.add(course);

        return studentRepository.save(student);
    }

    // ⭐ GET REGISTERED COURSES
    public List<Course> getStudentCourses(Long studentId){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return student.getCourses();
    }
}