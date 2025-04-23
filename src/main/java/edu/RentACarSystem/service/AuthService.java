package edu.RentACarSystem.service;

import edu.RentACarSystem.dto.LoginRequest;
import edu.RentACarSystem.dto.SignupRequest;
import edu.RentACarSystem.dto.UserDto;

public interface AuthService {
    UserDto createCustomer(SignupRequest signupRequest);
    Boolean loginCustomer(LoginRequest loginRequest);
}
