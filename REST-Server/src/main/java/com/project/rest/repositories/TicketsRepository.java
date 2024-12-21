package com.project.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.rest.models.Ticket;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketsRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE t.doctor.id = :doctorId AND t.date = :date AND t.patient IS NULL")
    List<Ticket> findAvailableSlotsByDoctorAndDate(@Param("doctorId") Long doctorId, @Param("date") LocalDate date);

    @Query("SELECT t FROM Ticket t WHERE t.patient.id = :patientId")
    List<Ticket> findAllTicketsByPatientId(@Param("patientId") Long patientId);
}
