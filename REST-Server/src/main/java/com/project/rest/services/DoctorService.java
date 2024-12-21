package com.project.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.rest.models.Doctor;
import com.project.rest.repositories.DoctorsRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DoctorService {
    private final DoctorsRepository doctorsRepository;

    @Autowired
    public DoctorService(DoctorsRepository doctorsRepository) {
        this.doctorsRepository = doctorsRepository;
    }

    public Optional<Doctor> findById(Long id){
        return doctorsRepository.findById(id);
    }
}
