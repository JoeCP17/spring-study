package com.example.disign.abstract_factory.pizzas.basic;

import com.example.disign.abstract_factory.manual.PizzaIngredientFactory;
import com.example.disign.abstract_factory.pizzas.Pizza;
import com.example.disign.abstract_factory.topping.interfacetopping.Cheese;
import com.example.disign.abstract_factory.topping.interfacetopping.Clams;
import com.example.disign.abstract_factory.topping.interfacetopping.Dough;
import com.example.disign.abstract_factory.topping.interfacetopping.Sauce;

public class ClamPizza extends Pizza {

    PizzaIngredientFactory ingredientFactory;

    public ClamPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + getName());
        Dough dough = ingredientFactory.createDough();
        Sauce sauce = ingredientFactory.createSauce();
        Cheese cheese = ingredientFactory.createCheese();
        Clams clam = ingredientFactory.createClam();
    }
}
