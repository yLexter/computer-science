package utils;

import erros.*;

import java.time.DateTimeException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import interfaces.general.IObjectFactory;
import static utils.Constants.*;

/**
 * A classe DataInput fornece métodos para obtenção e validação de entrada de usuário.
 */


public class DataInput {

   /**
     * Classe interna para representar uma opção de escolha.
     * Cada opção possui um rótulo e um valor associado.
     *
     * @param <T> o tipo do valor da opção
     */

    public record ChoiseOption<T> (String label, T option) {
       /**
         * Obtém o rótulo da opção.
         *
         * @return o rótulo da opção
         */

        public String getLabel() {
            return label;
        }
      
       /**
         * Obtém o valor da opção.
         *
         * @return o valor da opção
         */
      

        public T getOption () {
            return option;
        }

    }

  /**
 * Obtém dados de entrada do usuário e os valida.
 *
 * @param <T>           o tipo dos dados de saída
 * @param labelInput    a etiqueta de entrada exibida ao usuário
 * @param convertToType uma função para converter a entrada em um tipo específico
 * @param validInput    um consumidor para validar a entrada convertida
 * @return os dados de entrada validados
 */
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

            } catch (DateTimeException | IllegalArgumentException err) {
                Decoration.showMessageAndClearScreen("Error: Data fornecida é invalida");
            }

        } while (finalOutput == null);

        return finalOutput;
    }

  /**
 * Obtém dados de entrada do usuário e os valida.
 *
 * @param <T>          o tipo dos dados de saída
 * @param labelInput   a etiqueta de entrada exibida ao usuário
 * @param validInput   uma função para validar e converter a entrada em um tipo específico
 * @return os dados de entrada validados
 */

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
                Decoration.showMessageAndClearScreen("Error: %s".formatted(err.getMessage()));
            }


        } while (finalOutput == null);

        return finalOutput;
    }

  /**
 * Obtém dados de entrada do usuário e os valida.
 *
 * @param labelInput   a etiqueta de entrada exibida ao usuário
 * @param validInput   um consumidor para validar a entrada
 * @return o dado de entrada validado
 */

    public static String getDataByUser(String labelInput, Consumer<String> validInput) {

        Scanner input = Global.getScanner();
        String finalOutput = null, currentOutput;

        do {
            try {
                System.out.println(labelInput + " ou %s para sair".formatted(exitInputString));

                currentOutput = input.nextLine().trim();
                checkUserLeftMenu(currentOutput);

                validInput.accept(currentOutput);
                finalOutput = currentOutput;

            } catch(IllegalArgumentException err) {
                Decoration.showMessageAndClearScreen("ERROR: %s\n".formatted(err.getMessage()));
            }

        } while (finalOutput == null);

        return finalOutput;
    }

/**
 * Exibe as opções de escolha para o usuário.
 *
 * @param options uma lista de opções de escolha
 * @param <T> o tipo do valor das opções de escolha
 */

    public static <T> void showOptions(List<ChoiseOption<T>> options) {
        int index = startOptionsIndex;

        for(ChoiseOption<T> option : options)
            System.out.printf("%d. %s\n", index++, option.getLabel());
    }

