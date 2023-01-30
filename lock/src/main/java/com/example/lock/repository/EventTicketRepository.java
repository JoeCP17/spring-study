package com.example.lock.repository;

import com.example.lock.entity.EventTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTicketRepository extends JpaRepository<EventTicket, Long> {
}
