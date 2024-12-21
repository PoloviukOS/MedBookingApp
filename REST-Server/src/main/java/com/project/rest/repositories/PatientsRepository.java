package com.project.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.rest.models.Patient;

@Repository
public interface PatientsRepository extends JpaRepository<Patient, Long> { }
