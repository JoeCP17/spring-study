package com.example.lock.entity;

public class Ticket {
    private String name;
    private String keyId;
    private int amount;

    public Ticket(String name, String keyId, int amount) {
        this.name = name;
        this.keyId = keyId;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getKeyId() {
        return keyId;
    }

    public int getAmount() {
        return amount;
    }
}
