package general;

import java.util.List;

public class Teacher extends Employee {
    private Double salary;
    private List<Subject> subjects;

    public Teacher(String name, String lastName, int age, String dateOfBirth, String registration, Role role, String cpf, String password, Double salary, List<Subject> subjects) {
        super(name, lastName, age, dateOfBirth, registration, role, cpf, password);
        this.salary = salary;
        this.subjects = subjects;
    }
}
