package database.DatabaseMemory;

import general.*;
import interfaces.database.IDatabaseCollegeClass;
import menu.TeacherMenu.StudentGradeRecord;
import general.RegisterClass.StudentCallLog;
import utils.Global;
import utils.StudentSubjectStatus;

import java.util.ArrayList;
import java.util.List;


public class DatabaseCollegeClass extends DatabaseBase<CollegeClass> implements IDatabaseCollegeClass {

  /**
     * Localiza uma classe universitária no banco de dados com base em seu ID.
     *
     * @param id O ID da classe universitária a ser localizada.
     * @return A classe universitária correspondente ao ID, ou null se nenhuma classe for encontrada.
     */
    @Override
    public CollegeClass findById(String id) {
        return findOne(collegeClass -> collegeClass.getCollegeClassId().equals(id));
    }

    /**
     * Salva uma chamada de presença para uma classe universitária.
     *
     * @param collegeClassId O ID da classe universitária.
     * @param registerClass  A chamada de presença a ser salva.
     */
    @Override
    public void saveCall(String collegeClassId, RegisterClass registerClass) {
         CollegeClass collegeClass = findById(collegeClassId);

         for (StudentCallLog studentCallLog : registerClass.getListCall()) {

             SubjectStudent subjectStudent = collegeClass
                     .getStudents()
                     .stream()
                     .filter(subjectStudent1 -> subjectStudent1.getStudentId().equals(studentCallLog.id()))
                     .findFirst()
                     .orElse(null);

             if (subjectStudent != null && studentCallLog.isMissed())
                subjectStudent.increaseAbsences();

         }

         collegeClass.getRegisterClasses().add(registerClass);
    }

   /**
     * Adiciona um estudante a várias classes universitárias.
     *
     * @param student        O estudante a ser adicionado.
     * @param collegeClasses As classes universitárias às quais o estudante será adicionado.
     */
    @Override
    public void addStudentToCollegesClasses(Student student, List<CollegeClass> collegeClasses) {

        for(CollegeClass collegeClass : collegeClasses) {

            SubjectStudent subjectStudent = Subject.studentToSubjectStudent(
                    collegeClass,
                    student,
                    collegeClass.getCollegeClassId()
            );

           collegeClass.addSubjectStudent(subjectStudent);

           update(collegeClass.getCollegeClassId(), collegeClass);
        }

    }

    /**
     * Remove um estudante de uma classe universitária.
     *
     * @param collegClass O ID da classe universitária.
     * @param studentId O ID do estudante a ser removido.
     */
    public void removeLSL(String collegClass, String studentId) {
        CollegeClass collegeClass = findById(collegClass);
        List<SubjectStudent> students = collegeClass.getStudents();

        for(SubjectStudent subjectStudent : students) {

            if (subjectStudent.getStudentId().equals(studentId)) {
                students.remove(subjectStudent);
                break;
            }


        }


    }

  /**
     * Remove um estudante de uma classe universitária.
     *
     * @param collegClass O ID da classe universitária.
     * @param studentId   O ID do estudante a ser removido.
     */
    @Override
    public void removeStudentFromCollegeClasses(String studentId) {

        for(CollegeClass collegeClass : getAll()) {
            collegeClass
                    .getStudents()
                    .stream()
                    .filter(x -> x.getStudent().getId().equals(studentId))
                    .findAny()
                    .ifPresent(subjectStudent -> collegeClass.getStudents().remove(subjectStudent));
        }

    }

  /**
     * Remove um professor de todas as classes universitárias.
     *
     * @param teacherId O ID do professor a ser removido.
     */
    @Override
    public void removeTeacherFromCollegeClasses(String teacherId) {

        for(CollegeClass collegeClass : getAll()) {

            String idTeacherCollegeClass = collegeClass.getTeacher().getId();

            if (idTeacherCollegeClass.equals(teacherId))
                collegeClass.setTeacherId(null);

        }
    }

  /**
     * Adiciona um professor a várias classes universitárias.
     *
     * @param collegeClasses As classes universitárias às quais o professor será adicionado.
     * @param teacherId      O ID do professor.
     */
    @Override
    public void addTeacherFromCollegeClasses(List<CollegeClass> collegeClasses, String teacherId) {

        for(CollegeClass collegeClass : collegeClasses)
            collegeClass.setTeacherId(teacherId);

    }


  /**
     * Salva as notas dos estudantes em uma classe universitária.
     *
     * @param studentGradeRecords As notas dos estudantes.
     * @param collegeClassId      O ID da classe universitária.
     */
    @Override
    public void saveNotes(List<StudentGradeRecord> studentGradeRecords, String collegeClassId) {
       CollegeClass collegeClass = findById(collegeClassId);

       for (StudentGradeRecord studentGradeRecord : studentGradeRecords) {

          SubjectStudent subjectStudent = collegeClass
                  .getStudents()
                  .stream()
                  .filter(subjectStudent1 -> subjectStudent1.getStudentId().equals(studentGradeRecord.id))
                  .findFirst()
                  .orElse(null);

          if (subjectStudent != null) {
              subjectStudent.setNote1(studentGradeRecord.note1);
              subjectStudent.setNote2(studentGradeRecord.note2);
              subjectStudent.setFinalExameScore(studentGradeRecord.finalExam);
          }

       }
    }

   /**
     * Obtém todos os SubjectStudents de um estudante em todas as classes universitárias.
     *
     * @param studentId O ID do estudante.
     * @return Uma lista de SubjectStudents correspondentes ao estudante.
     */
    @Override
    public ArrayList<SubjectStudent> getAllSubjectStudentOfStudent(String studentId) {
        return getAll()
                .stream()
                .reduce(new ArrayList<>(), (acc, collegeClass) -> {

                   for(SubjectStudent subjectStudent : collegeClass.getStudents()) {
                       if (subjectStudent.getStudentId().equals(studentId))
                          acc.add(subjectStudent);
                   }

                  return acc;
                }, (acc1, acc2) -> acc1);
    }


   /**
     * Registra as notas dos estudantes no histórico e atualiza o status da aprovação.
     *
     * @param studentGradeRecords As notas dos estudantes.
     * @param collegeClassId      O ID da classe universitária.
     */
    @Override
    public void recordInStudentHistory(List<StudentGradeRecord> studentGradeRecords, String collegeClassId) {

        AcademicSystem academicSystem = Global.getAcademicSystem();
        CollegeClass collegeClass = findById(collegeClassId);

        for (StudentGradeRecord studentGradeRecord : studentGradeRecords) {
            Student student = academicSystem.db.students.findById(studentGradeRecord.id);

            SubjectStudent subjectStudent = collegeClass
                    .getStudents()
                    .stream()
                    .filter(subjectStudent1 -> subjectStudent1.getStudentId().equals(studentGradeRecord.id))
                    .findFirst()
                    .orElse(null);

            if (subjectStudent == null)
                continue;

            if (subjectStudent.getAverage() >= academicSystem.getSettings().getMinimumAverage()) {
                subjectStudent.setStatus(StudentSubjectStatus.APPROVED);
                student.addInHistoric(subjectStudent);
                removeLSL(collegeClassId, student.getId());
                continue;
            }

            if (subjectStudent.getFinalExameScore() != null) {
               boolean isPassed = subjectStudent.checkIsPassing();

                if (isPassed) {
                    subjectStudent.setStatus(StudentSubjectStatus.APPROVED);
                } else {
                    subjectStudent.setStatus(StudentSubjectStatus.DISAPPROVED);
                }

                student.addInHistoric(subjectStudent);
                removeLSL(collegeClassId, student.getId());
            }

    }

    }


}
