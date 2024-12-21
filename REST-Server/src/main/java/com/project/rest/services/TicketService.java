package com.project.rest.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.project.rest.models.Patient;
import com.project.rest.models.Ticket;
import com.project.rest.repositories.PatientsRepository;
import com.project.rest.repositories.TicketsRepository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class TicketService {
    private final TicketsRepository ticketsRepository;
    private final PatientsRepository patientsRepository;

    @Autowired
    public TicketService(TicketsRepository ticketsRepository, PatientsRepository patientsRepository) {
        this.ticketsRepository = ticketsRepository;
        this.patientsRepository = patientsRepository;
    }

    public List<Ticket> findAvailableSlotsByDoctorAndDate(Long doctorId, LocalDate localDate) {
        return ticketsRepository.findAvailableSlotsByDoctorAndDate(doctorId, localDate);
    }

    @Transactional
    public Ticket registerPatientTicket(Long ticketId, Long patientId) {
        Ticket ticket = ticketsRepository.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("Ticket not found with id: " + ticketId));
        if (ticket.getPatient() != null) throw new IllegalStateException("Ticket is already occupied");
        Patient patient = patientsRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
        ticket.setPatient(patient);
        return ticketsRepository.save(ticket);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    public List<Ticket> findAllTicketsByPatientId(Long patientId) {
        return ticketsRepository.findAllTicketsByPatientId(patientId);
    }
}
