package utils;

import erros.*;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class DataInput {

    public static final String exitInputString = "-s";
    public static void validStringInput(String string) {
        if (string.equals(""))
           throw new IllegalArgumentException("A String não pode ser vazia");
    }

    public static String getStringByUser(String labelInput, Consumer<String> validInput) {

        Scanner input = SingleScanner.getScanner();
        String finalOutput = null;

        do {
            try {
               System.out.println(labelInput + " Ou %s para sair".formatted(exitInputString));
               String currentOutput = input.nextLine();

               checkUserLeftMenu(currentOutput);
               validInput.accept(currentOutput);
               finalOutput = currentOutput;
            } catch(IllegalArgumentException err) {
                System.out.printf("Error: %s", err.getMessage());
            }

        } while (finalOutput == null);

        return finalOutput;
    }

    public static Integer getIntByUser(String labelInput, Consumer<Integer> validInput) {

        Integer finalOutput = null, currentOutput;
        Scanner input = SingleScanner.getScanner();

        do {
            try {
                System.out.println(labelInput + " Ou %s para sair".formatted(exitInputString));
                String stringOutput = input.nextLine();

                checkUserLeftMenu(stringOutput);
                currentOutput = Integer.parseInt(stringOutput);
                validInput.accept(currentOutput);
                finalOutput = currentOutput;

            } catch(IllegalArgumentException err) {
                System.out.printf("Error: %s", err.getMessage());
            }

        } while (finalOutput == null);

        return finalOutput;
    }

    public static Float getFloatByUser(String labelInput, Consumer<Float> validInput) {

        Float finalOutput = null, currentOutput;
        Scanner input = SingleScanner.getScanner();

        do {
            try {
                System.out.println(labelInput + " Ou %s para sair".formatted(exitInputString));
                String stringOutput = input.nextLine();

                checkUserLeftMenu(stringOutput);

                currentOutput = Float.parseFloat(stringOutput);
                validInput.accept(currentOutput);
                finalOutput = currentOutput;
            } catch(IllegalArgumentException err) {
                System.out.printf("Error: %s", err.getMessage());
            }

        } while (finalOutput == null);

        return finalOutput;
    }

    public static void checkUserLeftMenu(String string) {
        if (string.equals(exitInputString))
            throw new UserLeftMenuException();
    }




}
