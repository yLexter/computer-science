package menu;

import general.*;
import interfaces.ISubMenu;
import interfaces.ISubMenuOption;
import utils.DataInput;
import utils.Role;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import utils.DataInput.ChoiseOption;
import utils.Utils;

// ToDo Pegar as cadeiras do bancos de dados e não do metodo estatico
public class CoordinatorMenu implements ISubMenu {

    private record OptionAddStudent (String label) implements ISubMenuOption {

        @Override
        public void run() {

            Employee employee = Employee.createEmployeeByUser(Role.STUDENT);
            Map<Integer, ChoiseOption<SubjectStudent>> optionsChoiseSubject =
                    DataInput.mapSubjectToSubjectOptions(
                            Utils.getSubjects()
                                    .stream()
                                    .map(Subject::ToSubjectStudent)
                                    .collect(Collectors.toList())
                    );

           List<SubjectStudent> subjects = DataInput.getOptionsByUser(optionsChoiseSubject);

           Student student = new Student(
                employee.getName(),
                employee.getLastName(),
                employee.getAge(),
                employee.getDateOfBirth(),
                employee.getRole(),
                employee.getCpf(),
                subjects,
                "CC"
            );

        }
    }

    private record OptionAddTeatcher (String label) implements ISubMenuOption {

        @Override
        public void run() {

            Employee employee = Employee.createEmployeeByUser(Role.TEACHER);
            Double salary = DataInput.getDataByUser("Digite o salario do professor",
                    (x) -> {
                        Double convert = Double.parseDouble(x);

                        if (convert <= 0)
                            throw new RuntimeException("Salario menor igual a 0");

                        return convert;
                    }
            );


            Teacher teacher = new Teacher(
                    employee.getName(),
                    employee.getLastName(),
                    employee.getAge(),
                    employee.getDateOfBirth(),
                    employee.getRole(),
                    employee.getCpf(),
                    null,
                    salary
            );


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
       new Menu(this).run();
    }

}
