package database.DatabaseMemory;

import general.AcademicSystemSettings;
import interfaces.database.IDatabaseAcademicSystemSettings;

/**
 * Esta classe representa uma implementação específica de um banco de dados para armazenar e acessar as configurações do sistema acadêmico.
 * Ela estende a classe DatabaseBase e implementa a interface IDatabaseAcademicSystemSettings.
 */
public class DatabaseAcademicSystemSettings extends DatabaseBase<AcademicSystemSettings> implements IDatabaseAcademicSystemSettings {

    /**
     * O objeto de dados que armazena as configurações do sistema acadêmico.
     */
    public final AcademicSystemSettings data;

    /**
     * Constrói uma nova instância de DatabaseAcademicSystemSettings com os dados especificados.
     *
     * @param data Os dados das configurações do sistema acadêmico a serem armazenados no banco de dados.
     */
    public DatabaseAcademicSystemSettings(AcademicSystemSettings data) {
        this.data = data;
    }

    /**
     * Recupera as configurações do sistema acadêmico pelo ID.
     *
     * @param id O ID das configurações do sistema acadêmico a serem recuperadas.
     * @return As configurações do sistema acadêmico correspondentes ao ID fornecido, ou null se não encontradas.
     */
    @Override
    public AcademicSystemSettings findById(String id) {
        return null;
    }

    /**
     * Retorna as configurações do sistema acadêmico armazenadas no banco de dados.
     *
     * @return As configurações do sistema acadêmico.
     */
    @Override
    public AcademicSystemSettings getData() {
        return data;
    }
}
