package com.saitej.courseportal.service;

import com.saitej.courseportal.entity.Course;
import com.saitej.courseportal.entity.Student;
import com.saitej.courseportal.repository.CourseRepository;
import com.saitej.courseportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    // ✅ Add course (Admin)
    public Course addCourse(Course course){
        return courseRepository.save(course);
    }

    // ✅ View all courses
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    // 🔥 Enroll course (Student)
    public Student enrollCourse(Long studentId, Long courseId){

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // 🔥 Prevent duplicate
        if(student.getCourses().contains(course)){
            throw new RuntimeException("Already enrolled!");
        }

        student.getCourses().add(course);
        return studentRepository.save(student);
    }

}
