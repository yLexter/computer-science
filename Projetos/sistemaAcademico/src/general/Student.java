package general;

import java.util.*;
public class Student extends Employee {

    private List<Subject> subjects;


    public Student(String name, String lastName, int age, String dateOfBirth, String registration, Role role, String cpf, String password, List<Subject> subjects) {
        super(name, lastName, age, dateOfBirth, registration, role, cpf, password);
        this.subjects = subjects;
    }
}
