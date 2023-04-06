package Java.ExerciciosLab.Ap8;

// Questão 1
public class SumElements {

    public static int sum(int[] array) {

        int totalSum = 0;

        for (int x = 0; x < array.length / 2; x++) {

            int element1 = array[x];
            int element2 = array[(array.length - 1) - x];

            totalSum += Math.pow(element1 - element2, 2);
        }

        return totalSum;
    }

    public static void main(String[] args) {

        int[] array = {1,2,3,4,5,6,7,8,9,10};

        System.out.printf("Soma Total: %d", SumElements.sum(array));

    }


}
