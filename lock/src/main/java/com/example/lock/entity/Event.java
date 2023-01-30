package com.example.lock.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ticketLimit;

    @OneToMany(mappedBy = "event")
    private List<EventTicket> eventTickets;

    public Event(Long ticketLimit) {
        this.ticketLimit = ticketLimit;
    }

    public Event() {
    }

    public boolean isClosed() {
        return eventTickets.size() >= ticketLimit;
    }

}
