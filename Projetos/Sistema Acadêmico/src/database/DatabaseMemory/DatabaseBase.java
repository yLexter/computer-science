package database.DatabaseMemory;

import interfaces.database.IDatabaseBase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Classe abstrata que serve como base genérica para classes de banco de dados.
 *
 * @param <T> O tipo de dado armazenado no banco de dados.
 */
public abstract class DatabaseBase<T> implements IDatabaseBase<T> {

    protected ArrayList<T> data;

    /**
     * Construtor padrão que inicializa uma nova instância do banco de dados vazio.
     */
    public DatabaseBase() {
        this.data = new ArrayList<>();
    }

   /**
     * Construtor que inicializa uma nova instância do banco de dados com uma lista de dados existente.
     *
     * @param data A lista de dados existente a ser armazenada no banco de dados.
     */
    public DatabaseBase(List<T> data) {
        this.data = new ArrayList<>(data);
    }

   /**
     * Obtém todos os dados armazenados no banco de dados.
     *
     * @return Uma lista contendo todos os dados armazenados no banco de dados.
     */
    @Override
    public List<T> getAll() {
       return new ArrayList<>(data);
    }

   /**
     * Atualiza um dado no banco de dados com base em seu ID.
     *
     * @param id       O ID do dado a ser atualizado.
     * @param employee O novo dado a ser armazenado.
     */
    @Override
    public void update(String id, T employee) {
        delete(id);
        save(employee);
    }

   /**
     * Exclui um dado do banco de dados com base em seu ID.
     *
     * @param id O ID do dado a ser excluído.
     */
    @Override
    public void delete(String id) {
        T content = findById(id);

        data.remove(content);
    }

  /**
     * Salva um novo dado no banco de dados.
     *
     * @param content O novo dado a ser armazenado.
     */
    @Override
    public void save(T content) {
        data.add(content);
    }

    /**
     * Localiza um dado no banco de dados com base em um predicado de busca.
     *
     * @param callback O predicado de busca para localizar o dado.
     * @return O primeiro dado encontrado que corresponde ao predicado de busca, ou null se nenhum dado for encontrado.
     */
    @Override
    public T findOne(Predicate<T> callback) {
        return getAll()
                .stream()
                .filter(callback)
                .findAny()
                .orElse(null);
    }

  /**
     * Localiza vários dados no banco de dados com base em um predicado de busca.
     *
     * @param callback O predicado de busca para localizar os dados.
     * @return Uma lista contendo todos os dados encontrados que correspondem ao predicado de busca.
     */
    @Override
    public List<T> findMany(Predicate<T> callback) {
        return getAll()
                .stream()
                .filter(callback)
                .collect(Collectors.toList());
    }

   /**
     * Localiza um dado no banco de dados com base em seu ID.
     *
     * @param id O ID do dado a ser localizado.
     * @return O dado correspondente ao ID, ou null se nenhum dado for encontrado.
     */
    @Override
    public abstract T findById(String id);

  /**
     * Atualiza todos os dados do banco de dados com uma nova lista de dados.
     *
     * @param data A nova lista de dados a serem armazenados no banco de dados.
     */
    @Override
    public void updateAll(List<T> data) {
        this.data = new ArrayList<>(data);
    }

  /**
     * Salva vários dados no banco de dados.
     *
     * @param dataList A lista de dados a serem salvos.
     */
    @Override
    public void saveMany(List<T> dataList) {
        data.addAll(dataList);
    }

  /**
     * Verifica se o banco de dados contém um dado que corresponde a um determinado predicado de busca.
     *
     * @param callback O predicado de busca para verificar a existência do dado.
     * @return true se o banco de dados contém um dado que corresponde ao predicado de busca, caso contrário, false.
     */
    @Override
    public boolean has(Predicate<T> callback) {
        return getAll()
                .stream()
                .anyMatch(callback);
    }

}
