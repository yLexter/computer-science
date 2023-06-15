package general;

import utils.Role;

import java.time.LocalDate;
import java.util.List;

public class Teacher extends Employee {
    private double salary;
    private List<SubjectTeatcher> subjects;

    public Teacher(String name, String lastName, int age, LocalDate dateOfBirth, Role role, String cpf, List<SubjectTeatcher> subjects, double salary) {
        super(name, lastName, age, dateOfBirth, role, cpf);
        this.subjects = subjects;

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

