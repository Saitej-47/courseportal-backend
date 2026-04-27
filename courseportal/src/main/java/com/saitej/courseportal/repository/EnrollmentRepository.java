package com.saitej.courseportal.repository;

import com.saitej.courseportal.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudentId(Long studentId);

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    @Query(value = """
        SELECT c.id, c.course_name AS courseName, c.day,
               c.start_time AS startTime, c.end_time AS endTime,
               c.faculty_name AS faculty
        FROM enrollment e
        JOIN course c ON e.course_id = c.id
        WHERE e.student_id = :studentId
        """, nativeQuery = true)
    List<Object[]> getCoursesByStudent(@Param("studentId") Long studentId);

    @Query(value = """
        SELECT c.faculty_name AS faculty, COUNT(e.student_id) AS students
        FROM enrollment e
        JOIN course c ON e.course_id = c.id
        WHERE c.faculty_name IS NOT NULL AND c.faculty_name != ''
        GROUP BY c.faculty_name
        """, nativeQuery = true)
    List<Object[]> getFacultyStudentCounts();

    @Query(value = """
        SELECT e.id, s.name AS studentName, c.course_name AS courseName, c.faculty_name AS facultyName
        FROM enrollment e
        JOIN student s ON e.student_id = s.id
        JOIN course c ON e.course_id = c.id
        """, nativeQuery = true)
    List<Object[]> getAllEnrollmentDetails();
}