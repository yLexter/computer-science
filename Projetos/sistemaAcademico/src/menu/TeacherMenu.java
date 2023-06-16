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

    private record OptionRegisterClass(String label) implements ISubMenuOption {


        @Override
        public void run() {

            Teacher teacher = null;

            Map<Integer, ChoiseOption<CollegeClass>> collegeClassMap = DataInput.convertListToOptionMap(
                teacher.getCollegeClasses(), CollegeClass::getName
            );

            CollegeClass chosenClass = DataInput.getOptionByUser(collegeClassMap, "Escolha uma Turma");



        }

    }

    @Override
    public Map<Integer, ISubMenuOption> getOptions() {

        Map<Integer, ISubMenuOption> newOptions = new LinkedHashMap<>();

        newOptions.put(1, new OptionRegisterClass("Registrar Aula"));

        return  newOptions;
    }

    @Override
    public void run() {
        new Menu(this).run();
    }

}
