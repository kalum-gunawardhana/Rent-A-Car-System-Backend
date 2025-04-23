package edu.RentACarSystem.controller;

import edu.RentACarSystem.dto.LoginRequest;
import edu.RentACarSystem.dto.SignupRequest;
import edu.RentACarSystem.dto.UserDto;
import edu.RentACarSystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> createCustomer(@RequestBody SignupRequest signupRequest){
        UserDto customer = authService.createCustomer(signupRequest);
        if (customer==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request!");
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody LoginRequest loginRequest){
        boolean isAuthenticated = authService.loginCustomer(loginRequest);

        if (isAuthenticated) {
            return ResponseEntity.ok(Map.of("message", "Login successful"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}