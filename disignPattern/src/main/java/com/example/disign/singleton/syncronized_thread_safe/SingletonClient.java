package com.example.disign.singleton.syncronized_thread_safe;



public class SingletonClient {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance.getDescription());
    }
}
