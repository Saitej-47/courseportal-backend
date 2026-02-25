package com.saitej.courseportal.repository;

import com.saitej.courseportal.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudentId(Long studentId);

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    // ⭐ Timetable query (JOIN enrollment + course)
    @Query(value = """
        SELECT c.id,
               c.course_name AS courseName,
               c.day,
               c.start_time AS startTime,
               c.end_time AS endTime,
               c.faculty
        FROM enrollment e
        JOIN course c ON e.course_id = c.id
        WHERE e.student_id = :studentId
        """, nativeQuery = true)
    List<Object[]> getCoursesByStudent(@Param("studentId") Long studentId);


    // ⭐ Faculty vs student analytics query (NEW)
    @Query(value = """
        SELECT c.faculty AS faculty, COUNT(e.student_id) AS students
        FROM enrollment e
        JOIN course c ON e.course_id = c.id
        GROUP BY c.faculty
        """, nativeQuery = true)
    List<Object[]> getFacultyStudentCounts();
}