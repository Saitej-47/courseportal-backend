package com.saitej.courseportal.service;

import com.saitej.courseportal.entity.Schedule;
import com.saitej.courseportal.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository repo;

    public Schedule add(Schedule s) { return repo.save(s); }

    // ✅ get all schedules for a specific student
    public List<Schedule> getByStudent(Long studentId) {
        return repo.findByStudentId(studentId);
    }

    public void delete(Long id) { repo.deleteById(id); }
}