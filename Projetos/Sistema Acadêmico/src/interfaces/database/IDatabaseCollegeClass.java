package interfaces.database;

import general.CollegeClass;
import general.RegisterClass;
import general.Student;
import general.SubjectStudent;
import menu.TeacherMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta interface representa um banco de dados para as turmas da faculdade (CollegeClass).
 * Ela estende a interface IDatabaseBase, fornecendo métodos específicos para as turmas da faculdade.
 */
public interface IDatabaseCollegeClass extends IDatabaseBase<CollegeClass> {
    /**
     * Salva a chamada de presença para uma turma específica.
     *
     * @param collegeClassId O ID da turma.
     * @param registerClass  O registro da chamada de presença.
     */
    void saveCall(String collegeClassId, RegisterClass registerClass);

   /**
     * Obtém todas as matérias/estudantes de um determinado aluno em todas as turmas.
     *
     * @param studentId O ID do aluno.
     * @return Uma lista de SubjectStudent contendo as matérias/estudantes do aluno.
     */
    ArrayList<SubjectStudent> getAllSubjectStudentOfStudent(String studentId);

    /**
     * Adiciona um aluno a várias turmas.
     *
     * @param student        O aluno a ser adicionado.
     * @param collegeClasses As turmas em que o aluno será adicionado.
     */
  
    void addStudentToCollegesClasses(Student student, List<CollegeClass> collegeClasses);

    /**
     * Remove um aluno de todas as turmas.
     *
     * @param studentId O ID do aluno a ser removido.
     */
  
    void removeStudentFromCollegeClasses(String studentId);

   /**
     * Remove um professor de todas as turmas.
     *
     * @param teacherId O ID do professor a ser removido.
     */
  
    void removeTeacherFromCollegeClasses(String teacherId);

   /**
     * Adiciona um professor em várias turmas.
     *
     * @param collegeClasses As turmas em que o professor será adicionado.
     * @param teacherId      O ID do professor a ser adicionado.
     */

    void addTeacherFromCollegeClasses(List<CollegeClass> collegeClasses, String teacherId);

   /**
     * Salva as notas dos alunos de uma turma.
     *
     * @param studentGradeRecords As notas dos alunos.
     * @param collegeClassId      O ID da turma.
     */
    void saveNotes(List<TeacherMenu.StudentGradeRecord> list, String collegeClassId);

     /**
     * Registra o histórico de notas de um aluno em uma turma.
     *
     * @param studentGradeRecords As notas do aluno.
     * @param collegeClassId      O ID da turma.
     */
    void recordInStudentHistory(List<TeacherMenu.StudentGradeRecord> studentGradeRecords, String collegeClassId);
}
