package Java.ExerciciosLab.Ap2;

import java.security.*;

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
            int roll = new SecureRandom().nextInt(this.totalFaces) + 1;
            this.throwsDices[roll] += 1;
            this.totalSum += roll;
        }

        return this;
    }

    void showRolls() {

        for (int x = 0; x < this.throwsDices.length; x++)
            System.out.printf("Face %d: %d\n", x - 1, this.throwsDices[x]);

        System.out.printf("Soma total: %d", this.totalSum);
    }


}
