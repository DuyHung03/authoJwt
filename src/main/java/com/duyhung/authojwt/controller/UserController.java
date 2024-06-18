package com.duyhung.authojwt.controller;

import com.duyhung.authojwt.entity.ApiResponse;
import com.duyhung.authojwt.entity.AuthRequest;
import com.duyhung.authojwt.entity.LoginResponse;
import com.duyhung.authojwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) throws Exception {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), null, userService.register(request));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) throws Exception {
        String res = userService.login(request);
        if (res == null)
            throw new UsernameNotFoundException("Not found");
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), null, new LoginResponse(res)));
    }

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("hello user");
    }

    @GetMapping("/admin")
    public ResponseEntity<?> admin() {
        return ResponseEntity.ok("hello admin");
    }


}
