package utils;

import erros.*;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.*;


// ToDo Melhorar a forma de saida dos formularios e no geral
public class DataInput {

    public record ChoiseOption<T> (String label, T option) {

        public String getLabel() {
            return label;
        }

        public T getOption () {
            return option;
        }

    }

    public static final String exitInputString = "-s";

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
                System.out.printf("Error: %s\n", err.getMessage());
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
                System.out.printf("Error: %s\n", err.getMessage());
            }

        } while (finalOutput == null);

        return finalOutput;
    }

    public static <T> T getOptionByUser(Map<Integer, ChoiseOption<T>> options, String label) {

        T optionSelected = null;
        Scanner scanner = Global.getScanner();
        int intOption;

        do {

            try {
                System.out.println(label);
                showOptions(options);
                System.out.println("Digite a opção desejada: ");
                String stringOption = scanner.nextLine();

                if (stringOption.equals(exitInputString))
                    break;

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

    public static <T> List<T> getOptionsByUser(Map<Integer, ChoiseOption<T>> options, String label) {

        List<T> optionsSelected = new ArrayList<>();
        Scanner scanner = Global.getScanner();
        int intOption;
        T option;

        do {
            try {
                System.out.println(label);
                showOptions(options);
                System.out.println("Digite a opção desejada: ");
                String stringOption = scanner.nextLine();

                if (stringOption.equals(exitInputString))
                    break;

                intOption = Integer.parseInt(stringOption);

                if (!options.containsKey(intOption)) {
                    System.out.println("Opção inválida");
                    continue;
                }

                option = options.get(intOption).getOption();

                if (optionsSelected.contains(option)) {
                    System.out.println("Opção já selecionada");
                    continue;
                }

                optionsSelected.add(option);

                Decoration.clearScreen();

            } catch(NumberFormatException err) {
                Decoration.clearScreen();
                System.out.println("Forneça um número inteiro válido.");
            }

        } while (true);

        return optionsSelected;
    }

    public static <T> void showOptions(Map<Integer, ChoiseOption<T>> options) {
        for(Map.Entry<Integer, ChoiseOption<T>> entry : options.entrySet())
            System.out.printf("%d. %s\n", entry.getKey(), entry.getValue().getLabel());
    }

    public static void checkUserLeftMenu(String string) {
        if (string.equals(exitInputString))
            throw new LeftMenuException();
    }

    public static Boolean getConfirmationByUser(String label){
        Map<Integer, ChoiseOption<Boolean>> options = new LinkedHashMap<>();

        options.put(1, new ChoiseOption<>("Sim", true));
        options.put(2, new ChoiseOption<>("Não", false));

        return getOptionByUser(options, label);
    }

    public static <T> T getElementFromListByUser(List<T> list, Function<T,String> getLabelOption, String label) {

        if (list.size() == 0)
            throw new LeftMenuException("Lista vazia");

        Map<Integer, ChoiseOption<T>> options = new LinkedHashMap<>();

        int count = 0;

        for(T option : list)
            options.put(++count, new ChoiseOption<>(getLabelOption.apply(option), option));

        return getOptionByUser(options, label);
    }

    public static <T> List<T> getElementsFromListByUser(List<T> list, Function<T,String> getLabelOption, String label) {

        if (list.size() == 0)
            throw new LeftMenuException("Lista vazia");

        Map<Integer, ChoiseOption<T>> options = new LinkedHashMap<>();

        int count = 0;

        for(T option : list)
            options.put(++count, new ChoiseOption<>(getLabelOption.apply(option), option));

        return getOptionsByUser(options, label);
    }



}
