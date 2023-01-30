package com.example.lock.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class EventTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Event event;

    public EventTicket(Event event) {
        this.event = event;
    }

    public EventTicket() {

    }
}
