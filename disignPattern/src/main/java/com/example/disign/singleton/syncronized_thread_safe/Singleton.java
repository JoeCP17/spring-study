package com.example.disign.singleton.syncronized_thread_safe;

public class Singleton {

    private static Singleton singleton;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (singleton == null) singleton = new Singleton();

        return singleton;
    }

    public String getDescription() {
        return "I'm a thread safe Singleton!";
    }
}
