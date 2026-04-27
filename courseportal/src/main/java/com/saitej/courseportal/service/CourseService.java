package com.saitej.courseportal.service;

import com.saitej.courseportal.entity.Course;
import com.saitej.courseportal.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // ✅ GET ALL COURSES
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // ✅ ADD COURSE
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    // ✅ DELETE COURSE
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}