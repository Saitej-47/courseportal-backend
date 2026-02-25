package com.saitej.courseportal.service;

import com.saitej.courseportal.entity.Enrollment;
import com.saitej.courseportal.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository repo;

    public Enrollment enroll(Long studentId, Long courseId){
        if(repo.existsByStudentIdAndCourseId(studentId, courseId)){
            return null;
        }
        return repo.save(new Enrollment(studentId, courseId));
    }

    public List<Enrollment> getStudentEnrollments(Long studentId){
        return repo.findByStudentId(studentId);
    }

    // ⭐ Timetable method
    public List<Map<String,Object>> getStudentCourses(Long studentId){
        List<Object[]> rows = repo.getCoursesByStudent(studentId);

        List<Map<String,Object>> result = new ArrayList<>();

        for(Object[] r : rows){
            Map<String,Object> m = new HashMap<>();
            m.put("id", r[0]);
            m.put("courseName", r[1]);
            m.put("day", r[2]);
            m.put("startTime", r[3]);
            m.put("endTime", r[4]);
            m.put("faculty", r[5]);
            result.add(m);
        }

        return result;
    }

    // ⭐ NEW: Faculty vs student analytics
    public List<Map<String,Object>> getFacultyStudentCounts(){
        List<Object[]> rows = repo.getFacultyStudentCounts();

        List<Map<String,Object>> result = new ArrayList<>();

        for(Object[] r : rows){
            Map<String,Object> m = new HashMap<>();
            m.put("faculty", r[0]);
            m.put("students", r[1]);
            result.add(m);
        }

        return result;
    }
}