package com.example.disign.singleton.enum_class;

public enum Singleton {
    UNIQUE_INSTANCE;

    public String getDescription() {
        return "I'm a thread safe Singleton that used enum!";
    }
}
