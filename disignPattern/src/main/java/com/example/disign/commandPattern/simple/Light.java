package com.example.disign.commandPattern.simple;

public class Light {
    String light;

    public Light() {}
    public Light(String light) {
        this.light = light;
    }

    public static void on() {
        System.out.println("불이 켜졌습니다.");
    }

    public static void off() {
        System.out.println("불이 꺼졌습니다.");
    }
}
