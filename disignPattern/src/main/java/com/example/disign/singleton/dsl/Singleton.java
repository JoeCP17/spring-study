package com.example.disign.singleton.dsl;

public class Singleton {

    private volatile static Singleton singleton;

    private Singleton() {}

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if(singleton == null)
                    singleton = new Singleton();
            }
        }
        return singleton;
    }

    public String getDescription() {
        return "I'm a Singleton that used DCL!";
    }
}
