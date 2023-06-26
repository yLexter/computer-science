package general;

import utils.Role;

import java.time.LocalDate;

public class Admin extends Employee {

    public Admin(String name, String lastName, LocalDate dateOfBirth, String cpf) {
        super(name, lastName, dateOfBirth, Role.ADMIN, cpf);
    }



    public String toString() {
        return String.format("%s", super.toString());
    }


}
