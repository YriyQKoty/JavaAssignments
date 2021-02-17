package _assigment1;

import java.sql.Array;
import java.util.*;

import static java.lang.Math.sqrt;

public class Assigment1 {
    //Варіант 1,
    // завдання # [ 7,12,28,10,27 ]
    public static void main(String[] args) {



        var assigment = new Assigment1();

        //Завдання №7
        System.out.println("Task 7: " + assigment.revertNumber(76673579));

        //Завдання №12
        try {
            var arr = assigment.insertionSort(new int[]{1, 4, 2, -1, 5, 6, 656, 213, 64});
            System.out.println("Task 12: " + Arrays.toString(arr));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        //Завдання №28
        System.out.println("Task 28: " + assigment.decToHex(-42));

        //Завдання №10
        int[][] arrays = {new int[]{1, 2, 3, 5, -6, 5, 32, 6, 2}, new int[]{53, 5, 6, 75, 2, -26, 2, 67, 431, 17}};
        System.out.println("Task 10: " + Arrays.toString(assigment.findPrimes(arrays)));

        //Завдання №27
        System.out.println("Task 27: " + assigment.decToOct(9));


        System.out.println(assigment.isPrime(2));
        System.out.println(assigment.isPrime(1));
        System.out.println(assigment.isPrime(-1));
        System.out.println(assigment.isPrime(11));


    }

    //Завдання №7
    //Створіть метод , який може перевернути будь яку число int . Приклад - 357 на вході, метод поверне 753
    public int revertNumber(int number) {
        char sign = '+';
        if (number < 0) {
            sign = '-';
            number = Math.abs(number);
        }
//TODO № 7 пропоную створити ще одну реалізацію бех реверсу , робити все за допомогою =молотка та цвяхів=
        var result = Integer.toString(number).toCharArray();
        char[] charArray = new char[result.length];

        for (int i = result.length - 1, j = 0; i >= 0; i--, j++) {
            charArray[j] = result[i];
        }

        return sign == '+' ? Integer.parseInt(String.valueOf(charArray)) : -Integer.parseInt(String.valueOf(charArray));
    }

    //Завдання №12
    //Створіть метод, який сортує будь який масив int[] методом вставок.
    public int[] insertionSort(int[] array) throws IllegalArgumentException {

        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null!");//TODO ви знаєте моє відношення до такого перехвата виключень.
            // або використовуйте checked виключення та обробляйте їх або у методі або транслюйте далі
        }

        for (int i = 0; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }

        return array;
    }

    //Завдання №28
    //Створіть метод, який приймає параметр int та конвертує
    // його з десятичної у шістнадцятирічну систему обчислення та повертає у вигляді String.
    // Приклад- приймає 11, повертає =0xb=
    public String decToHex(int number) {

        if (number == 0) {
            return "=0x0=";
        }

        char sign = '0';
        if (number < 0) {
            sign = '1';
            number = Math.abs(number);
        }
        String result = "";
        char[] hexSystem = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        while (number > 0) {
            int modulo = number % 16;
            result = hexSystem[modulo] + result;
            number /= 16;
        }
        return String.format("=%cx%s=", sign, result);
    }

    //Завдання №10
    //Створіть метод, який дозволяє поєднати між собою будь яку кількість масивів int[]
    // та повернути результуючий масив,
    // який буде складатися виключно з простих чисел, які були у складі масивів.
    public int[] findPrimes(int[][] arrayOfArrays) {

        if (arrayOfArrays == null) {

            throw new NullPointerException();

        }

        Set<Integer> set = new HashSet<>();
        for (var array : arrayOfArrays) {
            for (int j : array) {
                if (isPrime(j)) {
                    set.add(j);
                }
            }
        }

        int[] result = new int[set.size()];

        int index = 0;

        for (Integer i : set) {
            result[index++] = i;
        }

        return insertionSort(result);
    }


    public boolean isPrime(int n) {

        if (n < 2) {
            return false;
        }
        for (long i = 2; i <= sqrt(n); i++) {

            if (n % i == 0)  return false;
        }
        return true;
    }

//    public boolean isPrime(int num) {//TODO ця логіка має фрагментарний характер, зробіть алгоритм універсальнимм щоб він підходив для 0,1, 2, 3, 4,5...
//        // це зекономить зусилля колегам при роботі з вашим кодом
//
//        if (num <= 1) {
//            return false;
//        }
//        if (num <= 3) {
//            return true;
//        }
//
//        if (num % 2 == 0 || num % 3 == 0) {
//            return false;
//        }
//
//        for (int i = 5; i * i < num; i += 6) {
//            if (num % i == 0 || num % (i + 2) == 0) {
//                return false;
//            }
//        }
//
//        return true;
//    }


    //Завдання №27
    //Створіть метод, який приймає параметр int та конвертує його з десятичної у восьмирічну систему числення
    // та повертає у вигляді String. Приклад- приймає 9, повертає =011=
    public String decToOct(int number) {

        if (number == 0) {
            return "=0o0=";
        }

        char sign = '0';
        if (number < 0) {
            sign = '1';
            number = Math.abs(number);
        }
        String result = "";

        while (number > 0) {
            int modulo = number % 8;
            result = modulo + result;
            number /= 8;
        }
        return String.format("=%co%s=", sign, result);
    }

}
