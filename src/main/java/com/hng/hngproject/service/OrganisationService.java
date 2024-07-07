package com.hng.hngproject.service;

import com.hng.hngproject.model.Organisation;
import com.hng.hngproject.model.User;
import com.hng.hngproject.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrganisationService {

    @Autowired
    private OrganisationRepository organisationRepository;

    public List<Organisation> findByUser(User user) {
        return organisationRepository.findAll().stream()
                .filter(org -> org.getUsers().contains(user))
                .collect(Collectors.toList());
    }

    public Optional<Organisation> findById(UUID orgId) {
        return organisationRepository.findById(orgId);
    }

    public Organisation save(Organisation organisation) {
        return organisationRepository.save(organisation);
    }
}
