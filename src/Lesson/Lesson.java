package Lesson;

import org.junit.jupiter.api.MethodOrderer;

import javax.management.Query;
import java.util.*;

public class Lesson {

    public static void main(String[] args) {
        var array = sortAndRemoveDuplicates(new Integer[]{-1,55,56,57,9,11,1,4,5,3,2,6,4,23,235,77,246,-5,-2,0,54,35,38,59,60,61,62,49,50,12,-12,554,-11,-10});

        var mapped = getAllChains(array);
        //виводимо усі послідовності
        for (var arr: mapped.entrySet()) {
            System.out.println();
            System.out.print(arr.getKey() + ": " + Arrays.toString(arr.getValue()));
        }

        //виводимо найдовшу послідовність
        System.out.println("\n\nLongest chaih: " + Arrays.toString(findMaxChain(mapped)));

    }

    //метод знаходит максимально довгу послідовність
    static Integer[] findMaxChain(Map<String,Integer[]> map) {
        var maxLength = 0;
        var keyOfLongestChain = "";
        for(Map.Entry<String,Integer[]> entry : map.entrySet()) {
            if(entry.getValue().length>maxLength) {
                maxLength=entry.getValue().length;
                keyOfLongestChain=entry.getKey();
            }
        }

        return map.get(keyOfLongestChain);
    }

    //метод повертає Map усіх послідовностей
    static Map<String,Integer[]> getAllChains(Integer[] allChainsArray) {

        Map<String,Integer[]> map = new HashMap<>();

        //виводимо
        System.out.println("Only chains in one array: " + Arrays.toString(allChainsArray));

        //початок послідовності
        var headIndex = 0;
        //кінець послідовності
        var tailIndex = 0;
        boolean shouldMapArray = false;
        //визначаємо межі послідовностей та записуємо їх у Map
        for (int i = 0; i < allChainsArray.length - 1; i++) {
            //якщо наступне значення більше за поточне на 1 - збільшуємо значення хвоста
            if(allChainsArray[i+1] == allChainsArray[i] + 1){
                tailIndex = i + 1;

                //якщо масив закінчився - хвіст == розміру масива
                if (i + 1 == allChainsArray.length - 1 ) {
                    tailIndex = allChainsArray.length;
                    shouldMapArray = true;
                }
            }
            else if (i != 0) {
                //якщо поточний елемент на 1 більше за попердній - збільшуємо хвіст
                if (allChainsArray[i] == allChainsArray[i - 1] + 1) {
                    tailIndex = i + 1 ;
                }

                shouldMapArray = true;
            }
            if (shouldMapArray) {
                //додаємо в словник за унікальним ключем визначену послідовність
                map.put("Array " + UUID.randomUUID(), Arrays.copyOfRange(allChainsArray, headIndex, tailIndex));
                headIndex = tailIndex;
                shouldMapArray = false;
            }

        }

        return map;
    }

    //метод видаляє дублікати та сортує масив
    static Integer[] sortAndRemoveDuplicates(Integer[] arr) {

        System.out.println("Initial array: " + Arrays.toString(arr));

        List<Integer> allChains = new ArrayList<>();

        Set<Integer> set = new HashSet<>(Arrays.asList(arr));

        Integer[] array = set.toArray(new Integer[0]);

        //сортуємо insertion sort
        for (int i = 0; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }

        //виводимо
        System.out.println("Sorted array: " + Arrays.toString(array));

        //вибираємо усі послідовності
        for (int i = 0; i < array.length - 1; i++) {
            //якщо наступний елемент на одиницю більший
            if (array[i + 1] == array[i] + 1)
            {
                allChains.add(array[i]);
            }
            else if (i != 0) { //інакше, якщо не початок масиву

                //якщо наступний більший за поточний ніж на одиницю
                if (array[i + 1] != array[i] + 1) {
                    //якщо поточний більше за попередній на одиницю
                    if (array[i - 1] + 1 == array[i])
                    {
                        allChains.add(array[i]);
                    }
                }

            }
        }

        return allChains.toArray(new Integer[0]);
    }
}
