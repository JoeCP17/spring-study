package com.example.lock;

import com.example.lock.dto.EventResponse;
import com.example.lock.dto.EventTicketResponse;
import com.example.lock.entity.Event;
import com.example.lock.entity.EventTicket;
import com.example.lock.repository.EventRepository;
import com.example.lock.repository.EventTicketRepository;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final EventRepository eventRepository;
    private final EventTicketRepository eventTicketRepository;

    private final RedissonClient redissonClient;

    @Transactional
    public EventResponse createEvent(final Long ticketLimit) {
        Event savedEvent = eventRepository.save(new Event(ticketLimit));
        return new EventResponse(savedEvent.getId(), savedEvent.getTicketLimit());
    }

    @Transactional
    public EventTicketResponse createEventTicket(final Long eventId) {

        Event event = eventRepository.findById(eventId).orElseThrow();
        if(event.isClosed()) throw new RuntimeException("마감 되었습니다.");

        EventTicket savedEventTicket = eventTicketRepository.save(new EventTicket(event));
        return new EventTicketResponse(savedEventTicket.getId(), savedEventTicket.getEvent().getId());
    }

    @Transactional
    public EventTicketResponse createEventTicketByRedisson(final Long eventId) {
        RLock lock = redissonClient.getLock(String.valueOf(eventId)); // 네임드 락

        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);

            if(!available) throw new RuntimeException("Lock을 획득하지 못했습니다.");

            Event event = eventRepository.findById(eventId).orElseThrow();

            if(event.isClosed()) throw new RuntimeException("마감 되었습니다.");

            EventTicket savedEventTicket = eventTicketRepository.save(new EventTicket(event));
            return new EventTicketResponse(savedEventTicket.getId(), savedEventTicket.getEvent().getId());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}
