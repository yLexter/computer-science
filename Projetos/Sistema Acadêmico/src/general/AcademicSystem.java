package general;

import database.Database;
import erros.DatabaseException;
import utils.Constants;

import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Essa classe representa o sistema acadêmico em si. Ela lida com a autenticação de funcionários e fornece acesso às configurações e ao período atual.
 * 
 * O objeto 'db' representa o banco de dados usado pelo sistema.
 */
public class AcademicSystem {

    /**
     * O banco de dados usado pelo sistema acadêmico.
     */
    public final Database db;

    /**
     * Cria uma instância do sistema acadêmico com o banco de dados fornecido.
     *
     * @param db O banco de dados a ser utilizado pelo sistema.
     */
    public AcademicSystem(Database db) {
        this.db = db;
    }

    /**
     * Autentica um funcionário com base no ID e na senha fornecidos.
     * A autenticação é realizada primeiro para o administrador, depois para o aluno e, em seguida, para o professor.
     *
     * @param id O ID do funcionário a ser autenticado.
     * @param password A senha do funcionário a ser autenticado.
     * @return O funcionário autenticado (administrador, aluno ou professor).
     * @throws DatabaseException Se nenhum funcionário for autenticado com sucesso.
     */
    public Employee autenticate(String id, String password) {

        // Tenta autenticar como administrador
        Employee isAdmin = db.admin.authenticate(id, password);

        if (isAdmin != null)
            return isAdmin;

        // Tenta autenticar como aluno
        Employee isStudent = db.students.authenticate(id, password);

        if (isStudent != null)
            return isStudent;

        // Tenta autenticar como professor
        Employee isTeacher = db.teachers.authenticate(id, password);

        if (isTeacher != null)
            return isTeacher;

        // Se nenhum funcionário for autenticado, lança uma exceção indicando falha na autenticação.
        throw new DatabaseException(Constants.ErrorMessage.EMPLOYEE_NOT_AUTHENTICATE);
    }

    /**
     * Obtém as configurações do sistema acadêmico.
     *
     * @return As configurações do sistema acadêmico.
     */
    public AcademicSystemSettings getSettings() {
        return db.AcademicSystemSettings.getData();
    }

    /**
     * Obtém o período acadêmico atual.
     *
     * @return O período acadêmico atual no formato "ano.período" (ex: "2023.1" ou "2023.2").
     */
    public String getCurrentPeriod() {
        LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());
        String currentYear = Integer.toString(currentDate.getYear());
        String currentPeriod = currentDate.getMonthValue() > 6 ? "2" : "1";

        return String.format("%s.%s", currentYear, currentPeriod);
    }

}
