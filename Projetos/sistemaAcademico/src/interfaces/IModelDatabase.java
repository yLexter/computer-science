package interfaces;
import general.Employee;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public interface IModelDatabase<T extends Employee> {
    List<T> getAll();
    void update(T Employee);
    void delete(T Employee);
    void save(T Employee);
    Employee get(Predicate<T> callback);
    Employee authenticate(String registration, String password);
    Employee geByCpf(String cpf);
    Employee geById(String id);
}
