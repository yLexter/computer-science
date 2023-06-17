package general;

import utils.Role;

import java.time.LocalDate;
import java.util.List;

public class Teacher extends Employee {
    private double salary;
    public Teacher(String name, String lastName, int age, LocalDate dateOfBirth, String cpf, double salary) {
        super(name, lastName, age, dateOfBirth, Role.TEACHER, cpf);
        this.salary = salary;

        if (salary < 0) {
            throw new RuntimeException("O valor do salário deve ser positivo!");
        }

    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

}

