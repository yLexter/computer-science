package interfaces.database;

import general.AcademicSystemSettings;

/**
 * Esta interface representa um banco de dados para as configurações do sistema acadêmico (AcademicSystemSettings).
 * Ela estende a interface IDatabaseBase, fornecendo métodos específicos para as configurações do sistema acadêmico.
 */
public interface IDatabaseAcademicSystemSettings extends IDatabaseBase<AcademicSystemSettings> {

   /**
     * Obtém as configurações do sistema acadêmico.
     *
     * @return As configurações do sistema acadêmico.
     */
    AcademicSystemSettings getData();
}
