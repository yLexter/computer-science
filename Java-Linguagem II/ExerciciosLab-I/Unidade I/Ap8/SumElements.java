package Java.ExerciciosLab.Ap8;

// Questão 1
public class SumElements {

    public static <T extends Number> double sum(T[] array) {

        double totalSum = 0;

        for (int x = 0; x < array.length / 2; x++) {

            T element1 = array[x];
            T element2 = array[(array.length - 1) - x];

            totalSum += Math.pow(element1.doubleValue() - element2.doubleValue(), 2);
        }

        return totalSum;
    }

    public static void main(String[] args) {

        Integer[] array = {1,2,3,4,5,6,7,8,9,10};

        System.out.printf("Soma Total: %f", SumElements.sum(array));

    }


}
