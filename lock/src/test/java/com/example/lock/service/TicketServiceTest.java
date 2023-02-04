package com.example.lock.service;

import com.example.lock.entity.Ticket;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TicketServiceTest {

    @Autowired
    private TicketService ticketService;
    private String ticketKey;
    private Ticket ticket;

    @BeforeEach
    void settingTickets(){
        final String name = "IU-TICKET";
        final String keyId = "RedShoes";
        final int amount = 100;
        final Ticket iu_ticket = new Ticket(name, keyId, amount);

        this.ticketKey = ticketService.keyResolver(iu_ticket.getName(), iu_ticket.getKeyId());
        this.ticket = iu_ticket;
        ticketService.setTicket(this.ticketKey, amount);
    }

    @Test
    void checked_ticket() {
        final int amount = this.ticket.getAmount();

        final int currentCount = ticketService.currentTicket(ticketKey);
        System.out.println(currentCount);
        assertEquals(amount, currentCount);
    }

    @Test
    void decrease_ticket() {
        final int amount = this.ticket.getAmount();
        final int count = 1;

        ticketService.decrease(this.ticketKey, count);

        final int currentCount = ticketService.currentTicket(ticketKey);
        assertEquals(amount - count, currentCount);
    }

    @Test
    void not_used_lock() throws InterruptedException {
        final int people = 100;
        final int count = 1;
        final int soldOut = 0;
        final CountDownLatch countDownLatch = new CountDownLatch(people);

        List<Thread> workers = Stream
                .generate(() -> new Thread(new BuyNoLockWorker(this.ticketKey, count, countDownLatch)))
                .limit(people)
                .collect(Collectors.toList());
        workers.forEach(Thread::start);
        countDownLatch.await();

        final int currentCount = ticketService.currentTicket(ticketKey);
        assertNotEquals(soldOut, currentCount);
    }

    @Test
    void used_lock() throws InterruptedException {
        final int people = 200;
        final int count = 1;
        final int soldOut = 0;
        final CountDownLatch countDownLatch = new CountDownLatch(people);

        List<Thread> workers = Stream
                .generate(() -> new Thread(new BuyWorker(this.ticketKey, count, countDownLatch)))
                .limit(people)
                .collect(Collectors.toList());
        workers.forEach(Thread::start);
        countDownLatch.await();

        final int currentCount = ticketService.currentTicket(this.ticketKey);
        assertEquals(soldOut, currentCount);
    }

    private class BuyWorker implements Runnable{
        private final String ticketKey;
        private final int count;
        private final CountDownLatch countDownLatch;

        public BuyWorker(String ticketKey, int count, CountDownLatch countDownLatch) {
            this.ticketKey = ticketKey;
            this.count = count;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            ticketService.decrease(this.ticketKey, count);
            countDownLatch.countDown();
        }
    }

    private class BuyNoLockWorker implements Runnable{
        private final String ticketKey;
        private final int count;
        private final CountDownLatch countDownLatch;

        public BuyNoLockWorker(String ticketKey, int count, CountDownLatch countDownLatch) {
            this.ticketKey = ticketKey;
            this.count = count;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            ticketService.decreaseNoLock(this.ticketKey, count);
            countDownLatch.countDown();
        }
    }
}