package general;

enum Role {
    STUDENT,
    TEACHER,
    COORDINATOR
}

public class Employee {
    private String name;
    private String lastName;
    private final String cpf;
    private int age;
    private String dateOfBirth;
    private final String registration;
    private Role role;
    private String password;
    public Employee(String name, String lastName, int age, String dateOfBirth, String registration, Role role, String cpf, String password) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.registration = registration;
        this.role = role;
        this.cpf = cpf;
        this.password = password;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegistration() {
        return registration;
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


}
