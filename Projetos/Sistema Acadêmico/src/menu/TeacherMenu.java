package menu;

import general.*;
import interfaces.menu.IMenuEmployee;
import interfaces.menu.ISubMenuOption;
import utils.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import general.RegisterClass.StudentCallLog;

// ToDo Consertar bug de duplicidade de dados, pegar os dados da db
public class TeacherMenu implements IMenuEmployee<Teacher> {

  /**
 * Classe para armazenar o registro de notas de um estudante.
 */
    public static class StudentGradeRecord {

        public final String id;
        public final Float note1;
        public final Float note2;
        public final Float finalExam;


    /**
     * Construtor para criar um registro de notas de estudante.
     *
     * @param id         O identificador do estudante.
     * @param note1      A primeira nota do estudante.
     * @param note2      A segunda nota do estudante.
     * @param finalExam  A nota da prova final do estudante.
     */
        public StudentGradeRecord(String id, Float note1, Float note2, Float finalExam) {
            this.id = id;
            this.note1 = note1;
            this.note2 = note2;
            this.finalExam = finalExam;
        }

      /**
     * Construtor da classe StudentGradeRecord.
     *
     * @param id     O identificador do estudante.
     * @param note1  A primeira nota do estudante.
     * @param note2  A segunda nota do estudante.
     */
        public StudentGradeRecord(String id, Float note1, Float note2) {
            this.id = id;
            this.note1 = note1;
            this.note2 = note2;
            this.finalExam = null;
        }


    }

    private String teacherId;

  /**
     * Construtor para criar um menu de professor.
     * @param teacherId O identificador do professor.
     */
    public TeacherMenu(String teacherId) {
        this.teacherId = teacherId;
    }

  /** 
    Método responsável por permitir o registro de aula por parte dos professores
  */
    private void optionRegisterClass() {

            Teacher teacher =getUser();
            List<StudentCallLog> callList = new ArrayList<>();
            AcademicSystem academicSystem = Global.getAcademicSystem();

            CollegeClass chosenClass = DataInput.getElementFromListByUser(
                    teacher.getCollegeClasses(),
                    CollegeClass::toString,
                    "Escolha uma Turma"
            );

           LocalDate date = DataInput.getDataByUser(
                   "Digite a data da aula",
                   DataEntryValidator::validDateIsNotFromTheFuture
           );

           String classDescription = DataInput.getDataByUser(
               "Digite o contéudo da aula",
               DataEntryValidator::validStringIsNotEmpty
            );

           for (SubjectStudent subjectStudent : chosenClass.getStudents()) {
                  Student student = subjectStudent.getStudent();
                  boolean isJustified = false;
                  boolean studentMissed = DataInput.getConfirmationByUser("O Aluno %s faltou?".formatted(student.getFullName()));

                  if (studentMissed)
                      isJustified = DataInput.getConfirmationByUser("O Aluno %s Justificou?".formatted(student.getFullName()));;

                  callList.add(new StudentCallLog(student.getId(), studentMissed, isJustified));
           }

           academicSystem.db.collegeClass.saveCall(
                 chosenClass.getCollegeClassId(),
                 new RegisterClass(classDescription, date, callList, teacher.getId())
           );

        Decoration.showMessageAndClearScreen("Registro de aula feito com sucesso");
    }

