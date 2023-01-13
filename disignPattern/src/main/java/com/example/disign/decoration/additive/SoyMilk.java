package com.example.disign.decoration.additive;

import com.example.disign.decoration.Beverage;
import com.example.disign.decoration.CondimentDecorator;

public class SoyMilk extends CondimentDecorator {

    public SoyMilk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return  beverage.cost() + .15;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",두유";
    }
}
