package menu;
import erros.DatabaseException;
import erros.LeftMenuException;
import interfaces.menu.IMenu;
import interfaces.menu.ISubMenuOption;
import utils.Decoration;
import utils.Global;
import java.util.*;
import static utils.Constants.startOptionsIndex;

public class MenuExecutor {
    private final IMenu menu;
    private final List<ISubMenuOption> options;

    public MenuExecutor(IMenu menu) {
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

                if(intOption == options.size() + startOptionsIndex)
                    break;

                if (intOption < startOptionsIndex || intOption > options.size() + startOptionsIndex) {
                    System.out.println("Opção inválida");
                } else {
                    options.get(intOption - startOptionsIndex).run();
                }

             } catch(NumberFormatException err) {
                Decoration.clearScreen();
                System.out.println("Forneça um número inteiro válido.");
             } catch (DatabaseException err) {
                  System.out.printf("ERROR: %s\n", err.getMessage());
             } catch (LeftMenuException err) {
                if (err.getMessage() != null)
                  System.out.printf("Error: %s\n", err.getMessage());
            }

        }
    }

    void printMenu() {

        int index = startOptionsIndex;

        for(ISubMenuOption option : options)
            System.out.printf("%d. %s\n", index++, option.label());

        System.out.printf("%d. Sair\n", options.size() + startOptionsIndex);
    }


}
