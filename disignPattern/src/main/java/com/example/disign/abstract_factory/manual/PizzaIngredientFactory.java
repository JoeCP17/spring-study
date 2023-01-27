package com.example.disign.abstract_factory.manual;

import com.example.disign.abstract_factory.topping.interfacetopping.*;

public interface PizzaIngredientFactory {

    public Dough createDough();

    public Sauce createSauce();

    public Cheese createCheese();

    public Veggies[] createVeggies();

    public Pepperoni createPepperoni();

    public Clams createClam();
}
