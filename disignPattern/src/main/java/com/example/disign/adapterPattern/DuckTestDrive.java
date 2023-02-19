package com.example.disign.adapterPattern;

import com.example.disign.adapterPattern.ducks.Duck;
import com.example.disign.adapterPattern.ducks.MallarDuck;
import com.example.disign.adapterPattern.turkeys.Turkey;
import com.example.disign.adapterPattern.turkeys.WildTurkey;

public class DuckTestDrive {

    public static void main(String[] args) {
        Duck duck = new MallarDuck();

        Turkey turkey = new WildTurkey();
        Duck turkyAdapter = new TurkyAdapter(turkey);

        System.out.println("The Turkey says ...");
        turkey.gobble();
        turkey.fly();

        System.out.println("\nThe Duck says...");
        testDuck(turkyAdapter);
    }


    static void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }
}
