package com.project.soap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.soap.models.Ticket;

@Repository
public interface TicketsRepository extends JpaRepository<Ticket, Long> { }
