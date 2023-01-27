package com.example.disign.abstract_factory.store;

import com.example.disign.abstract_factory.pizzas.Pizza;

public abstract class PizzaStore {
    protected abstract Pizza createPizza(String item);

    public Pizza orderPizza(String type) {

        Pizza pizza = createPizza(type);

        pizza.prepare();

        pizza.bake();

        pizza.cut();

        pizza.box();

        return pizza;
    }
}
