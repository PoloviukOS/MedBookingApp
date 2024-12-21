package com.project.soap.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.soap.models.Doctor;
import com.project.soap.models.Ticket;
import com.project.soap.repositories.DoctorsRepository;
import com.project.soap.repositories.TicketsRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@Service
@Transactional(readOnly = true)
public class TicketService {
    private final TicketsRepository ticketsRepository;
    private final DoctorsRepository doctorsRepository;

    @Autowired
    public TicketService(TicketsRepository ticketsRepository, DoctorsRepository doctorsRepository) {
        this.ticketsRepository = ticketsRepository;
        this.doctorsRepository = doctorsRepository;
    }

    @Transactional
    public void createScheduleRequest(Long doctorId, LocalDate date, LocalTime startTime, int duration, int quantity) {
        Doctor doctor = doctorsRepository.findById(doctorId).get();
        LocalTime time = startTime;
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = new Ticket();
            ticket.setDoctor(doctor);
            ticket.setDate(date);
            ticket.setStartTime(time);
            ticket.setEndTime(time.plusMinutes(duration));
            ticketsRepository.save(ticket);
            time = time.plusMinutes(duration);
        }
    }
}
