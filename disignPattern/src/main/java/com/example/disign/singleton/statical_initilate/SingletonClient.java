package com.example.disign.singleton.statical_initilate;


public class SingletonClient {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance.getDescription());
    }
}
