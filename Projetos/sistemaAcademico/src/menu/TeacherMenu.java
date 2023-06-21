package menu;

import general.*;
import interfaces.ISubMenu;
import interfaces.ISubMenuOption;
import utils.DataInput;
import utils.Global;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import general.RegisterClass.StudentCallLog;
import utils.DataEntryValidator;

// ToDo Implementar o professor nas opções
public class TeacherMenu implements ISubMenu {

    private Teacher teacher;

    public TeacherMenu(Teacher teacher) {
        this.teacher = teacher;
    }

    private void optionRegisterClass() {

            List<StudentCallLog> callList = new ArrayList<>();
            AcademicSystem academicSystem = Global.getAcademicSystem();
            Teacher teacher = academicSystem.db.teachers.findById(this.teacher.getId());

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

                  callList.add(
                          new StudentCallLog(student.getId(), studentMissed)
                  );
           }

           academicSystem.db.collegeClass.saveCall(
                 chosenClass,
                 new RegisterClass(classDescription, date, callList, teacher.getId())
           );

    }

    private void optionPostStudentGrade() {

        AcademicSystem academicSystem = Global.getAcademicSystem();

        CollegeClass chosenClass = DataInput.getElementFromListByUser(
                teacher.getCollegeClasses(),
                CollegeClass::toString,
                "Escolha uma Turma"
        );

        List<SubjectStudent> students = chosenClass.getStudents();

        for (SubjectStudent subjectStudent : students) {

             System.out.printf("Aluno: %s", subjectStudent.getStudent().getFullName());

             float nota1 = DataInput.getDataByUser("Digite a nota 1", Float::parseFloat, DataEntryValidator::validNote);
             float nota2 = DataInput.getDataByUser("Digite a nota 2", Float::parseFloat, DataEntryValidator::validNote);

             subjectStudent.setNote1(nota1);
             subjectStudent.setNote2(nota2);
        }

        academicSystem.db.collegeClass.update(chosenClass.getClassId(), chosenClass);
    }

    private void optionShowClassReport() {

        AcademicSystem academicSystem = Global.getAcademicSystem();

        CollegeClass chosenClass = DataInput.getElementFromListByUser(
                teacher.getCollegeClasses(),
                CollegeClass::toString,
                "Escolha uma Turma"
        );





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
    public void run() {
        new MenuExecutor(this).run();
    }

}
