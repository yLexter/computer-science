package menu;

import general.*;
import interfaces.ISubMenu;
import interfaces.ISubMenuOption;
import utils.DataInput;
import utils.Global;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// ToDo Implementar o professor nas opções
public class TeacherMenu implements ISubMenu {

    Teacher teacher = null;

    public TeacherMenu(Teacher teacher) {
        this.teacher = teacher;
    }

    private void OptionRegisterClass() {

            AcademicSystem academicSystem = Global.getAcademicSystem();
            List<CollegeClass> collegeClassesTeatcher =  academicSystem.db.collegeClass
                    .findMany(collegeClass -> collegeClass.getTeacher().getId().equals(teacher.getId()));

            CollegeClass chosenClass = DataInput.getOptionFromListByUser(
                    collegeClassesTeatcher,
                    CollegeClass::getName,
                    "Escolha uma Turma"
            );

           List<SubjectStudent> listCall = chosenClass
                   .getStudents()
                   .stream()
                   .peek(subjectStudent -> {
                       boolean studentMissed = DataInput.getConfirmationByUser(subjectStudent.getStudent().getName());

                       if (studentMissed)
                           subjectStudent.increaseAbsences();

                   }).collect(Collectors.toList());

            academicSystem.db.teachers.saveCall(listCall);
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
