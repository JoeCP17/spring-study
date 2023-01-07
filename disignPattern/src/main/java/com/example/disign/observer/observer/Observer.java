package com.example.disign.observer.observer;

public interface Observer {

    void pull_update();

    void update(float temp, float humidity, float pressure);
}
