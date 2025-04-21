package edu.RentACarSystem.dto;

import edu.RentACarSystem.enums.UserRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;

    private String name;

    private String email;

    private String password;

    private UserRole role; // "ADMIN" or "CUSTOMER"
}