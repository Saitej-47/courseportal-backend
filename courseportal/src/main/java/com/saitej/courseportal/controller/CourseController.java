package com.saitej.courseportal.controller;

import com.saitej.courseportal.entity.Course;
import com.saitej.courseportal.entity.Student;
import com.saitej.courseportal.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @GetMapping("/all")
    public List<Course> getCourses(){
        return courseService.getAllCourses();
    }
    @PostMapping("/enroll")
    public Student enroll(
            @RequestParam Long studentId,
            @RequestParam Long courseId
    ){
        return courseService.enrollCourse(studentId, courseId);
    }

}
