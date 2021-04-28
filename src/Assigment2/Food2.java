package Assigment2;

import java.util.Date;

public class Food2 extends  Food{
    public Food2(String name, Producer producer, Date productionDate, float price, long expireDays) {
        super(name, producer, productionDate, price, expireDays);
    }

    public Food2(String name, Producer producer, Date productionDate, float price, long expireDays, FoodCategory category) {
        super(name, producer, productionDate, price, expireDays, category);
    }

    Food2() {
        super();
    };
}
