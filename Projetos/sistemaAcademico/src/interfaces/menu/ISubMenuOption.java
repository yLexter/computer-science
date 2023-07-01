package interfaces.menu;
import java.util.*;

/**
 * Esta interface representa uma opção de submenu.
 * Ela estende a interface Runnable e adiciona o método para obter o rótulo da opção.
 */
public interface ISubMenuOption extends Runnable {
    /**
     * Obtém o rótulo da opção.
     *
     * @return O rótulo da opção.
     */
    String label();
}
