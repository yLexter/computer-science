package menu;

import general.CollegeClass;
import general.Subject;
import general.Teacher;
import interfaces.ISubMenu;
import interfaces.ISubMenuOption;
import utils.DataInput;
import utils.DataInput.ChoiseOption;
import java.util.LinkedHashMap;
import java.util.Map;

// ToDo Implementar o professor nas opções
public class TeacherMenu implements ISubMenu {

    Teacher teacher = null;

    public TeacherMenu(Teacher teacher) {
        this.teacher = teacher;
    }

    private void OptionRegisterClass() {

            CollegeClass chosenClass = DataInput.getOptionFromListByUser(
                    teacher.getCollegeClasses(),
                    CollegeClass::getName,
                    "Escolha uma Turma"
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
        new Menu(this).run();
    }

}
