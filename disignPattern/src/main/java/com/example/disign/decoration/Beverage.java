package com.example.disign.decoration;


/*
    Beverage는 추상클래스이며 getDescription(), cost()라는 메서드를 가진다.
 */
public abstract class Beverage {
    public String description = "제목 없음";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
