package general;

import utils.Role;

import java.time.LocalDate;

public class Admin extends Employee {

    public Admin(String name, String lastName, int age, LocalDate dateOfBirth, String cpf) {
        super(name, lastName, age, dateOfBirth, Role.ADMIN, cpf);
    }

    public String toString() {
        return String.format("%s", super.toString());
    }

}
