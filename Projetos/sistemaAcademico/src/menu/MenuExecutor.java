package menu;
import erros.DatabaseException;
import erros.LeftMenuException;
import interfaces.*;
import utils.Decoration;
import utils.Global;
import java.util.*;

public class MenuExecutor {
    private final ISubMenu menu;
    private final Map<Integer, ISubMenuOption> options;

    public MenuExecutor(ISubMenu menu) {
        this.menu = menu;
        this.options = menu.getOptions();
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
             } catch (DatabaseException err) {
                 System.out.printf("ERROR: %s\n", err.getMessage());
             } catch (LeftMenuException err) {
                System.out.printf("Error: %s\n", err.getMessage());
            }

        }
    }

    void printMenu() {

        for(Map.Entry<Integer, ISubMenuOption> entry : options.entrySet())
            System.out.printf("%d. %s\n", entry.getKey(), entry.getValue().label());

        System.out.printf("%d. Sair\n", options.size() + 1);
    }


}
