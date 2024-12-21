package com.project.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.rest.exception.DoctorNotFoundException;
import com.project.rest.exception.PatientNotFoundException;
import com.project.rest.exception.TicketNotFoundException;
import com.project.rest.models.Ticket;
import com.project.rest.services.DoctorService;
import com.project.rest.services.PatientService;
import com.project.rest.services.TicketService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/ticket")
@Tag(
    name = "Supervisor of obtaining and booking available doctor's appointments",
    description = "Obtaining free vouchers, taking up a doctor's appointment"
)
public class TicketController {
    private final TicketService ticketService;
    private final DoctorService doctorService;
    private final PatientService patienService;

    @Autowired
    public TicketController(TicketService ticketService, DoctorService doctorService, PatientService patienService) {
        this.ticketService = ticketService;
        this.doctorService = doctorService;
        this.patienService = patienService;
    }

    @Operation(
        summary = "Obtaining available slots by physician ID and date",
        description = "The method takes in the id of the doctor and the date. Returns the list of available tickets"
    )
    @GetMapping("/available")
    public ResponseEntity<List<Ticket>> findAvailableSlotsByDoctorAndDate(@RequestParam Long doctorId, @RequestParam LocalDate date) {
        if (doctorService.findById(doctorId).isEmpty()) {
            try {
                throw new DoctorNotFoundException("Doctor with ID: " + doctorId + " not found");
            } catch (DoctorNotFoundException e) {
                log.info(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        List<Ticket> tickets = ticketService.findAvailableSlotsByDoctorAndDate(doctorId, date);
        if (tickets.isEmpty()) {
            try {
                throw new TicketNotFoundException("No tickets available, please check the correctness of the input data");
            } catch (TicketNotFoundException e) {
                log.info(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return ResponseEntity.ok(tickets);
    }

    @Operation(
        summary = "Patient registration",
        description = "The method takes in the id of the ticket and the id of the patient. Returns the registered ticket to the doctor"
    )
    @PatchMapping("/register/{ticketId}")
    public Ticket registerPatientTicket(@PathVariable Long ticketId, @RequestParam Long patientId) {
        return ticketService.registerPatientTicket(ticketId, patientId);
    }

    @Operation(
        summary = "Receive all tickets by patient ID",
        description = "The method takes in the id of the patient. Returns the list of tickets occupied by him"
    )
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Ticket>> findAllTicketsByPatientId(@PathVariable Long patientId) {
        if (patienService.findById(patientId).isEmpty()) {
            try {
                throw new PatientNotFoundException("Patient with ID: " + patientId + " not found");
            } catch (PatientNotFoundException e) {
                log.info(e.getMessage());
                throw new RuntimeException(e);
            }
        }

        List<Ticket> tickets = ticketService.findAllTicketsByPatientId(patientId);
        if (tickets.isEmpty()) {
            try {
                throw new TicketNotFoundException("Ticket not found");
            } catch (TicketNotFoundException e) {
                log.info(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return ResponseEntity.ok(tickets);
    }
}
