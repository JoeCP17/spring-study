package com.example.lock.service;

import com.example.lock.property.TicketProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {

    private final RedissonClient redissonClient;
    private final TicketProperty ticketProperty;
    private static final int EMPTY = 0;


    public String keyResolver(String domain, String keyId) {
        final String prefix = ticketProperty.getPrefix()+":"+domain+":%s";
        return String.format(prefix, keyId);
    }

    public void decrease(final String key, final int count) {
        final String lockName = key + ":lock";
        final RLock lock = redissonClient.getLock(lockName);
        final String worker = Thread.currentThread().getName();

        try {
            if(!lock.tryLock(1,3, TimeUnit.SECONDS)) return;

            final int ticket = currentTicket(key);

            if (ticket <= EMPTY) {
                log.info("[{}] 현재 남은 티켓이 없습니다. & 현재 남은 티켓 : {}개", worker, ticket);
                return;
            }
            log.info("현재 진행중인 사람 : {} & 현재 남은 티켓 개수 : {}개", worker, ticket);
            setTicket(key, ticket - count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock != null && lock.isLocked()) {
                lock.unlock();
            }
        }
    }

    public void decreaseNoLock(final String key, final int count){
        final String worker = Thread.currentThread().getName();
        final int ticket = currentTicket(key);
        log.info("[{}] 현재 남은 티켓 : {}", worker, currentTicket(key));

        if(ticket <= EMPTY){
            log.info("[{}] 현재 남은 티켓이 없습니다. ({}개)", worker, ticket);
            return;
        }

        log.info("현재 진행중인 사람 : {} & 현재 남은 티켓 : {}개", worker, ticket);
        setTicket(key, ticket - count);
    }


    public void setTicket(String key, int amount){
        redissonClient.getBucket(key).set(amount);
    }

    public int currentTicket(String key){
        return (int) redissonClient.getBucket(key).get();
    }
}
