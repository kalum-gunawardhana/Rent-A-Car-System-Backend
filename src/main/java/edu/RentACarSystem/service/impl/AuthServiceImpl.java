package edu.RentACarSystem.service.impl;

import edu.RentACarSystem.dto.LoginRequest;
import edu.RentACarSystem.dto.SignupRequest;
import edu.RentACarSystem.dto.UserDto;
import edu.RentACarSystem.entity.UserEntity;
import edu.RentACarSystem.enums.UserRole;
import edu.RentACarSystem.repository.UserDao;
import edu.RentACarSystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {

        UserEntity user = new UserEntity();
        user.setName(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setRole(UserRole.CUSTOMER);

        UserEntity userEntity = userDao.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setPassword(userEntity.getPassword());
        userDto.setRole(userEntity.getRole());

        return userDto;
    }

    @Override
    public Boolean loginCustomer(LoginRequest loginRequest) {
        Optional<UserEntity> optionalUser = userDao.findByEmail(loginRequest.getEmail());

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();

            // In production, use a password encoder like BCrypt instead of plain comparison
            return user.getPassword().equals(loginRequest.getPassword());
        }

        return false;
    }
}