package edu.RentACarSystem.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {
    private String name;
    private String email;
    private String password;
}