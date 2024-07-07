package com.hng.hngproject.controller;

import com.hng.hngproject.model.Organisation;
import com.hng.hngproject.model.User;
import com.hng.hngproject.service.OrganisationService;
import com.hng.hngproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/organisations")
public class OrganisationController {

    @Autowired
    private OrganisationService organisationService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getMyOrganisations() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email).orElse(null);
        if (user != null) {
            return ResponseEntity.ok(organisationService.findByUser(user));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/{orgId}")
    public ResponseEntity<?> getOrganisationById(@PathVariable UUID orgId) {
        Optional<Organisation> organisationOpt = organisationService.findById(orgId);
        if (organisationOpt.isPresent()) {
            return ResponseEntity.ok(organisationOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Organisation not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrganisation(@Valid @RequestBody Organisation organisation) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email).orElse(null);
        if (user != null) {
            organisation.getUsers().add(user);
            Organisation savedOrganisation = organisationService.save(organisation);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOrganisation);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/{orgId}/users")
    public ResponseEntity<?> addUserToOrganisation(@PathVariable UUID orgId, @RequestBody UUID userId) {
        Optional<Organisation> organisationOpt = organisationService.findById(orgId);
        Optional<User> userOpt = userService.findById(userId);
        if (organisationOpt.isPresent() && userOpt.isPresent()) {
            Organisation organisation = organisationOpt.get();
            User user = userOpt.get();
            organisation.getUsers().add(user);
            organisationService.save(organisation);
            return ResponseEntity.ok("User added to organisation successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Organisation or User not found");
        }
    }
}
