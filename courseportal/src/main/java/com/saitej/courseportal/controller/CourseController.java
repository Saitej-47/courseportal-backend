package com.saitej.courseportal.controller;

import com.saitej.courseportal.entity.Course;
import com.saitej.courseportal.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/all")
    public List<Map<String, Object>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Course c : courses) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", c.getId());
            map.put("courseName", c.getCourseName());
            map.put("faculty", c.getFacultyName());
            map.put("day", c.getDay());
            map.put("startTime", c.getStartTime());
            map.put("endTime", c.getEndTime());
            result.add(map);
        }
        return result;
    }

    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
    }
}