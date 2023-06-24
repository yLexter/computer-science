package interfaces.database;
import general.Employee;

public interface IDatabaseEmployee<T extends Employee> extends IDatabaseBase<T> {
    T authenticate(String registration, String password);
    T findByCpf(String cpf);
}
