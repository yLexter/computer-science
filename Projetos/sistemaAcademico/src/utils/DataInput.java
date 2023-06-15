package utils;

import erros.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class DataInput {

    public static record Option<T> (String label, T option) {

        public String getLabel() {
            return label;
        }

        public T getOption () {
            return option;
        }

    }

    public static final String exitInputString = "-s";

    public static void validStringInput(String string) {
        if (string.equals(""))
           throw new IllegalArgumentException("A String não pode ser vazia");
    }

    public static int validAge(String integer) {
        int toInt = Integer.parseInt(integer);

        if(toInt < 0)
          throw new Error("O número fornecido precisa ser positivo");

        return toInt;
    }

    public static void validCpf(String cpf) {
        if(!cpf.matches("(\\d{3}).(\\d{3}).(\\d{3})-(\\d{2})"))
           throw new Error("O cpf não está no formato XXX.XXX.XXX-XX");
    }

    public static LocalDate validDate(String date) {
        Pattern regex = Pattern.compile("(0[0-9]|[1-2][1-9]|3[0-1])/(0[1-9]|1[0-2])/(\\d{4})");
        Matcher match = regex.matcher(date);

        if(!match.find())
            throw new Error("A Data não está no formato XX/XX/XXXX");

        return LocalDate.of(
            Integer.parseInt(match.group(3)),
            Integer.parseInt(match.group(2)),
            Integer.parseInt(match.group(1))
         );

    }

    public static <T> T getDataByUser(String labelInput, Function<String, T> validInput) {

        Scanner input = Global.getScanner();
        T finalOutput = null;

        do {
            try {
                System.out.println(labelInput + " ou %s para sair".formatted(exitInputString));
                String currentInput = input.nextLine().trim();

                checkUserLeftMenu(currentInput);
                finalOutput = validInput.apply(currentInput);

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
                System.out.println(labelInput + " ou %s para sair".formatted(exitInputString));

                currentOutput = input.nextLine().trim();
                checkUserLeftMenu(currentOutput);

                validInput.accept(currentOutput);
                finalOutput = currentOutput;

            } catch(IllegalArgumentException err) {
                System.out.printf("Error: %s", err.getMessage());
            }

        } while (finalOutput == null);

        return finalOutput;
    }

    public <T> T getOptionByUser(Map<Integer, Option<T>> options) {

        T optionSelected = null;
        Scanner scanner = Global.getScanner();
        int intOption;

        do {

            try {
                showOptions(options);
                System.out.println("Digite a opção desejada: ");
                String stringOption = scanner.nextLine();

                checkUserLeftMenu(stringOption);

                intOption = Integer.parseInt(stringOption);
                if (options.containsKey(intOption)) {
                    optionSelected = options.get(intOption).getOption();
                } else {
                    System.out.println("Opção inválida");
                }

            } catch(NumberFormatException err) {
                Decoration.clearScreen();
                System.out.println("Forneça um número inteiro válido.");
            }

        } while (optionSelected == null);

        return optionSelected;
    }

    public <T> List<T> getOptionsByUser(Map<Integer, Option<T>> options) {

        List<T> optionsSelected = new ArrayList<>();
        Scanner scanner = Global.getScanner();
        int intOption;

        do {
            try {
                showOptions(options);
                System.out.println("Digite a opção desejada: ");
                String stringOption = scanner.nextLine();

                if (stringOption.equals(exitInputString))
                    break;

                intOption = Integer.parseInt(stringOption);

                if (options.containsKey(intOption)) {
                    optionsSelected.add(options.get(intOption).getOption());
                } else {
                    System.out.println("Opção inválida");
                }

                Decoration.clearScreen();

            } catch(NumberFormatException err) {
                Decoration.clearScreen();
                System.out.println("Forneça um número inteiro válido.");
            }

        } while (true);

        return optionsSelected;
    }

    public static <T> void showOptions(Map<Integer, Option<T>> options) {
        for(Map.Entry<Integer, Option<T>> entry : options.entrySet())
            System.out.printf("%d. %s\n", entry.getKey(), entry.getValue().getLabel());
    }

    public static void checkUserLeftMenu(String string) {
        if (string.equals(exitInputString))
            throw new UserLeftMenuException();
    }




}
