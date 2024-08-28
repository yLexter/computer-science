package infrastructure.integerData;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vector {

    private static final int MAX_VALUE = 300_000;

    private static final SecureRandom random = new SecureRandom();

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
        int percentage = getPercentage(size), temp;
        List<Integer> list1 = generateAscendingVector(size);

        for (int i = percentage; i < list1.size(); i++) {
            int randomPosition = random.nextInt(percentage, size);

            temp = list1.get(i);
            list1.set(i, random.nextInt(size));
            list1.set(randomPosition, temp);

        }


        return list1;
    }

    public static List<Integer> generateAscendingVectorWithRandomStart(int size) {
        List<Integer> list1 = generateAscendingVector(size);
        int percentageStart = size - getPercentage(size), temp;

        for (int i = 0; i < percentageStart; i++) {
            int randomPosition = random.nextInt(0, percentageStart);
            temp = list1.get(i);
            list1.set(i, random.nextInt(size));
            list1.set(randomPosition, temp);
        }

        return list1;
    }

    public static List<Integer> generateRandomOrderVectorWithoutRepetition(int size) {
        List<Integer> list = generateAscendingVector(size);

        for (int i = 0; i < size; i++) {
            int randomPosition = random.nextInt(0, size);

            int temp = list.get(i);
            list.set(i, list.get(randomPosition));
            list.set(randomPosition, temp);
        }

        return list;
    }

    public static List<Integer> generateRandomOrderVectorWithRepetiton(int size) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int randomValue = random.nextInt(MAX_VALUE);
            list.add(randomValue);
        }

        return list;
    }

    public static List<Integer> generateRandomOrderVector(int size) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int randomValue = random.nextInt(size);
            list.add(randomValue);
        }

        return list;
    }

    public static int getPercentage(int size) {
        final int PERCENTAGE = 90;
        return (int) Math.ceil(size * (PERCENTAGE / 100.0));
    }

}


