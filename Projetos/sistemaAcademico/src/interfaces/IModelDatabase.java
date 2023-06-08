package interfaces;
import general.Employee;
import java.util.*;
public interface IModelDatabase {
    void connect() throws Exception;
    List<Employee> getAllEmployees();
    void updateEmployee(Employee Employee);
    void deleteEmployee(Employee Employee);
    void save(Employee Employee);
    Employee getEmployeeByCpf(String id);
    Employee authenticateEmployee(String registration, String password);
}
