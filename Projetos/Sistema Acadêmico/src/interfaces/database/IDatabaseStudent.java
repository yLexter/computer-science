package interfaces.database;

import general.Student;

/**
 * Esta interface representa um banco de dados para os estudantes (Student).
 * Ela estende a interface IDatabaseEmployee, fornecendo métodos específicos para os estudantes.
 *
 * @param <Student> O tipo de estudante a ser manipulado.
 */
public interface IDatabaseStudent extends IDatabaseEmployee<Student> {
    /**
     * Remove uma turma de colégio dos estudantes.
     *
     * @param collegeClassId O ID da turma de colégio a ser removida dos estudantes.
     */
    void removeCollegeClassFromStudents(String collegeClassId);
}
