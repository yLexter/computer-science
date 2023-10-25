package infrastructure.integerData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Vector {
    private static final int MAX_VALUE = 300_000;
    public static List<Integer> generateAscendingVector(int size) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        return list;
    }

    public static List<Integer> generateDescendingVector(int size) {
        List<Integer> list = generateAscendingVector(size);
        Collections.reverse(list);
        return list;
    }

    public static List<Integer> generateAscendingVectorWithRandomEnd(int size) {
        int percentage = getPercentage(size);
        List<Integer> list1 = generateAscendingVector(percentage);
        List<Integer> list2 = generateRandomOrderVector(size - percentage, size);
        list1.addAll(list2);
        return list1;
    }

    public static List<Integer> generateAscendingVectorWithRandomStart(int size) {
        int percentage = getPercentage(size);

        List<Integer> list1 = generateRandomOrderVector(size - percentage, size);
        List<Integer> list2 = generateAscendingVector(percentage);
        list1.addAll(list2);

        return list1;
    }

    private static List<Integer> generateRandomOrderVector(int size, int interval) {
        List<Integer> list = new ArrayList<>();

        if (size > interval) {
            size = interval;
        }

        List<Integer> availableNumbers = new ArrayList<>();

        for (int i = 0; i < interval; i++) {
            availableNumbers.add(i);
        }

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            if (availableNumbers.isEmpty()) {
                break;
            }

            int index = random.nextInt(availableNumbers.size());
            int randomValue = availableNumbers.remove(index);
            list.add(randomValue);
        }

        return list;
    }

    public static List<Integer> generateRandomOrderVector(int size) {
        return generateRandomOrderVector(size, MAX_VALUE);
    }

    public static List<Integer> generateRandomOrderVectorWithRepetiton(int size) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int randomValue = random.nextInt(MAX_VALUE);
            list.add(randomValue);
        }

        return list;
    }

    public static List<Integer> generateRandomOrderVectorWithRepeats(int size) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int randomValue = random.nextInt(MAX_VALUE);
            list.add(randomValue);
        }

        return list;
    }

    public static int getPercentage(int size) {
        final int PERCENTAGE = 90;
        return (int) Math.ceil(size * (PERCENTAGE / 100.0));
    }
}


