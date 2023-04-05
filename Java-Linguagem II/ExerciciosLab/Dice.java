package Java.ExerciciosLab;

import java.util.Random;

// Exercicio 2
public class Dice {
    private int totalFaces;
    private int totalSum;
    private int[] throwsDices;

    public Dice(int totalFaces) {
        this.totalFaces = totalFaces;
        this.throwsDices = new int[totalFaces + 1];
    }

    Dice rollDice() {
        for (int x = 0; x < 10000000; x++) {
            int roll = new Random().nextInt(this.totalFaces) + 1;
            this.throwsDices[roll] += 1;
            this.totalSum += roll;
        }

        return this;
    }

    void showRolls() {

        for (int x = 1; x < this.throwsDices.length; x++)
            System.out.printf("Face %d: %d\n", x, this.throwsDices[x]);

        System.out.printf("Soma total: %d", this.totalSum);
    }

    public static void test() {

        Dice dice = new Dice(20);

        dice
                .rollDice()
                .showRolls();
    }
}
