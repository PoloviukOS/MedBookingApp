package com.project.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.rest.models.Patient;
import com.project.rest.repositories.PatientsRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PatientService {
    private final PatientsRepository patientsRepository;

    @Autowired
    public PatientService(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    public Optional<Patient> findById(Long id){
        return patientsRepository.findById(id);
    }
}
