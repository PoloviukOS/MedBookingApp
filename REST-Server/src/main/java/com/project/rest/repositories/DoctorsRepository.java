package com.project.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.rest.models.Doctor;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctor,Long> { }
