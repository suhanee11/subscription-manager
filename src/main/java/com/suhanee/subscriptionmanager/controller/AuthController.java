// AuthController.java

package com.suhanee.subscriptionmanager.controller;

import com.suhanee.subscriptionmanager.entity.UserEntity;
import com.suhanee.subscriptionmanager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public String signup(
            @RequestBody UserEntity user
    ) {

        return authService.signup(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody UserEntity user
    ) {

        return authService.login(user);
    }
}
