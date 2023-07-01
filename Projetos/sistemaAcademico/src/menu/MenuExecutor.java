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

   /**
     * Cria um novo MenuExecutor com base no menu fornecido.
     *
     * @param menu o menu a ser executado
     */
    public MenuExecutor(IMenu menu) {
        this.menu = menu;
        this.options = menu.getOptions();
    }

  /**
     * Executa o menu, exibindo as opções disponíveis e processando a escolha do usuário.
     */
    void run() {

        Scanner scanner = Global.getScanner();
        int intOption;

        while(true) {

            try {
                System.out.println(menu.getHeader());

                printMenu();
                System.out.println("Digite a opção desejada: ");
                intOption = Integer.parseInt(scanner.nextLine());

                if(intOption == options.size() + startOptionsIndex)
                    break;

                if (intOption < startOptionsIndex || intOption > options.size() + startOptionsIndex) {
                    Decoration.showMessageAndClearScreen("Opção inválida");
                } else {
                    options.get(intOption - startOptionsIndex).run();
                }

             } catch(NumberFormatException err) {
                Decoration.showMessageAndClearScreen("Forneça um número inteiro válido.");
             } catch (DatabaseException err) {
                Decoration.showMessageAndClearScreen("ERROR: %s\n".formatted(err.getMessage()));
             } catch (LeftMenuException err) {
                if (err.getMessage() != null)
                    Decoration.showMessageAndClearScreen("ERROR: %s\n".formatted(err.getMessage()));
            } catch (RuntimeException err) {
                Decoration.showMessageAndClearScreen("Error: %s".formatted(err.getMessage()));
            }

        }
    }

  /**
     * Imprime as opções do menu na tela.
     */
    void printMenu() {

        int index = startOptionsIndex;

        for(ISubMenuOption option : options)
            System.out.printf("%d. %s\n", index++, option.label());

        System.out.printf("%d. Sair\n", options.size() + startOptionsIndex);
    }


}
