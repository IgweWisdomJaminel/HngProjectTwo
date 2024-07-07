package com.hng.hngproject.controller;

import com.hng.hngproject.dto.UserLoginDTO;
import com.hng.hngproject.dto.UserRegistrationDTO;
import com.hng.hngproject.model.Organisation;
import com.hng.hngproject.model.User;
import com.hng.hngproject.repository.OrganisationRepository;
import com.hng.hngproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrganisationRepository organisationRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        Optional<User> existingUser = userService.findByEmail(userRegistrationDTO.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already in use");
        }
        User user = userService.registerUser(userRegistrationDTO);

        Organisation organisation = new Organisation();
        organisation.setName(user.getFirstName() + "'s Organisation");
        organisation.getUsers().add(user);
        organisationRepository.save(organisation);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        Optional<User> user = userService.authenticateUser(userLoginDTO);
        if (user.isPresent()) {
            String token = userService.generateToken(user.get());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}