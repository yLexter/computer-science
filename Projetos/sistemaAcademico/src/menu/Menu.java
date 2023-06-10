package menu;
import interfaces.*;
import utils.Decoration;
import utils.Global;
import java.util.*;

public class Menu {
    private final Map<Integer, ISubMenuOption> options;
    public Menu(Map<Integer, ISubMenuOption> options) {
        this.options = options;
    }

    void run() {

        Scanner scanner = Global.getScanner();
        int intOption;

        while(true) {

            try {
                printMenu();
                System.out.println("Digite a opção desejada: ");
                intOption = Integer.parseInt(scanner.nextLine());

                if(intOption == options.size() + 1)
                    break;

                if (options.containsKey(intOption)) {
                    options.get(intOption).run();
                } else {
                    System.out.println("Opção inválida");
                }

             } catch(NumberFormatException err) {
                Decoration.clearScreen();
                System.out.println("Forneça um número inteiro válido.");
            }

        }
    }

    void printMenu() {

        for(Map.Entry<Integer, ISubMenuOption> entry : options.entrySet())
            System.out.printf("%d. %s\n", entry.getKey(), entry.getValue().label());

        System.out.printf("%d. Sair\n", options.size() + 1);
    }

    public static void main(String[] args) {
        new HomeMenu().run();
    }

}