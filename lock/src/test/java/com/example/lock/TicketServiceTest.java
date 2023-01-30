package com.example.lock;

import com.example.lock.entity.Event;
import com.example.lock.entity.EventTicket;
import com.example.lock.repository.EventRepository;
import com.example.lock.repository.EventTicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TicketServiceTest {

    EventTicketRepository eventTicketRepository;

    EventRepository eventRepository;

    @BeforeEach
    void 티켓_세팅() {
        Event event = new Event(100L);
        EventTicket eventTicket = new EventTicket(event);

        eventRepository.save(event);
        eventTicketRepository.save(eventTicket);
    }

    @Test
    @DisplayName("분산락 적용하지 않았을 때")
    void createEventTicket() {
    }

    @Test
    @DisplayName("Redisson 분산락을 적용했을 때")
    void createEventTicketByRedisson() {
    }
}