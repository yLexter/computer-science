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
    T findOne(Predicate<T> callback);
    T authenticate(String registration, String password);

    List<T> findMany(Predicate<T> callback);

    T findById(String id);

    T findByCpf(String cpf);
}
