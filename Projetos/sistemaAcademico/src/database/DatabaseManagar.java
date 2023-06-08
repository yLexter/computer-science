package database;
import erros.*;
import general.Employee;
import interfaces.IModelDatabase;
import java.util.*;

public class DatabaseManagar {
    private final IModelDatabase db;
    DatabaseManagar(IModelDatabase db) {
        this.db = db;
    }
    public List<Employee> getAllEmployees() {
        return db.getAllEmployees();
    }
    public void createEmployee(Employee employee) throws DatabaseException {

        Employee employeeAlreadyExist = db.getEmployeeByCpf(employee.getCpf());

        if (employeeAlreadyExist != null)
            throw new DatabaseException(ErrorMessage.EMPLOYEE_ALREADY_EXISTS);

         db.save(employee);
    }

    public void deleteEmployee(Employee employee) throws DatabaseException {

        Employee deletedEmployee = db.getEmployeeByCpf(employee.getCpf());

        if (deletedEmployee == null)
            throw new DatabaseException(ErrorMessage.EMPLOYEE_NOT_FOUND);

        db.deleteEmployee(employee);
    }

    public void updateEmployee(Employee employee) throws DatabaseException {

        Employee deletedEmployee = db.getEmployeeByCpf(employee.getCpf());

        if (deletedEmployee == null)
            throw new DatabaseException(ErrorMessage.EMPLOYEE_NOT_FOUND);

        db.updateEmployee(deletedEmployee);
    }

    public Employee authenticateEmployee(String registration, String password) throws DatabaseException {

        Employee employeeAuthentic = db.authenticateEmployee(registration, password);

        if (employeeAuthentic == null)
           throw new DatabaseException(ErrorMessage.EMPLOYEE_NOT_AUTHENTICATE);

        return employeeAuthentic;
    }

}


