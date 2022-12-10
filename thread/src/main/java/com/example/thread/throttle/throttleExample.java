package com.example.thread.throttle;

import com.google.common.util.concurrent.RateLimiter;

public class throttleExample {
    private static int count = 1;
    private static RateLimiter rateLimiter;
    public static void main(String[] args) {

        // if you are allowing 500 requests per second
        rateLimiter = RateLimiter.create(5);

        long start = System.currentTimeMillis();

        while(true) {
            if (System.currentTimeMillis() - start > 4990) break;
            throttleMethod();
        }
    }
    public static void throttleMethod() {
        if (rateLimiter.tryAcquire()) {
            System.out.println(count++);
        }
    }

    /*
        acquire : 몇개의 인자를 획득할것인지를 의미
        tryAcquire : 할당한 개수 중 현재 전달받은 인자를 의미
     */
}
