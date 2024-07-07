package com.hng.hngproject.service;

import com.hng.hngproject.dto.UserLoginDTO;
import com.hng.hngproject.dto.UserRegistrationDTO;
import com.hng.hngproject.model.User;
import com.hng.hngproject.repository.UserRepository;
import com.hng.hngproject.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(UUID userId) {
        return userRepository.findById(userId);
    }

    public User registerUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setPhone(userRegistrationDTO.getPhone());
        return userRepository.save(user);
    }

    public Optional<User> authenticateUser(UserLoginDTO userLoginDTO) {
        Optional<User> user = userRepository.findByEmail(userLoginDTO.getEmail());
        if (user.isPresent() && passwordEncoder.matches(userLoginDTO.getPassword(), user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    public String generateToken(User user) {
        return jwtTokenUtil.generateToken(user.getEmail());
    }
}
