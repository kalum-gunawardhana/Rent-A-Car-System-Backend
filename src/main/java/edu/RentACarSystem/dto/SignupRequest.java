package edu.RentACarSystem.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {
    private String username;
    private String email;
    private String password;
}