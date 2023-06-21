package utils;

import erros.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;


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

    public static final int startLoopIndex = 1;

    public static <T> T getDataByUser(String labelInput, Function<String, T> convertToType, Consumer<T> validInput) {

        Scanner input = Global.getScanner();
        T finalOutput = null, converted;

        do {
            try {
                System.out.println(labelInput + " ou %s para sair".formatted(exitInputString));
                String currentInput = input.nextLine().trim();

                checkUserLeftMenu(currentInput);

                converted = convertToType.apply(currentInput);
                validInput.accept(converted);
                finalOutput = converted;

            } catch(IllegalArgumentException err) {
                System.out.printf("Error: %s\n", err.getMessage());
            }


        } while (finalOutput == null);

        return finalOutput;
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

    public static <T> void showOptions(List<ChoiseOption<T>> options) {
        int index = startLoopIndex;

        for(ChoiseOption<T> option : options)
            System.out.printf("%d. %s\n", index++, option.getLabel());
    }

    public static void checkUserLeftMenu(String string) {
        if (string.equals(exitInputString))
            throw new LeftMenuException();
    }

    public static Boolean getConfirmationByUser(String label){
        List<ChoiseOption<Boolean>> options = new ArrayList<>();

        options.add(new ChoiseOption<>("Sim", true));
        options.add(new ChoiseOption<>("Não", false));

        return getElementFromListByUser(options,ChoiseOption::getLabel ,label).option();
    }

    public static <T> T getElementFromListByUser(List<T> list, Function<T,String> getLabelOption, String label) {

        if (list.size() == 0)
            throw new LeftMenuException("Lista vazia");

        List<ChoiseOption<T>> listOptions = list
                .stream()
                .map(option -> new ChoiseOption<>(getLabelOption.apply(option), option))
                .toList();

        T optionSelected = null;
        Scanner scanner = Global.getScanner();
        int intOption;

        do {
            try {
                System.out.println(label);
                showOptions(listOptions);
                System.out.println("Digite a opção desejada: ");
                String stringOption = scanner.nextLine();

                if (stringOption.equals(exitInputString))
                    break;

                intOption = Integer.parseInt(stringOption);

                if (intOption < startLoopIndex || intOption > listOptions.size() + startLoopIndex) {
                    System.out.println("Opção inválida");
                } else {
                    optionSelected = listOptions.get(intOption - startLoopIndex).getOption();
                }

            } catch(NumberFormatException err) {
                Decoration.clearScreen();
                System.out.println("Forneça um número inteiro válido.");
            }

        } while (optionSelected == null);

        return optionSelected;


    }

    public static <T> List<T> getElementsFromListByUser(List<T> list, Function<T,String> getLabelOption, String label) {

        if (list.size() == 0)
            throw new LeftMenuException("Lista vazia");

        List<ChoiseOption<T>> listOptions = list
                .stream()
                .map(option -> new ChoiseOption<>(getLabelOption.apply(option), option))
                .toList();

        List<T> optionsSelected = new ArrayList<>();
        Scanner scanner = Global.getScanner();
        int intOption;
        T option;

        do {
            try {
                System.out.println(label);
                showOptions(listOptions);
                System.out.println("Digite a opção desejada: ");
                String stringOption = scanner.nextLine();

                if (stringOption.equals(exitInputString))
                    break;

                intOption = Integer.parseInt(stringOption);

                if (intOption < startLoopIndex || intOption > listOptions.size() + startLoopIndex) {
                    System.out.println("Opção inválida");
                    continue;
                }

                option = listOptions.get(intOption - startLoopIndex).getOption();

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

}
