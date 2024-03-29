package interfaces.database;

import java.util.List;
import java.util.function.Predicate;

/**
 * Esta interface representa a base de um banco de dados genérico.
 * Ela define os métodos básicos para manipulação de dados.
 *
 * @param <T> O tipo de dado armazenado no banco de dados.
 */
public interface IDatabaseBase<T> {
    /**
     * Obtém todos os registros do banco de dados.
     *
     * @return Lista contendo todos os registros.
     */
    List<T> getAll();

    /**
     * Atualiza um registro no banco de dados.
     *
     * @param id   ID do registro a ser atualizado.
     * @param data Novos dados a serem atribuídos ao registro.
     */
    void update(String id, T data);

    /**
     * Exclui um registro do banco de dados.
     *
     * @param id ID do registro a ser excluído.
     */
    void delete(String id);

    /**
     * Salva um novo registro no banco de dados.
     *
     * @param data Dados do novo registro a serem salvos.
     */
    void save(T data);

    /**
     * Encontra vários registros que correspondem ao predicado fornecido.
     *
     * @param callback Predicado que define a condição de busca.
     * @return Lista contendo os registros que correspondem ao predicado.
     */
    List<T> findMany(Predicate<T> callback);

    /**
     * Encontra um registro que corresponde ao predicado fornecido.
     *
     * @param callback Predicado que define a condição de busca.
     * @return O registro que corresponde ao predicado, ou null se não encontrado.
     */
    T findById(String id);

    /**
     * Encontra um registro que corresponde ao predicado fornecido.
     *
     * @param callback Predicado que define a condição de busca.
     * @return O registro que corresponde ao predicado, ou null se não encontrado.
     */
    T findOne(Predicate<T> callback);

    /**
     * Atualiza todos os registros do banco de dados com os novos dados fornecidos.
     *
     * @param data Lista contendo os novos dados a serem atribuídos aos registros.
     */
    void updateAll(List<T> data);

    /**
     * Salva vários novos registros no banco de dados.
     *
     * @param data Lista contendo os dados dos novos registros a serem salvos.
     */
    void saveMany(List<T> data);

    /**
     * Verifica se há algum registro que corresponde ao predicado fornecido.
     *
     * @param callback Predicado que define a condição de busca.
     * @return true se existe pelo menos um registro que corresponde ao predicado, false caso contrário.
     */
    boolean has(Predicate<T> callback);
}
