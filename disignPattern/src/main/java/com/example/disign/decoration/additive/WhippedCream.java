package com.example.disign.decoration.additive;

import com.example.disign.decoration.Beverage;
import com.example.disign.decoration.CondimentDecorator;

public class WhippedCream extends CondimentDecorator {
    public WhippedCream(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost() + .10;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",휘핑크림";
    }
}
