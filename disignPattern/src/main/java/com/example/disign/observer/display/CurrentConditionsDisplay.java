package com.example.disign.observer.display;

import com.example.disign.observer.observer.Observer;
import com.example.disign.observer.weather.WeatherData;

public class CurrentConditionsDisplay implements Observer, DisplayElement{
    private float temperature;
    private float humidity;

    private WeatherData weatherData;
    public CurrentConditionsDisplay(WeatherData weatherData) {
        weatherData.registerObserver(this);
    }

    // getter 메서드를 통해 바로 땡겨오는 update 방식
    @Override
    public void pull_update() {
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
        System.out.println("현재 상태 : 온도" + temperature + "F, 습도 " + humidity + "%");
    }
}
