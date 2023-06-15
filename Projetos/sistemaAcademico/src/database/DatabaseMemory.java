package database;
import general.Employee;
import interfaces.IModelDatabase;
import java.util.*;
import java.util.function.Predicate;

public class DatabaseMemory<T extends Employee> implements IModelDatabase<T> {
    private final List<T> data;
    public DatabaseMemory() {
        this.data = new ArrayList<>();
    }
    @Override
    public List<T> getAll() {
        return data;
    }

    @Override
    public void update(T employee) {
        delete(employee);
        save(employee);
    }

    @Override
    public void delete(T employee) {
        data.remove(employee);
    }

    @Override
    public void save(T employee) {
        data.add(employee);
    }

    @Override
    public Employee get(Predicate<T> callback) {
        return data
                .stream()
                .filter(callback)
                .findAny()
                .orElse(null);
    }

    @Override
    public Employee authenticate(String registration, String password) {
        return get(employee -> employee.getPassword().equals(password) && employee.getId().equals(registration));
    }

    @Override
    public Employee geById(String id) {
        return get(employee -> employee.getId().equals(id));
    }


    @Override
    public Employee geByCpf(String cpf) {
        return get(employee -> employee.getCpf().equals(cpf));
    }


}
