package com.example.disign.adapterPattern;

import com.example.disign.adapterPattern.ducks.Duck;
import com.example.disign.adapterPattern.turkeys.Turkey;

public class TurkyAdapter implements Duck {

    Turkey turkey;

    public TurkyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
