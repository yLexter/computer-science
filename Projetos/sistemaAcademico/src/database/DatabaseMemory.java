package database;
import general.Employee;
import interfaces.IModelDatabase;
import java.util.*;

public class DatabaseMemory implements IModelDatabase {
    private List<Employee> data;

    DatabaseMemory(List<Employee> data){
        this.data = new ArrayList<>();
    }
    @Override
    public void connect() throws Exception {}

    @Override
    public List<Employee> getAllEmployees() {
        return data;
    }

    @Override
    public void updateEmployee(Employee employee) {
        deleteEmployee(employee);
        save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
         data.remove(employee);
    }

    @Override
    public void save(Employee employee) {
        data.add(employee);
    }

    @Override
    public Employee getEmployeeByCpf(String cpf) {
        return data
                .stream()
                .filter(employee -> employee.getCpf().equals(cpf))
                .findAny()
                .orElse(null);
    }

    @Override
    public Employee authenticateEmployee(String registration, String password) {
        return data
                .stream()
                .filter(employee -> employee.getPassword().equals(password) && employee.getRegistration().equals(registration))
                .findAny()
                .orElse(null);
    }
}
