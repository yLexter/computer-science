package menu;

import general.Student;
import interfaces.ISubMenu;
import interfaces.ISubMenuOption;
import utils.DataInput;
import utils.Role;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class CoordinatorMenu implements ISubMenu {

    private record OptionAddStudent (String label) implements ISubMenuOption {

        @Override
        public void run() {

            Student student;
            LocalDate dathOfBirth;
            int age;
            String name, lastName, cpf;

            name = DataInput.getDataByUser("Digite o nome", DataInput::validStringInput);
            lastName = DataInput.getDataByUser("Digite o sobrenome", DataInput::validStringInput);
            cpf = DataInput.getDataByUser("Digite o CPF no formato XXX.XXX.XXX-XX", DataInput::validCpf);
            age = DataInput.getDataByUser("Digite a idade", DataInput::validAge);
            dathOfBirth = DataInput.getDataByUser("Digite a data de nascimento no formato XX/XX/XXXX", DataInput::validDate);


//            student = new Student(
//                 name,
//                 lastName,
//                 age,
//                 dathOfBirth,
//                 Role.STUDENT,
//                 cpf,
//            );

        }
    }

    private record OptionAddTeatcher (String label) implements ISubMenuOption {

        @Override
        public void run() {

        }
    }

    @Override
    public Map<Integer, ISubMenuOption> getOptions() {
        Map<Integer, ISubMenuOption> newOptions = new LinkedHashMap<>();

        newOptions.put(1, new OptionAddStudent("Adicionar estudante"));
        newOptions.put(2, new OptionAddTeatcher("Adicionar Professor"));

        return  newOptions;
    }

    @Override
    public void run() {
        Menu menu = new Menu(getOptions());
        menu.run();
    }

}