/**
 * Verifica se o usuário inseriu o comando para sair do menu.
 *
 * @param string a string inserida pelo usuário
 * @throws LeftMenuException se a string corresponder ao comando de saída do menu
 */

    public static void checkUserLeftMenu(String string) {
        if (string.equals(exitInputString))
            throw new LeftMenuException();
    }

   /**
 * Obtém a confirmação do usuário com base em uma pergunta fornecida.
 *
 * @param label a pergunta exibida ao usuário
 * @return o valor de confirmação escolhido pelo usuário (true para "Sim", false para "Não")
 */

    public static Boolean getConfirmationByUser(String label){

        List<ChoiseOption<Boolean>> options = List.of(
                new ChoiseOption<>("Sim", true),
                new ChoiseOption<>("Não", false)
        );

        return getElementFromListByUser(options,ChoiseOption::getLabel ,label).option();
    }

   /**
 * Obtém um elemento de uma lista com base na escolha do usuário.
 *
 * @param list           a lista de elementos disponíveis para escolha
 * @param getLabelOption uma função para obter o rótulo de um elemento da lista
 * @param label          a pergunta ou instrução exibida ao usuário
 * @return o elemento selecionado pelo usuário
 * @throws LeftMenuException se a lista estiver vazia
 */

    public static <T> T getElementFromListByUser(List<T> list, Function<T,String> getLabelOption, String label) {

        if (list.size() == 0)
            throw new LeftMenuException("Lista vazia");

        Scanner scanner = Global.getScanner();
        List<ChoiseOption<T>> listOptions = list
                .stream()
                .map(option -> new ChoiseOption<>(getLabelOption.apply(option), option))
                .toList();

        T optionSelected = null;
        int intOption;

        do {
            try {
                System.out.println(label);
                showOptions(listOptions);
                System.out.println("Digite a opção desejada: ");
                String stringOption = scanner.nextLine();

                checkUserLeftMenu(stringOption);

                intOption = Integer.parseInt(stringOption);

                if (intOption < startOptionsIndex || intOption >= listOptions.size() + startOptionsIndex) {
                    Decoration.showMessageAndClearScreen("Opção inválida");
                } else {
                    optionSelected = listOptions.get(intOption - startOptionsIndex).getOption();
                }

            } catch(NumberFormatException err) {
                Decoration.showMessageAndClearScreen("orneça um número inteiro válido");
            }

        } while (optionSelected == null);

        return optionSelected;


    }

/**
 * Obtém uma lista de elementos da lista com base nas escolhas do usuário.
 *
 * @param list           a lista de elementos disponíveis para escolha
 * @param getLabelOption uma função para obter o rótulo de um elemento da lista
 * @param label          a pergunta ou instrução exibida ao usuário
 * @return uma lista de elementos selecionados pelo usuário
 * @throws LeftMenuException se a lista estiver vazia
 */

    public static <T> List<T> getElementsFromListByUser(List<T> list, Function<T,String> getLabelOption, String label) {

        if (list.size() == 0)
            throw new LeftMenuException("Lista vazia");

        List<ChoiseOption<T>> listOptions;
        List<T> optionsSelected = new ArrayList<>();
        Scanner scanner = Global.getScanner();
        int intOption;
        T optionSelected;

        do {
            try {

                if (optionsSelected.size() == list.size())
                    return optionsSelected;

                ArrayList<T> listWithoutDuplicates = Utils.getIntersectionBetweenLists(list, optionsSelected);
                listOptions = listWithoutDuplicates
                        .stream()
                        .map(option -> new ChoiseOption<>(getLabelOption.apply(option), option))
                        .toList();

                showOptions(listOptions);
                System.out.println(label + " ou digite %s para parar de escolher".formatted(exitInputString));
                System.out.println("Digite a opção desejada: ");

                String stringOption = scanner.nextLine();

                if (stringOption.equals(exitInputString))
                    return optionsSelected;

                intOption = Integer.parseInt(stringOption);

                if (intOption < startOptionsIndex || intOption >= listOptions.size() + startOptionsIndex) {
                    Decoration.showMessageAndClearScreen("Opção inválida");
                    continue;
                }

                optionSelected = listOptions.get(intOption - startOptionsIndex).getOption();

                optionsSelected.add(optionSelected);

                Decoration.clearScreen();

            } catch(NumberFormatException err) {
                Decoration.showMessageAndClearScreen("Forneça um número inteiro válido.");
            }

        } while (true);

    }

    /**
 * Obtém uma lista de elementos da lista com base nas escolhas do usuário, com um tamanho máximo especificado.
 *
 * @param list           a lista de elementos disponíveis para escolha
 * @param getLabelOption uma função para obter o rótulo de um elemento da lista
 * @param label          a pergunta ou instrução exibida ao usuário
 * @param maximumSize    o tamanho máximo da lista de elementos selecionados
 * @return uma lista de elementos selecionados pelo usuário
 * @throws LeftMenuException se a lista estiver vazia
 */

    public static <T> List<T> getElementsFromListByUser(List<T> list, Function<T,String> getLabelOption, String label, int maximumSize) {

        if (list.size() == 0)
            throw new LeftMenuException("Lista vazia");

        List<T> optionsSelected = new ArrayList<>();
        List<ChoiseOption<T>>  listOptions;
        T optionSelected;

        Scanner scanner = Global.getScanner();

        int intOption;

        do {
            try {

                if (optionsSelected.size() == maximumSize || optionsSelected.size() == list.size())
                    return optionsSelected;

                ArrayList<T> listWithoutDuplicates = Utils.getIntersectionBetweenLists(list, optionsSelected);
                listOptions = listWithoutDuplicates
                        .stream()
                        .map(option -> new ChoiseOption<>(getLabelOption.apply(option), option))
                        .toList();

                showOptions(listOptions);
                System.out.println(label + " ou Digite %s para parar de escolher".formatted(exitInputString));
                System.out.printf("Itens Escolhidos: %d/%d\n\n", optionsSelected.size(), maximumSize);
                System.out.println("Digite a opção desejada: ");

                String stringOption = scanner.nextLine();

                if (stringOption.equals(exitInputString))
                    return optionsSelected;

                intOption = Integer.parseInt(stringOption);

                if (intOption < startOptionsIndex || intOption >= listOptions.size() + startOptionsIndex) {
                    Decoration.showMessageAndClearScreen("Opção inválida");
                    continue;
                }

                optionSelected = listOptions.get(intOption - startOptionsIndex).getOption();


                optionsSelected.add(optionSelected);

                Decoration.clearScreen();

            } catch(NumberFormatException err) {
                Decoration.showMessageAndClearScreen("Forneça um número inteiro válido.");
            }

        } while (true);

    }
