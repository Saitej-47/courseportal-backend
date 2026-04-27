package com.saitej.courseportal.repository;

import com.saitej.courseportal.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}