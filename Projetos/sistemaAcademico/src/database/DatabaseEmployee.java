package database;
import general.Employee;
import interfaces.IDatabaseEmployee;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DatabaseEmployee<T extends Employee> implements IDatabaseEmployee<T> {
    private List<T> data;
    public DatabaseEmployee() {
        this.data = new ArrayList<>();
    }
    @Override
    public List<T> getAll() {
        return new ArrayList<>(data);
    }
    @Override
    public void update(String id, T employee) {
        delete(id);
        save(employee);
    }

    @Override
    public void delete(String id) {
        T employee = findById(id);

        data.remove(employee);
    }

    @Override
    public void save(T employee) {
        data.add(employee);
    }

    @Override
    public T findOne(Predicate<T> callback) {
        return data
                .stream()
                .filter(callback)
                .findAny()
                .orElse(null);
    }

    @Override
    public List<T> findMany(Predicate<T> callback) {
        return data
                .stream()
                .filter(callback)
                .collect(Collectors.toList());
    }

    @Override
    public T authenticate(String registration, String password) {
        return findOne(employee -> employee.getPassword().equals(password) && employee.getId().equals(registration));
    }

    @Override
    public T findById(String id) {
        return findOne(employee -> employee.getId().equals(id));
    }

    @Override
    public T findByCpf(String cpf) {
        return findOne(employee -> employee.getCpf().equals(cpf));
    }

    @Override
    public void updateAll(List<T> data) {
        this.data = data;
    }

}
