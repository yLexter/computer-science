package database.DatabaseMemory;
import general.Employee;
import interfaces.database.IDatabaseEmployee;

/**
  Esta é uma classe abstrata que representa um banco de dados de funcionários.
  Ela estende a classe DatabaseBase e implementa a interface IDatabaseEmployee.
  Aqui são fornecidas operações de banco de dados específicas para funcionários.
  A classe genérica T é restrita a tipos que estendem a classe Employee.
  Os métodos nesta classe permitem autenticação, busca por ID e busca por CPF.
*/
public abstract class DatabaseEmployee<T extends Employee> extends DatabaseBase<T> implements IDatabaseEmployee<T> {

  /**
  Autentica um funcionário com base em seu registro e senha.
  @param registration O registro do funcionário.
  @param password A senha do funcionário.
  @return O funcionário autenticado, ou null se a autenticação falhar.
*/
    @Override
    public T authenticate(String registration, String password) {
        return findOne(employee -> employee.getPassword().equals(password) && employee.getId().equals(registration));
    }

  /**
  Encontra um funcionário pelo seu ID.
  @param id O ID do funcionário a ser encontrado.
  @return O funcionário encontrado, ou null se nenhum funcionário correspondente for encontrado.
*/
    @Override
    public T findById(String id) {
        return findOne(employee -> employee.getId().equals(id));
    }

  /**
  Encontra um funcionário pelo seu CPF.
  @param cpf O CPF do funcionário a ser encontrado.
  @return O funcionário encontrado, ou null se nenhum funcionário correspondente for encontrado.
*/
    @Override
    public T findByCpf(String cpf) {
        return findOne(employee -> employee.getCpf().equals(cpf));
    }

}
