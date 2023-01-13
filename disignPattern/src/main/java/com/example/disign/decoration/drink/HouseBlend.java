package com.example.disign.decoration.drink;

import com.example.disign.decoration.Beverage;

public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "하우스 블랜드 커피";
    }

    @Override
    public double cost() {
        return .89;
    }
}
