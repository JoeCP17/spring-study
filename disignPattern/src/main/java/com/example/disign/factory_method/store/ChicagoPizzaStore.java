package com.example.disign.factory_method.store;

import com.example.disign.factory_method.*;

public class ChicagoPizzaStore extends PizzaStore {

    @Override
    Pizza createPizza(String item) {

        switch (item) {

            case "cheese":
                return new ChicagoStyleCheesePizza();
            case "veggie":
                return new ChicagoStyleVeggiePizza();
            case "clam":
                return new ChicagoStyleClamPizza();
            case "pepperoni":
                return new ChicagoStylePepperoniPizza();
            default:
                return null;
        }

    }
}
