package com.hng.hngproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Data
@Entity
public class Organisation {

    @Id
    @GeneratedValue
    private UUID orgId;

    @NotNull
    private String name;

    private String description;

    @ManyToMany(mappedBy = "organisations")
    private Set<User> users = new HashSet<>();


}
