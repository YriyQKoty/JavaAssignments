package Assigment2;

import java.util.Date;

class Food extends Good {


    public FoodCategory category = FoodCategory.None;


    public Food(String name, Producer producer, Date productionDate, float price, long expireDays) {

        super(name, producer, productionDate, price, expireDays);


        this.expireDays = expireDays;
    }

    public Food(String name, Producer producer, Date productionDate, float price, long expireDays, FoodCategory category) {
        this(name, producer, productionDate, price, expireDays);

        this.category = category;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", producer=" + producer +
                ", productionDate=" + productionDate +
                ", price=" + price +
                ", expireDate=" + expireDays +
                ", category=" + category +
                '}';
    }
}
