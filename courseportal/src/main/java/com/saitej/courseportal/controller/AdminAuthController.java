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

        // ✅ Debug: check if API is hit
        System.out.println("==== ADMIN LOGIN API HIT ====");

        // ✅ Safe fetch + trim
        String email = req.get("email") != null ? req.get("email").trim() : "";
        String password = req.get("password") != null ? req.get("password").trim() : "";

        System.out.println("Entered Email: [" + email + "]");
        System.out.println("Entered Password: [" + password + "]");

        Map<String, Object> res = new HashMap<>();

        // ✅ Validation
        if (email.isEmpty() || password.isEmpty()) {
            res.put("success", false);
            res.put("message", "Email or Password cannot be empty");
            return res;
        }

        // ✅ Actual login check
        if (ADMIN_EMAIL.equalsIgnoreCase(email) && ADMIN_PASSWORD.equals(password)) {
            res.put("success", true);
            res.put("message", "Admin login success");
        } else {
            res.put("success", false);
            res.put("message", "Invalid admin credentials");
        }

        return res;
    }
}