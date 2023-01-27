package com.example.disign.decoration;

/*
    Beverage 객체가 들어갈 자리에 들어갈 수 있어야 하기에, Beverage 클래스를 확장한다.
 */
public abstract class CondimentDecorator extends Beverage {

    //각 데코레이터가 감쌀 음료를 나타내는 Beverage 객체를 여기에 저장한다.
    public Beverage beverage;

    public abstract String getDescription();
}
