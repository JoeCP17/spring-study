package com.example.disign.decoration;

import com.example.disign.decoration.additive.Mocha;
import com.example.disign.decoration.additive.SoyMilk;
import com.example.disign.decoration.additive.WhippedCream;
import com.example.disign.decoration.drink.DarkRostCoffe;
import com.example.disign.decoration.drink.Espresso;
import com.example.disign.decoration.drink.HouseBlend;

public class StartbuzzCoffe {

    public static void main(String[] args) {
        Beverage espresso = new Espresso();
        System.out.println(espresso.getDescription() + "$" + espresso.cost());

        Beverage darkRoast = new DarkRostCoffe();
        darkRoast = new Mocha(darkRoast);
        darkRoast = new Mocha(darkRoast);
        darkRoast = new Mocha(darkRoast);

        System.out.println(darkRoast.getDescription() + "$" + darkRoast.cost());

        Beverage houseBlend = new HouseBlend();
        houseBlend = new SoyMilk(houseBlend);
        houseBlend = new Mocha(houseBlend);
        houseBlend = new WhippedCream(houseBlend);
        System.out.println(houseBlend.getDescription() + "$" + houseBlend.cost());

    }
}
