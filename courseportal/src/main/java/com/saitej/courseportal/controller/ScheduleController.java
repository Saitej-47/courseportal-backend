package com.saitej.courseportal.controller;

import com.saitej.courseportal.entity.Schedule;
import com.saitej.courseportal.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://courseportal-frontend.vercel.app", "http://localhost:3000"})
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @PostMapping
    public Schedule add(@RequestBody Schedule s){
        return service.add(s);
    }

    // ✅ GET by studentId — frontend calls /schedule/{studentId}
    @GetMapping("/{studentId}")
    public List<Schedule> getByStudent(@PathVariable Long studentId){
        return service.getByStudent(studentId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}