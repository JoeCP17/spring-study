package com.example.disign.singleton.normal;

public class ChocolateController {

    public static void main(String[] args) {

        ChocolateBoiler instance = ChocolateBoiler.getInstance();

        instance.fill();
        instance.boil();
        instance.drain();

    }
}
