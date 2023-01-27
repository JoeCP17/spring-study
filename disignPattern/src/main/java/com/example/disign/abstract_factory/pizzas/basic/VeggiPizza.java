package com.example.disign.abstract_factory.pizzas.basic;


import com.example.disign.abstract_factory.manual.PizzaIngredientFactory;
import com.example.disign.abstract_factory.pizzas.Pizza;
import com.example.disign.abstract_factory.topping.interfacetopping.*;

public class VeggiPizza extends Pizza {

    PizzaIngredientFactory ingredientFactory;

    public VeggiPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + getName());
        Dough dough = ingredientFactory.createDough();
        Sauce sauce = ingredientFactory.createSauce();
        Cheese cheese = ingredientFactory.createCheese();
        Veggies[] veggies = ingredientFactory.createVeggies();
        Pepperoni pepperoni = ingredientFactory.createPepperoni();
    }
}
