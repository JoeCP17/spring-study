package com.example.lock.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventResponse {

    private Long id;
    private Long tiketLimit;

    public EventResponse(Long id, Long tiketLimit) {
        this.id = id;
        this.tiketLimit = tiketLimit;
    }
}
