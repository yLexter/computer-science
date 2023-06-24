package database.DatabaseMemory;
import general.Employee;
import interfaces.database.IDatabaseEmployee;

public abstract class DatabaseEmployee<T extends Employee> extends DatabaseBase<T> implements IDatabaseEmployee<T> {

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

}
