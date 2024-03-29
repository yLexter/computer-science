package general;
import utils.*;

import java.security.SecureRandom;
import java.time.*;
import java.time.format.DateTimeFormatter;


/**
 * Essa classe representa um funcionário. Ela contém informações como nome, sobrenome, CPF, data de nascimento, ID,
 * papel (role) e senha.
 */
public class Employee {
    public static final int sizeId = 8;
    private String name;
    private String lastName;
    private final String cpf;
    private LocalDate dateOfBirth;
    private final String id;
    private Role role;
    private String password;

    /**
     * Constrói um novo objeto Employee com as informações fornecidas.
     *
     * @param name         O nome do funcionário.
     * @param lastName     O sobrenome do funcionário.
     * @param dateOfBirth  A data de nascimento do funcionário.
     * @param role         O papel (role) do funcionário.
     * @param cpf          O CPF do funcionário.
     */
    public Employee(String name, String lastName, LocalDate dateOfBirth, Role role, String cpf) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.cpf = cpf;
        this.id = generateId();
        this.password = formatDateOfBirthToPassword();
    }

    /**
     * Obtém o nome do funcionário.
     *
     * @return O nome do funcionário.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do funcionário.
     *
     * @param name O nome do funcionário a ser definido.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o sobrenome do funcionário.
     *
     * @return O sobrenome do funcionário.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Define o sobrenome do funcionário.
     *
     * @param lastName O sobrenome do funcionário a ser definido.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtém a idade do funcionário.
     *
     * @return A idade do funcionário.
     */
    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     * Obtém a data de nascimento do funcionário.
     *
     * @return A data de nascimento do funcionário.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Define a data de nascimento do funcionário.
     *
     * @param dateOfBirth A data de nascimento do funcionário a ser definida.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Obtém o ID do funcionário.
     *
     * @return O ID do funcionário.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtém o CPF do funcionário.
     *
     * @return O CPF do funcionário.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Obtém o papel (role) do funcionário.
     *
     * @return O papel (role) do funcionário.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Define o papel (role) do funcionário.
     *
     * @param role O papel (role) do funcionário a ser definido.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Obtém a senha do funcionário.
     *
     * @return A senha do funcionário.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a senha do funcionário.
     *
     * @param password A senha do funcionário a ser definida.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gera um ID para o funcionário.
     *
     * @return O ID gerado para o funcionário.
     */
    public String generateId() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        SecureRandom random = new SecureRandom();
        String[] splited = academicSystem.getCurrentPeriod().split("\\.");
        StringBuilder id = new StringBuilder("%s%s".formatted(splited[0], splited[1]));

        for(int x = 3; x < sizeId; x++)
            id.append(random.nextInt(9));

        return id.toString();
    }

    /**
     * Formata a data de nascimento como senha.
     *
     * @return A data de nascimento formatada como senha.
     */
    public String formatDateOfBirthToPassword() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return this.dateOfBirth.format(formatter);
    }

    /**
     * Obtém a data de nascimento formatada.
     *
     * @return A data de nascimento formatada.
     */
    public String getFormatedDateOfBirth() {
        return Utils.formatDate(dateOfBirth);
    }

    /**
     * Cria um novo objeto Employee com base nos dados fornecidos pelo usuário.
     *
     * @param role O papel (role) do funcionário.
     * @return Um novo objeto Employee criado com base nos dados do usuário.
     */
    public static Employee createEmployeeByUser(Role role) {
        return new Employee(
                DataInput.getDataByUser("Digite o nome", DataEntryValidator::validName),
                DataInput.getDataByUser("Digite o sobrenome", DataEntryValidator::validName),
                DataInput.getDataByUser("Digite a data de nascimento no formato XX/XX/XXXX", DataEntryValidator::validDateIsNotFromTheFuture),
                role,
                DataInput.getDataByUser("Digite o CPF com apenas 11 dígitos", DataEntryValidator::validCpf)
         );
    }

    /**
     * Obtém o nome completo do funcionário.
     *
     * @return O nome completo do funcionário.
     */
    public String getFullName() {
        return "%s %s".formatted(name, lastName);
    }

    /**
     * Retorna uma representação em formato de string do objeto Employee.
     *
     * @return Uma string que representa o objeto Employee.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s | Idade: %d", id , getFullName(), getAge());
    }

}

