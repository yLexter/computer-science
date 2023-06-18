package menu;

import general.*;
import interfaces.ISubMenu;
import interfaces.ISubMenuOption;
import utils.DataInput;
import utils.Global;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import general.RegisterClass.StudentCallLog;

// ToDo Implementar o professor nas opções
public class TeacherMenu implements ISubMenu {

    private Teacher teacher;

    public TeacherMenu(Teacher teacher) {
        this.teacher = teacher;
    }

    private void OptionRegisterClass() {

            List<StudentCallLog> callList = new ArrayList<>();
            AcademicSystem academicSystem = Global.getAcademicSystem();
            Teacher teacher = academicSystem.db.teachers.findById(this.teacher.getId());

            CollegeClass chosenClass = DataInput.getOptionFromListByUser(
                    teacher.getCollegeClass(),
                    CollegeClass::getName,
                    "Escolha uma Turma"
            );

           LocalDate date = DataInput.getDataByUser(
                   "Digite a data da aula",
                   DataInput::validDate
           );

           String classDescription = DataInput.getDataByUser(
               "Digite o contéudo da aula",
               DataInput::validStringInput
            );

           for (SubjectStudent subjectStudent : chosenClass.getStudents()) {
                  Student student = subjectStudent.getStudent();
                  boolean studentMissed = DataInput.getConfirmationByUser("O Aluno %s faltou?".formatted(student.getName()));
                  callList.add(new StudentCallLog( student.getId(), studentMissed));
           }

           academicSystem.db.teachers.saveCall(
                   new RegisterClass(classDescription, date, callList, teacher.getId())
           );
    }

    @Override
    public Map<Integer, ISubMenuOption> getOptions() {

        Map<Integer, ISubMenuOption> newOptions = new LinkedHashMap<>();

        newOptions.put(1, new OptionMenu("Registrar Aula", this::OptionRegisterClass));

        return  newOptions;
    }

    @Override
    public void run() {
        new MenuExecutor(this).run();
    }

}
