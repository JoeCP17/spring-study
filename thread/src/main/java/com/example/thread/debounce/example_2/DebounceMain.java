package com.example.thread.debounce.example_2;

import java.io.IOException;

public class DebounceMain {

    private static DebounceImpl debounce;

    public static void main(String[] args) throws IOException, InterruptedException {
        debounce = new DebounceImpl();
        search("H");
        search("HE");
        search("HEL");
        System.out.println("Waiting for user to finish typing");
        Thread.sleep(2000);
        search("HELL");
        search("HELLO");
    }


    private static void search(String searchPharse) {
        System.out.println("Search for : " + searchPharse);
        Debounce debounceCallback = () -> System.out.println("Now Executing search for : " + searchPharse);
        debounce.debounce(searchPharse, debounceCallback, 4000);
    }
}
