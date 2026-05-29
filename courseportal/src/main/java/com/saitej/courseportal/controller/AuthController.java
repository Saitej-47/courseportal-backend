package com.saitej.courseportal.controller;

import com.saitej.courseportal.entity.Student;
import com.saitej.courseportal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"https://courseportal-frontend.vercel.app", "http://localhost:3000"})

public class AuthController {

    @Autowired
    private AuthService authService;

    // ✅ REGISTER
    @PostMapping("/register")
    public Student register(@RequestBody Student student){
        return authService.register(student);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public Student login(@RequestBody Map<String, String> request){

        String email = request.get("email");
        String password = request.get("password");

        return authService.login(email, password);
    }

    // 🔥 ENROLL COURSE
    @PostMapping("/enroll")
    public Student enrollCourse(
            @RequestParam Long studentId,
            @RequestParam Long courseId
    ){
        return authService.enrollCourse(studentId, courseId);
    }
}
