package interfaces.menu;
import general.Employee;

/**
 * Esta interface representa um menu para funcionários.
 * Ela estende a interface IMenu e adiciona o método para obter o usuário.
 *
 * @param <T> O tipo de funcionário.
 */
public interface IMenuEmployee<T extends Employee> extends IMenu {
    /**
     * Obtém o usuário do menu.
     *
     * @return O usuário do menu.
     */
    T getUser();
}

