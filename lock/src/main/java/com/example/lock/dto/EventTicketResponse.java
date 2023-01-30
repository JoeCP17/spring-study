package com.example.lock.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventTicketResponse {

    private Long id;
    private Long eventTicketId;

    public EventTicketResponse(Long id, Long eventTicketId) {
        this.id = id;
        this.eventTicketId = eventTicketId;
    }
}
