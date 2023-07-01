package interfaces.database;

import general.Teacher;

/**
 * Esta interface representa um banco de dados para os professores (Teacher).
 * Ela estende a interface IDatabaseEmployee, fornecendo operações de banco de dados específicas para os professores.
 *
 * @param <Teacher> O tipo de professor a ser manipulado.
 */
public interface IDatabaseTeacher extends IDatabaseEmployee<Teacher> {}
