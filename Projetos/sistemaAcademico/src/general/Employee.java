package general;
import utils.Role;

import java.security.SecureRandom;
import java.time.*;

// ToDo implementar password igual a data nascimento e id como matricula
public abstract class Employee {
    public static int sizeId = 6;
    private String name;
    private String lastName;
    private final String cpf;
    private int age;
    private LocalDate dateOfBirth;
    private final String id;
    private Role role;
    private String password;

    public Employee(String name, String lastName, int age, LocalDate dateOfBirth, Role role, String cpf) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.cpf = cpf;
        this.id = "15";
        this.password = "15";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String generateId() {
        SecureRandom random = new SecureRandom();
        LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());
        String lastTwoDigitsYear = Integer.toString(currentDate.getYear()).substring(2,4);
        String currentPeriod = currentDate.getMonthValue() > 6 ? "2" : "1";
        StringBuilder id = new StringBuilder("%s%s".formatted(lastTwoDigitsYear, currentPeriod));

        for(int x = 3; x < sizeId; x++)
            id.append(random.nextInt(9));

        return id.toString();
    }


}
