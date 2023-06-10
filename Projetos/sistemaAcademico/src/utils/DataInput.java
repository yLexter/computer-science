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


    public static <T> T getDataByUser(String labelInput, Consumer<T> validInput, Function<String, T> convertType) {

        Scanner input = Global.getScanner();
        T finalOutput = null, currentOutput = null;

        do {
            try {
                System.out.println(labelInput + " Ou %s para sair".formatted(exitInputString));
                String currentOutputString = input.nextLine();

                checkUserLeftMenu(currentOutputString);
                currentOutput = convertType.apply(currentOutputString);

                validInput.accept(currentOutput);
                finalOutput = currentOutput;

            } catch(IllegalArgumentException err) {
                System.out.printf("Error: %s", err.getMessage());
            }

        } while (finalOutput == null);

        return finalOutput;
    }

    public static String getDataByUser(String labelInput, Consumer<String> validInput) {

        Scanner input = Global.getScanner();
        String finalOutput = null, currentOutput = null;

        do {
            try {
                System.out.println(labelInput + " Ou %s para sair".formatted(exitInputString));

                currentOutput = input.nextLine();
                checkUserLeftMenu(currentOutput);

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
