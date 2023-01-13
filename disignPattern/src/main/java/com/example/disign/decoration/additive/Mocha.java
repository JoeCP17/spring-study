package com.example.disign.decoration.additive;

import com.example.disign.decoration.Beverage;
import com.example.disign.decoration.CondimentDecorator;

public class Mocha extends CondimentDecorator {

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost() + .20;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",모카";
    }
}
