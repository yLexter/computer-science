package menu;
import erros.DatabaseException;
import erros.LeftMenuException;
import interfaces.*;
import utils.DataInput;
import utils.Decoration;
import utils.Global;
import java.util.*;

public class MenuExecutor {
    private final ISubMenu menu;
    private final List<ISubMenuOption> options;

    public static final int startLoopIndex = 1;

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

                if(intOption == options.size() + startLoopIndex)
                    break;

                if (intOption < startLoopIndex || intOption > options.size() + startLoopIndex) {
                    System.out.println("Opção inválida");
                } else {
                    options.get(intOption - startLoopIndex).run();
                }

             } catch(NumberFormatException err) {
                Decoration.clearScreen();
                System.out.println("Forneça um número inteiro válido.");
             } catch (DatabaseException err) {
                if (err.getMessage() != null)
                  System.out.printf("ERROR: %s\n", err.getMessage());
             } catch (LeftMenuException err) {
                System.out.printf("Error: %s\n", err.getMessage());
            }

        }
    }

    void printMenu() {

        int index = startLoopIndex;

        for(ISubMenuOption option : options)
            System.out.printf("%d. %s\n", index++, option.label());

        System.out.printf("%d. Sair\n", options.size() + startLoopIndex);
    }


}
