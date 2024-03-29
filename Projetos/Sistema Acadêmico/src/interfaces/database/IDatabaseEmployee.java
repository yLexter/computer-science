package interfaces.database;
import general.Employee;

/**
 * Esta interface representa um banco de dados para os funcionários (Employee).
 * Ela estende a interface IDatabaseBase, fornecendo métodos específicos para os funcionários.
 *
 * @param <T> O tipo de funcionário a ser manipulado.
 */
public interface IDatabaseEmployee<T extends Employee> extends IDatabaseBase<T> {
   /**
     * Autentica um funcionário com base no seu registro e senha.
     *
     * @param registration O registro do funcionário.
     * @param password     A senha do funcionário.
     * @return O funcionário autenticado ou null se a autenticação falhar.
     */
    T authenticate(String registration, String password);

    /**
     * Localiza um funcionário com base no seu CPF.
     *
     * @param cpf O CPF do funcionário.
     * @return O funcionário encontrado ou null se não for encontrado.
     */
    T findByCpf(String cpf);
}
