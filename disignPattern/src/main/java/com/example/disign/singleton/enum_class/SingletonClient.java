package com.example.disign.singleton.enum_class;


public class SingletonClient {

    public static void main(String[] args) {
        Singleton uniqueInstance = Singleton.UNIQUE_INSTANCE;
        System.out.println(uniqueInstance.getDescription());
    }
}
