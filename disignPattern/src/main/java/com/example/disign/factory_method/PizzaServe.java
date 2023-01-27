package com.example.disign.factory_method;

import com.example.disign.factory_method.store.ChicagoPizzaStore;
import com.example.disign.factory_method.store.NYPizzaStore;
import com.example.disign.factory_method.store.PizzaStore;

public class PizzaServe {
    public static void main(String[] args) {

        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        // cheese
        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("\nEthan ordered a " + pizza.getName() + "\n\n");

        pizza = chicagoStore.orderPizza("cheese");
        System.out.println("\nJoel ordered a " + pizza.getName() + "\n\n");

        // clam
        pizza = nyStore.orderPizza("clam");
        System.out.println("\nEthan ordered a " + pizza.getName() + "\n\n");

        pizza = chicagoStore.orderPizza("clam");
        System.out.println("\nJoel ordered a " + pizza.getName() + "\n\n");

        // pepperoni
        pizza = nyStore.orderPizza("pepperoni");
        System.out.println("\nEthan ordered a " + pizza.getName() + "\n\n");

        pizza = chicagoStore.orderPizza("pepperoni");
        System.out.println("\nJoel ordered a " + pizza.getName() + "\n\n");

        // veggie
        pizza = nyStore.orderPizza("veggie");
        System.out.println("\nEthan ordered a " + pizza.getName() + "\n\n");

        pizza = chicagoStore.orderPizza("veggie");
        System.out.println("\nJoel ordered a " + pizza.getName() + "\n\n");

    }
}
