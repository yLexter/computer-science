package interfaces.database;

import general.Subject;

/**
 * Esta interface representa um banco de dados para as disciplinas (Subject).
 * Ela estende a interface IDatabaseBase, fornecendo operações básicas de banco de dados para as disciplinas.
 *
 * @param <Subject> O tipo de disciplina a ser manipulada.
 */

public interface IDatabaseSubject extends IDatabaseBase<Subject>{}
