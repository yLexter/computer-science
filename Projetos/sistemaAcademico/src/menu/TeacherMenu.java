package menu;

import general.*;
import interfaces.menu.IMenuEmployee;
import interfaces.menu.ISubMenuOption;
import utils.DataInput;
import utils.Global;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import general.RegisterClass.StudentCallLog;
import utils.DataEntryValidator;
import utils.Utils;

// ToDo Consertar bug de duplicidade de dados, pegar os dados da db
public class TeacherMenu implements IMenuEmployee<Teacher> {

    private String teacherId;

    public TeacherMenu(String teacherId) {
        this.teacherId = teacherId;
    }

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
                   DataEntryValidator::validDate
           );

           String classDescription = DataInput.getDataByUser(
               "Digite o contéudo da aula",
               DataEntryValidator::validStringInput
            );

           for (SubjectStudent subjectStudent : chosenClass.getStudents()) {
                  Student student = subjectStudent.getStudent();
                  boolean studentMissed = DataInput.getConfirmationByUser("O Aluno %s faltou?".formatted(student.getFullName()));

                  callList.add(new StudentCallLog(student.getId(), studentMissed));
           }

           academicSystem.db.collegeClass.saveCall(
                 chosenClass,
                 new RegisterClass(classDescription, date, callList, teacher.getId())
           );

    }

    private void optionPostStudentGrade() {

        Teacher teacher = getUser();
        AcademicSystem academicSystem = Global.getAcademicSystem();

        CollegeClass chosenClass = DataInput.getElementFromListByUser(
                teacher.getCollegeClasses(),
                CollegeClass::toString,
                "Escolha uma Turma"
        );

        List<SubjectStudent> students = chosenClass.getStudents();

        for (SubjectStudent subjectStudent : students) {

            Student student = subjectStudent.getStudent();

            System.out.printf("Aluno: %s\n\n", student.getFullName());

            float note1 = DataInput.getDataByUser("Digite a nota 1", Float::parseFloat, DataEntryValidator::validNote);
            float note2 = DataInput.getDataByUser("Digite a nota 2", Float::parseFloat, DataEntryValidator::validNote);

            subjectStudent.setNote1(note1);
            subjectStudent.setNote2(note2);
        }

        academicSystem.db.collegeClass.update(chosenClass.getClassId(), chosenClass);
    }

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
                    Utils.numberToString(subjectStudent.getFinalExameScore()),
                    Utils.numberToString(subjectStudent.getAverage()),
                    subjectStudent.getAbsences(),
                    subjectStudent.getStatus().get(),
                    subjectStudent.getPeriod()
            );
        });

        Utils.printTable(students, callBack, headers);
    }

    @Override
    public List<ISubMenuOption> getOptions() {

        return List.of(
                new OptionMenu("Registrar Aula", this::optionRegisterClass),
                new OptionMenu("Postar Nota", this::optionPostStudentGrade),
                new OptionMenu("Mostrar relatorio de turma", this::optionShowClassReport)
        );

    }

    @Override
    public Teacher getUser() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        return academicSystem.db.teachers.findById(teacherId);
    }

    @Override
    public void run() {
        new MenuExecutor(this).run();
    }

}

// rm -r database erros general interfaces menu utils && unzip src.zip