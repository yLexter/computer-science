package interfaces.menu;

import java.util.List;

/**
 * Esta interface representa um menu.
 * Ela define os métodos para executar o menu, obter as opções de submenus e obter o cabeçalho do menu.
 */
public interface IMenu {
    /**
     * Executa o menu.
     */
    void run();

    /**
     * Obtém as opções de submenus do menu.
     *
     * @return A lista de opções de submenus.
     */
    List<ISubMenuOption> getOptions();

    /**
     * Obtém o cabeçalho do menu.
     *
     * @return O cabeçalho do menu.
     */
    String getHeader();
}

