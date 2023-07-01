package general;

import utils.Role;

import java.time.LocalDate;

/**
 * Essa classe representa um funcionário do tipo Administrador. Ela herda os atributos e comportamentos da classe Employee.
 * 
 * O construtor Admin permite criar uma instância de um Administrador com informações como nome, sobrenome, data de nascimento e CPF.
 * 
 * O método toString retorna uma representação em formato de string do objeto Admin.
 */
public class Admin extends Employee {

    /**
     * Constrói um novo objeto Admin com as informações fornecidas.
     *
     * @param name O nome do administrador.
     * @param lastName O sobrenome do administrador.
     * @param dateOfBirth A data de nascimento do administrador.
     * @param cpf O CPF do administrador.
     */
    public Admin(String name, String lastName, LocalDate dateOfBirth, String cpf) {
        super(name, lastName, dateOfBirth, Role.ADMIN, cpf);
    }

    /**
     * Retorna uma representação em formato de string do objeto Admin.
     *
     * @return Uma string que representa o objeto Admin.
     */
    public String toString() {
        return String.format("%s", super.toString());
    }
  
}
