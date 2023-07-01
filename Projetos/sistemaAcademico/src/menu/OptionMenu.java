package menu;
import interfaces.menu.ISubMenuOption;

/**
 * A classe OptionMenu representa uma opção de menu que pode ser selecionada pelo usuário.
 * Ela armazena um rótulo (label) que descreve a opção e uma função a ser executada quando a opção for selecionada.
 */
public record OptionMenu(String label, Runnable function) implements ISubMenuOption {

    /**
     * Executa a função associada a esta opção de menu.
     */

    @Override
    public void run() {
        function.run();
    }

}
