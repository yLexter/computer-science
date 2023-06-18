package interfaces;
import general.Employee;
import java.util.*;
import java.util.function.Predicate;

public interface IDatabaseEmployee<T extends Employee> {
    T authenticate(String registration, String password);
    T findByCpf(String cpf);
}
