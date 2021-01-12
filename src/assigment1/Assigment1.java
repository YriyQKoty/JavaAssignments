package assigment1;

import java.util.*;

public class Assigment1 {
    //Варіант 1,
    // завдання # [ 7,12,28,10,27 ]
    public static void main(String[] args) {

        var assigment = new Assigment1();

        //Завдання №7
        System.out.println("Task 7: " + assigment.revertNumber(357));

        //Завдання №12
        int[] arr = new int[]{1, 4, 2, -1, 5, 6, 656, 213, 64};
        arr = assigment.insertionSort(arr);
        System.out.println("Task 12: " + Arrays.toString(arr));

        //Завдання №28
        System.out.println("Task 28: " + assigment.decToHex(112));

        //Завдання №10
        int[][] arrays = {{1, 2, 3, 5, -6, 5, 32, 6, 2}, {53, 5, 6, 75, 2, -26, 2, 67, 431, 17}};
        System.out.println("Task 10: " + assigment.findPrimes(arrays));

        //Завдання №27
        System.out.println("Task 27: " + assigment.decToOct(9));
    }

    //Завдання №7
    //Створіть метод , який може перевернути будь яку число int . Приклад - 357 на вході, метод поверне 753
    public int revertNumber(int number) {
        StringBuilder v = new StringBuilder(Integer.toString(number));
        return Integer.parseInt(v.reverse().toString());
    }

    //Завдання №12
    //Створіть метод, який сортує будь який масив int[] методом вставок.
    public int[] insertionSort(int[] array) {

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
        char sign = '0';
        if (number < 0) {
            sign = '1';
            number = Math.abs(number);
        }
        String result = "";
        char hexSystem[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

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
    public Set<Integer> findPrimes(int[][] arrayOfArrays) {
        Set<Integer> result = new HashSet<>();
        for (var array : arrayOfArrays) {
            for (int i = 0; i < array.length; i++) {
                if (isPrime(array[i])) {
                    result.add(array[i]);
                }
            }
        }

        return result;
    }

    public boolean isPrime(int num) {

        if (num <= 1) {
            return false;
        }        if (num <= 3) {
            return true;
        }

        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i < num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    //Завдання №27
    //Створіть метод, який приймає параметр int та конвертує його з десятичної у восьмирічну систему числення
    // та повертає у вигляді String. Приклад- приймає 9, повертає =011=
    public String decToOct(int number) {
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
