package menu;

import general.*;
import interfaces.ISubMenu;
import interfaces.ISubMenuOption;
import utils.DataInput;
import utils.Global;
import utils.Role;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import utils.DataInput.ChoiseOption;
import utils.Utils;

// ToDo Pegar as cadeiras do bancos de dados e não do metodo estatico
// ToDo implementar uma forma de passar o academy system para as classes menu
public class AdminMenu implements ISubMenu {

    private Admin admin;

    public AdminMenu(Admin admin) {
        this.admin = admin;
    }

    private void optionShowStudents() {}

    private void optionAddStudent() {

        Employee employee = Employee.createEmployeeByUser(Role.STUDENT);
        AcademicSystem academicSystem = Global.getAcademicSystem();

        Map<Integer, ChoiseOption<SubjectStudent>> optionsChoiseSubject =
                DataInput.mapSubjectToSubjectOptions(
                        academicSystem.db.subjects.getAll()
                                .stream()
                                .map(Subject::createSubjectStudent)
                                .collect(Collectors.toList())
                );

       List<SubjectStudent> subjects = DataInput.getOptionsByUser(optionsChoiseSubject, "Escolha a cadeoras");

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

       academicSystem.db.students.save(student);
    }

    private void optionDeleteStudent() {
        AcademicSystem academicSystem = Global.getAcademicSystem();

        Student studentRemoved = DataInput.getOptionFromListByUser(
            academicSystem.db.students.getAll(),
                Student::getName,
                "Escolha o estudante"
        );

        academicSystem.db.students.delete(studentRemoved.getId());

        System.out.println("Estudante Removido");
    }

    private void optionDeleteTeatcher() {
        AcademicSystem academicSystem = Global.getAcademicSystem();

        Teacher studentRemoved = DataInput.getOptionFromListByUser(
                academicSystem.db.teachers.getAll(),
                Teacher::getName,
                "Escolha o Professor`"
        );

        academicSystem.db.teachers.delete(studentRemoved.getId());

        System.out.println("Professor Removido");
    }

    private void optionAddTeatcher() {

        AcademicSystem academicSystem = Global.getAcademicSystem();
        Employee employee = Employee.createEmployeeByUser(Role.TEACHER);

        Double salary = DataInput.getDataByUser("Digite o salario do professor",
                (x) -> {
                    double convert = Double.parseDouble(x);

                    if (convert <= 0)
                        throw new IllegalArgumentException("Salario menor igual a 0");

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

        academicSystem.db.teachers.save(teacher);

    }

    private void optionShowTeatchers() {}

    @Override
    public Map<Integer, ISubMenuOption> getOptions() {
        Map<Integer, ISubMenuOption> options = new LinkedHashMap<>();

        options.put(1, new OptionMenu("Ver Estudantes", this::optionShowStudents));
        options.put(2, new OptionMenu("Adicionar estudante", this::optionAddStudent));
        options.put(3, new OptionMenu("Remover estudante", this::optionDeleteStudent));

        options.put(6, new OptionMenu("Mostrar Professores", this::optionShowTeatchers));
        options.put(4, new OptionMenu("Adicionar Professor", this::optionAddTeatcher));
        options.put(5, new OptionMenu("Remover Professor", this::optionDeleteTeatcher));

        return options;
    }

    @Override
    public void run() {
       new Menu(this).run();
    }

}
