package com.hng.hngproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private UUID userId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    private String phone;

    @ManyToMany
    @JoinTable(
            name = "user_organisation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "organisation_id")
    )
    private Set<Organisation> organisations = new HashSet<>();


}