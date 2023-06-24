package interfaces.menu;
import general.Employee;

public interface IMenuEmployee<T extends Employee> extends IMenu {
    T getUser();
}