/**
 * Obtém instâncias de objetos fornecidos por meio de uma fábrica de objetos,
 * com um limite máximo de instâncias a serem criadas.
 *
 * @param function uma fábrica de objetos que gera instâncias com base em dados existentes
 * @param limit    o limite máximo de instâncias a serem criadas
 * @param <T>      o tipo de objeto a ser instanciado
 * @return uma lista contendo as instâncias de objetos geradas
 * @throws LeftMenuException     se a fábrica de objetos retornar uma exceção LeftMenuException quando nenhum dado estiver disponível
 * @throws IllegalArgumentException se a fábrica de objetos lançar uma exceção IllegalArgumentException ao gerar uma instância de objeto
 */

  
    public static <T> List<T> getObjectInstancesByUser(IObjectFactory<T> function, int limit) {
        ArrayList<T> data = new ArrayList<>();
        T currentData;

        while (data.size() != limit) {
            try {
               currentData = function.generate(data);
               data.add(currentData);

            } catch (IllegalArgumentException err) {
                Decoration.showMessageAndClearScreen("ERROR: %s\n".formatted(err.getMessage()));
            } catch (LeftMenuException err) {
                if(data.size() == 0)
                    throw new LeftMenuException();
                return data;
            }
        }

        return data;
    }

    /**
 * Obtém instâncias de objetos fornecidos por meio de uma fábrica de objetos de forma contínua.
 * O método continua a gerar e adicionar instâncias de objetos à lista de dados até que ocorra uma exceção LeftMenuException.
 *
 * @param function uma fábrica de objetos que gera instâncias com base em dados existentes
 * @param <T>      o tipo de objeto a ser instanciado
 * @return uma lista contendo as instâncias de objetos geradas
 * @throws LeftMenuException     se a fábrica de objetos retornar uma exceção LeftMenuException quando nenhum dado estiver disponível
 * @throws IllegalArgumentException se a fábrica de objetos lançar uma exceção IllegalArgumentException ao gerar uma instância de objeto
 */

    public static <T> List<T> getObjectInstancesByUser(IObjectFactory<T> function) {
        ArrayList<T> data = new ArrayList<>();
        T currentData;

        while (true) {

            try {
                currentData = function.generate(data);
                data.add(currentData);

            } catch (IllegalArgumentException err) {
                Decoration.showMessageAndClearScreen("Erro: %s".formatted(err.getMessage()));
            } catch (LeftMenuException err) {
                if(data.size() == 0)
                    throw new LeftMenuException();
                return data;
            }
        }

    }

}
