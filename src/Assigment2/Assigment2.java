package Assigment2;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Date;

interface Func<T> {
    boolean Filter(T param);
}


public class Assigment2 {

    public static void main(String[] args) {
        var assigment2 = new Assigment2();

        assigment2.Task45();

        System.out.print("\nTask31:Filter 1 result:  ");
        assigment2.Task31(new int[]{12, 3, 5, 6}, param -> param % 2 == 0);
        System.out.print("\nTask31: Filter 2 result: ");
        assigment2.Task31(new int[]{12, 3, 5, 6, -1 ,4, -1, -6,7,4,1,53}, param -> param < 0);

        System.out.print("\nTask34: days in month: " + assigment2.Task34(Month.January));
        System.out.print("\nTask34: days in month: " + assigment2.Task34(Month.February));
        System.out.print("\nTask34: days in month: " + assigment2.Task34(Month.April));
    }

   // 31,45,34,41,49


//
//    Створіть метод який дозволяє фільтрувати елементи будь якого масиву int та виводити ці елементи на консоль.
//    Умови фільтрації повинні передаватися в метод як параметр. Використовуйте функціональні інтерфейси та анонимні класи.

    void Task31 (int[] arr, Func<Integer> predicate) {
        for (int a: arr) {
            if (predicate.Filter(a)) {
                System.out.print(a + " ");
            }
        }
    }

    enum Month {
        January,
        February,
        March,
        April,
        May,
        June,
        July,
        August,
        September,
        October,
        November,
        December,
    }

    //Создайте метод, который принимает параметр- значение перечисления для месяца, а возвращает кол-во дней в месяце. Февраль всегда -28
    int Task34(Month month) {
        switch (month) {
            case January, July, August, October, December, May, March -> {
                return 31;
            }
            case February -> {
                return 28;
            }
            case April, June, September, November -> {
                return 30;
            }
            default -> {
                throw  new IllegalArgumentException("No month was set!");
            }

        }
    }


    void Task41() {

    }

    void Task49() {

    }


    //    Створіть просту  ієрархію спадкування, яка відповідає сутностям Товар
//    (властивості - назва, виробник, дата виготовлення, ціна) та Продукти харчування (термін придатності, харчова група
//     (молочні, м'ясні, бакалея та інше)), РадіоЕлектроніка (вид електроніки, гарантійний термін).
//      Продемонструйте перевантаження конструкторів та методів, перевизначення методів, особливості роботи конструкторів при спадкуванні.
    void Task45() {
        Producer producer1 = new Producer();
        producer1.name = "Product producer";
        Food product1 = new Food("new Product", producer1, new Date() , 100f, 4);
        System.out.println("Expire date: " + product1.getExpireDate());
        System.out.println("Current date: " + new Date());
        System.out.println("Expired:" + product1.checkIfExpired());
        System.out.println(product1 + "\n");

        Producer producer2 = new Producer();
        producer2.name = "Electronics producer";
        Electronics product2 = new Electronics("new Electonics", producer2, new Date() , 100f, 180);
        System.out.println("Expire date: " + product2.getExpireDate());
        System.out.println("Current date: " + new Date());
        System.out.println("Expired:" + product2.checkIfExpired());
        System.out.println(product2 + "\n");


        Producer producer3 = new Producer();
        producer3.name = "Electronics producer";
        Electronics product3 = new Electronics("new Resistor", producer3, new Date() , 10000f, 366, ElectronicsCategory.Resistors);
        System.out.println("Expire date: " + product3.getExpireDate());
        System.out.println("Current date: " + new Date());
        System.out.println("Expired:" + product3.checkIfExpired());
        System.out.println(product3);
    }
}


class Producer {
    String name;

    @Override
    public String toString() {
        return "Producer{" +
                "name='" + name + '\'' +
                '}';
    }

}

abstract class Good {
    String name;
    Producer producer;
    Date productionDate;
    float price;
    long expireDays;

    Good (String name, Producer producer, Date productionDate, float price, long expireDays) {
        this.name = name;
        this.producer = producer;
        this.productionDate = productionDate;
        this.price = price;
        this.expireDays = expireDays;
    }

    public Date getExpireDate () { return new Date(productionDate.getTime() + expireDays * 86400 * 1000);}

    public boolean checkIfExpired() {
        return getExpireDate().getTime() -  new Date().getTime() < 0;
    }
}

enum FoodCategory {
    Dairy,
    Meat,
    Groceries,
    None
}

enum ElectronicsCategory {
    Schemes,
    Resistors,
    Transistors,
    None,
}


class Food extends Good {


   public FoodCategory category = FoodCategory.None;


   public Food (String name, Producer producer, Date productionDate, float price, long expireDays) {

       super(name, producer, productionDate, price, expireDays);


       this.expireDays = expireDays;
    }

    public Food (String name, Producer producer, Date productionDate, float price, long expireDays, FoodCategory category)  {
        this(name, producer,productionDate, price, expireDays);

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


class Electronics extends Good {

    ElectronicsCategory category = ElectronicsCategory.None;

    public Electronics(String name, Producer producer, Date productionDate,float price, long warrantyTerm ) {
        super(name, producer, productionDate, price, warrantyTerm);
    }

    public Electronics(String name, Producer producer, Date productionDate, float price,long warrantyTerm, ElectronicsCategory category ) {
        this(name, producer, productionDate, price, warrantyTerm);
        this.category = category;
    }


    @Override
    public String toString() {
        return "Electronics{" +
                "name='" + name + '\'' +
                ", producer=" + producer +
                ", productionDate=" + productionDate +
                ", price=" + price +
                ", warrantyTerm='" + expireDays + '\'' +
                ", category=" + category +
                '}';
    }
}
