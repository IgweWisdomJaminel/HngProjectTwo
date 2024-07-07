package com.hng.hngproject.repository;

import com.hng.hngproject.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrganisationRepository extends JpaRepository<Organisation, UUID> {
}
