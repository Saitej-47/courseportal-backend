package com.saitej.courseportal.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminAuthController {

    private static final String ADMIN_EMAIL = "admin@gmail.com";
    private static final String ADMIN_PASSWORD = "admin123";

    @PostMapping("/login")
    public Map<String, Object> adminLogin(@RequestBody Map<String, String> req) {

        String email = req.get("email");
        String password = req.get("password");

        Map<String, Object> res = new HashMap<>();

        if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password)) {
            res.put("success", true);
            res.put("message", "Admin login success");
        } else {
            res.put("success", false);
            res.put("message", "Invalid admin credentials");
        }

        return res;
    }
}