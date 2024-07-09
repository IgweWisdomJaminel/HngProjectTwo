package com.hng.hngproject.repository;

import com.hng.hngproject.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, UUID> {
}
