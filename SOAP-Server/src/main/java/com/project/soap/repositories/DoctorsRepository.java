package com.project.soap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.soap.models.Doctor;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctor,Long> { }
