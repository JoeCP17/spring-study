package com.example.disign.abstract_factory.store;

import com.example.disign.abstract_factory.manual.NYPizzaFactory;
import com.example.disign.abstract_factory.manual.PizzaIngredientFactory;
import com.example.disign.abstract_factory.pizzas.Pizza;
import com.example.disign.abstract_factory.pizzas.basic.CheesePizza;
import com.example.disign.abstract_factory.pizzas.basic.ClamPizza;
import com.example.disign.abstract_factory.pizzas.basic.PepperoniPizza;
import com.example.disign.abstract_factory.pizzas.basic.VeggiPizza;

public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String item) {
            Pizza pizza = null;
            PizzaIngredientFactory ingredientFactory = new NYPizzaFactory();

            switch (item) {
                case "cheese":
                    pizza = new CheesePizza(ingredientFactory);
                    pizza.setName("Chicago Style Cheese Pizza");
                    break;

                case "veggie":
                    pizza = new VeggiPizza(ingredientFactory);
                    pizza.setName("Chicago Style Veggie Pizza");
                    break;

                case "clam":
                    pizza = new ClamPizza(ingredientFactory);
                    pizza.setName("Chicago Style Clam Pizza");
                    break;

                case "pepperoni":
                    pizza = new PepperoniPizza(ingredientFactory);
                    pizza.setName("Chicago Style Pepperoni Pizza");
                    break;
            }
            return pizza;
        }

}
