package Assigment2;

import Assigment2.modificators.Vehicle;

import java.util.Date;
import java.util.function.Function;


public class Assigment2 {

    public static void main(String[] args) {
        var assigment2 = new Assigment2();
//        System.out.print("\n///////////////////////////Task 49/////////////////////");
//        assigment2.Modificators();
        System.out.print("\n///////////////////////////Task 41/////////////////////");
        assigment2.Exceptions();
        System.out.print("\n///////////////////////////Task 45/////////////////////");
        assigment2.Task45();

        System.out.print("\n//////////////////Task31:Filter 1 result:  ");
        assigment2.FilterArray(new int[]{12, 3, 5, 6}, new Func<Integer>() {
            @Override
            public boolean Filter(Integer param) {
                return param % 2 == 0;
            }
        });
        System.out.print("\n/////////////////Task31: Filter 2 result: ");
        assigment2.FilterArray(new int[]{12, 3, 5, 6, -1 ,4, -1, -6,7,4,1,53}, param -> param < 0);
        System.out.print("\n/////////////////Task31: Filter 3 result: ");
        assigment2.FilterArrayAnotherSample(new int[]{2,4,6,7,8,15,61,6,-15}, param -> param % 3 == 1);

        System.out.print("\n////////Task34: days in month: " + assigment2.GetDaysInMonth(Month.January));
        System.out.print("\n///////////Task34: days in month: " + assigment2.GetDaysInMonth(Month.February));
        System.out.print("\n///////////Task34: days in month: " + assigment2.GetDaysInMonth(Month.April));
    }

   // 31,45,34,41,49


//Task31
//    Створіть метод який дозволяє фільтрувати елементи будь якого масиву int та виводити ці елементи на консоль.
//    Умови фільтрації повинні передаватися в метод як параметр. Використовуйте функціональні інтерфейси та анонимні класи.

    void FilterArray (int[] arr, Func<Integer> predicate) {
        for (int a: arr) {
            if (predicate.Filter(a)) {
                System.out.print("   " + a);
            }
        }
    }

    void FilterArrayAnotherSample (int[] arr, FuncForInteger predicate) {
        for (int a: arr) {
            if (predicate.Filter(a)) {
                System.out.print("   " + a);
            }
        }
    }


    //Task34
    //Создайте метод, который принимает параметр- значение перечисления для месяца, а возвращает кол-во дней в месяце. Февраль всегда -28
    int GetDaysInMonth(Month month) {
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
//Task41
//Створіть код, у якому генеруються слідуючі типи виключень 
// ArrayIndexOutOfBoundsException, ArithmeticException, NullPointerException, IndexOutOfBoundsException, Exception. 
// Продемонструйте відмінність між =checked=  та  =unchecked= виключеннями.
    void Exceptions() {

        var array = new int[] {1,2,4,6,7};

        try {
            System.out.println(array[array.length + 10]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\nArrayIndexOutOfBoundsException handled");
        }

        String str = null;

        try {
            str.length();
        }
        catch (NullPointerException e) {
            System.out.println("NullPointerException handled");
        }

        int a = 5;

        try {
           a = a / 0;
        }
        catch (ArithmeticException e) {
            System.out.println("ArithmeticException handled");
        }

        String str1 = "abcd";

        try {
            System.out.println(str1.charAt(6));
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException handled");
        }

        try {
            var b = Integer.parseInt("ffsd")/2;
        }
        catch (Exception e) {
            System.out.println("Exception handled");
        }

        //checked exceptions - винятки, які контролюються системою -> SocketException <- IOException -> FileNotFoundException
        //unchecked -> RuntimeExceptions - усі, які можуть зустрітись в Runtime, вони не перехоплюються автоматично,
        //аби не сповільнювати систему, таким чином відповідальність за винятки перекладається на програміста

    }

    //Task49
   // Створіть тестовий приклад, який демонструє відмінності при використанні різних модифікаторів доступу.
    void Modificators() {
        //приватний - лише в класі
        //protected - в пакеті, класі та нащадках
        //package (default) - в пакеті
        //public - всюди

        //Демонстрація public-private-package членів
        var vehicle = new Vehicle();

        System.out.println(vehicle.getName());
//        vehicle.setVelocity(2); //setVelocity(int)' has protected access in 'Assigment2.modificators.Vehicle'
//        System.out.println(vehicle.velocity);//velocity' is not public in 'Assigment2.modificators.Vehicle'. Cannot be accessed from outside package
//        System.out.println(vehicle.name);

        var food2 = new Food2();
        food2.Default();

        //демонстрація protected члена
        var electronics = new Electronics();
        System.out.println(electronics.category);
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






