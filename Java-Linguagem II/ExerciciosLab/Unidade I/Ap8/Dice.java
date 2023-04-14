package Java.ExerciciosLab.Ap8;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Dice {

    private final int sizeDice;
    private int[] rolls;
    private static final int minimumSum = 2;
    private final int maximumSum;
    private final static int firstNumberDice = 1;

    Dice(int sizeDice) {
        this.sizeDice = sizeDice;
        this.maximumSum = sizeDice * 2;
        this.rolls = new int[this.maximumSum + 1];
    }

    public int rollDice() {
        return new SecureRandom().nextInt(this.sizeDice) + 1;
    }

    public String getAllSum(int num) {

        String line = "";

        for (int x = firstNumberDice; x <= num / 2; x++) {

            int result = num - x;

            if (result <= this.sizeDice && result >= firstNumberDice) {
                line += String.format("%d + %d", x, result);

                if (x != num / 2)
                  line += " | ";
            }

        }

        return line;
    }

    public Dice computeRolls() {

        for (int x = minimumSum; x < 36_000_000 ; x++) {
            int roll1 = this.rollDice();
            int roll2 = this.rollDice();

            this.rolls[roll1 + roll2]++;
        }

        return this;
    }

    public void showRolls() {

        for (int x = minimumSum; x < this.rolls.length; x++)
            System.out.printf("Número: %d [ %s ] - %d\n", x, this.getAllSum(x), this.rolls[x]);

    }



    public static void main(String[] args) {

        Dice dice = new Dice(6);

           dice
             .computeRolls()
             .showRolls();

    }




}
