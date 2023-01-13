package com.example.disign.decoration.drink;

import com.example.disign.decoration.Beverage;

public class DarkRostCoffe extends Beverage {

    public DarkRostCoffe() {
        description = "다크 로스트 커피";
    }

    @Override
    public double cost() {
        return 99;
    }
}
