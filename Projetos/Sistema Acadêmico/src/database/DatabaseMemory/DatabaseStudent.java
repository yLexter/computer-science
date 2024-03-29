package database.DatabaseMemory;

import general.Student;
import interfaces.database.IDatabaseStudent;

/**
 * Esta é uma classe que representa um banco de dados de estudantes.
 * Ela estende a classe `DatabaseEmployee` e implementa a interface `IDatabaseStudent`.
 * 
 * Aqui são fornecidas operações de banco de dados específicas para estudantes.
 * A classe genérica `Student` é utilizada para representar estudantes.
 * 
 * Os métodos nesta classe permitem a remoção de uma disciplina de uma lista de disciplinas de um estudante.
 */
public class DatabaseStudent extends DatabaseEmployee<Student> implements IDatabaseStudent {

  /**
     * Remove uma disciplina da lista de disciplinas de todos os estudantes.
     *
     * @param collegeClassId O ID da disciplina a ser removida.
     */
    @Override
    public void removeCollegeClassFromStudents(String collegeClassId) {
        for(Student student : getAll()) {
               student
                    .getCollegeClasses()
                    .stream()
                    .filter(id -> id.equals(collegeClassId))
                    .findAny()
                    .ifPresent(id -> student.getCollegeClasses().remove(id));
        }
    }


}