  /** Método responsável por permitir ao professor enviar as 
  notas dos alunos para o sistema
  */
    private void optionPostStudentGrade() {

        Teacher teacher = getUser();
        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<SubjectStudent> students;
        ArrayList<StudentGradeRecord> studentGradeRecords = new ArrayList<>();

        CollegeClass chosenClass = DataInput.getElementFromListByUser(
                teacher.getCollegeClasses(),
                CollegeClass::toString,
                "Escolha uma Turma"
        );

         boolean isAllStudents = DataInput.getConfirmationByUser("Deseja selecionar alunos especificos?");

         if (isAllStudents) {
             students = DataInput.getElementsFromListByUser(
                     chosenClass.getStudents(),
                     (subjectStudent -> subjectStudent.getStudent().toString()),
                     "Escolha o aluno"
             );
         } else {
             students = chosenClass.getStudents();
         }

         for (SubjectStudent subjectStudent : students) {

            Student student = subjectStudent.getStudent();

            System.out.printf("Aluno: %s\n\n", student.getFullName());

            float note1 = DataInput.getDataByUser("Digite a nota 1", Float::parseFloat, DataEntryValidator::validNote);
            float note2 = DataInput.getDataByUser("Digite a nota 2", Float::parseFloat, DataEntryValidator::validNote);

            studentGradeRecords.add(
                new StudentGradeRecord(
                     student.getId(),
                     note1,
                     note2
             ));
        }

        academicSystem.db.collegeClass.saveNotes(studentGradeRecords, chosenClass.getCollegeClassId());
        academicSystem.db.collegeClass.recordInStudentHistory(studentGradeRecords, chosenClass.getCollegeClassId());

        Decoration.showMessageAndClearScreen("Nota Atualizada Com Sucesso");
    }
    private void optionPostFinalNote() {
        Teacher teacher = getUser();
        AcademicSystem academicSystem = Global.getAcademicSystem();
        ArrayList<StudentGradeRecord> studentGradeRecords = new ArrayList<>();

        CollegeClass chosenClass = DataInput.getElementFromListByUser(
                teacher.getCollegeClasses(),
                CollegeClass::toString,
                "Escolha uma Turma"
        );

        List<SubjectStudent> studentsInFinal = chosenClass.
                getStudentsInFinal()
                .stream()
                .filter(subjectStudent -> subjectStudent.getNote1() != null && subjectStudent.getNote2() != null)
                .toList();

        if (studentsInFinal.size() == 0) {
            Decoration.showMessageAndClearScreen("Poste as notas dos estudantes primeiros");
            return;
        }

        for (SubjectStudent subjectStudent : studentsInFinal) {
            Student student = subjectStudent.getStudent();
            System.out.printf("Aluno: %s\n\n", student.getFullName());

            float finalNote = DataInput.getDataByUser("Digite a final", Float::parseFloat, DataEntryValidator::validNote);

            studentGradeRecords.add(new StudentGradeRecord(
                 student.getId(),
                 subjectStudent.getNote1(),
                 subjectStudent.getNote2(),
                 finalNote
            ));
        }

        academicSystem.db.collegeClass.saveNotes(studentGradeRecords, chosenClass.getCollegeClassId());
        academicSystem.db.collegeClass.recordInStudentHistory(studentGradeRecords, chosenClass.getCollegeClassId());

        Decoration.showMessageAndClearScreen("Nota Final Atualizada Com Sucesso");
    }

    /** Método responsável por mostrar o relatório da turma
    */
    private void optionShowClassReport() {

        Teacher teacher = getUser();

        CollegeClass chosenClass = DataInput.getElementFromListByUser(
                teacher.getCollegeClasses(),
                CollegeClass::toString,
                "Escolha uma Turma"
        );

        List<SubjectStudent> students = chosenClass.getStudents();

        List<String> headers = List.of(
                "Matricula",
                "Nome",
                "1° Nota",
                "2° Nota",
                "Final",
                "Média",
                "Faltas",
                "Status",
                "Período"
        );

        Function<SubjectStudent, List<?>> callBack = (subjectStudent -> {
            Student student = subjectStudent.getStudent();
            List<String> notes = subjectStudent.getListNotes();

            return List.of(
                    student.getId(),
                    student.getFullName(),
                    notes.get(0),
                    notes.get(1),
                    Utils.objectToString(subjectStudent.getFinalExameScore()),
                    Utils.objectToString(subjectStudent.getAverage()),
                    subjectStudent.getAbsences(),
                    subjectStudent.getStatus().get(),
                    subjectStudent.getPeriod()
            );
        });

        Utils.printTable(students, callBack, headers);
    }

  /** 
    Obtem os registros de aula
  */
    private void showRegisterClass() {

        Teacher teacher = getUser();

        CollegeClass chosenClass = DataInput.getElementFromListByUser(
                teacher.getCollegeClasses(),
                CollegeClass::toString,
                "Escolha uma Turma"
        );

        List<RegisterClass> registerClasses = chosenClass.getRegisterClasses();

        List<String> headers = List.of(
                "Data",
                "Descrição",
                "Total de Faltas"
        );

        Function<RegisterClass, List<?>> callBack = (registerClass -> {
            return List.of(
                  Utils.formatDate(registerClass.getDate()),
                  registerClass.getDescription(),
                  registerClass.getTotalStudentsSissed()
            );
        });

        Utils.printTable(registerClasses, callBack, headers);
    }

  /** 
    Obtem as opções do menu
    @return a lista de opções
  */
    @Override
    public List<ISubMenuOption> getOptions() {

        return List.of(
                new OptionMenu("Registrar Aula", this::optionRegisterClass),
                new OptionMenu("Postar Notas", this::optionPostStudentGrade),
                new OptionMenu("Postar Notas Final", this::optionPostFinalNote),
                new OptionMenu("Mostrar relatorio de turma", this::optionShowClassReport),
                new OptionMenu("Ver Registros de Aula", this::showRegisterClass)
        );

    }

  /** 
    Obtem o cabeçalho do menu
    @return o cabeçalho
  */
    @Override
    public String getHeader() {
        Teacher teacher = getUser();
        return Decoration.generateWelcomeHeader(teacher.getFullName());
    }

  /** 
    Obtem o professor no banco de dados
    @return retorna o professor no banco de dados
  */
    @Override
    public Teacher getUser() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        return academicSystem.db.teachers.findById(teacherId);
    }

  /** 
    Executa o menu
  */
    @Override
    public void run() {
        new MenuExecutor(this).run();
    }

}

// rm -r database erros general interfaces menu utils && unzip src.zip