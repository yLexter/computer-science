package menu;

import database.Database.AllData;
import general.*;
import interfaces.ISubMenu;
import interfaces.ISubMenuOption;
import utils.DataInput;
import utils.Global;
import utils.Role;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import utils.DataInput.ChoiseOption;


// ToDo melhorar a forma  de pegar os horarios
public class AdminMenu implements ISubMenu {

    private final Admin admin;

    public AdminMenu(Admin admin) {
        this.admin = admin;
    }

    private void optionShowStudents() {}

    private void optionAddStudent() {

        Employee employee = Employee.createEmployeeByUser(Role.STUDENT);

        AcademicSystem academicSystem = Global.getAcademicSystem();

        List<CollegeClass> subjects = DataInput.getOptionsFromListByUser(
                academicSystem.db.collegeClass.getAll(),
                CollegeClass::getName,
               "Escolha a Cadeiras"
        );

        Student student = new Student(
            employee.getName(),
            employee.getLastName(),
            employee.getAge(),
            employee.getDateOfBirth(),
            employee.getCpf(),
            null,
            academicSystem.db.generalInformation.data.getCourse()
        );

        student.setSubjects(Subject.mapAllToSubjectStudent(subjects, student));

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
                employee.getCpf(),
                salary
        );

        academicSystem.db.teachers.save(teacher);

    }

    private void optionShowTeatchers() {}

    private void optionCreateCollegeClass() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        AllData allData = academicSystem.db.findAll();

        CollegeClass collegeClass = DataInput.getOptionFromListByUser(
                allData.collegeClasses(),
                CollegeClass::getName,
                "Escolha a Cadeira"
        );

        Teacher teacher = DataInput.getOptionFromListByUser(
             allData.teachers(),
             Teacher::getName,
             "Escolha o professor"
        );

        //ToDo Terminar a implementação
    }

    public void optionDeleteColegeClass() {

        AcademicSystem academicSystem = Global.getAcademicSystem();

        CollegeClass collegeClass = DataInput.getOptionFromListByUser(
                academicSystem.db.collegeClass.getAll(),
                CollegeClass::getName,
                "Escolha a turma"
        );

        academicSystem.db.collegeClass.delete(collegeClass.getClassId());
    }


    @Override
    public Map<Integer, ISubMenuOption> getOptions() {
        Map<Integer, ISubMenuOption> options = new LinkedHashMap<>();

        options.put(1, new OptionMenu("Ver Estudantes", this::optionShowStudents));
        options.put(2, new OptionMenu("Adicionar estudante", this::optionAddStudent));
        options.put(3, new OptionMenu("Remover estudante", this::optionDeleteStudent));

        options.put(4, new OptionMenu("Ver Professores", this::optionShowTeatchers));
        options.put(5, new OptionMenu("Adicionar Professor", this::optionAddTeatcher));
        options.put(6, new OptionMenu("Remover Professor", this::optionDeleteTeatcher));

        options.put(7, new OptionMenu("Adicionar turma", this::optionCreateCollegeClass));
        options.put(8, new OptionMenu("Deletar turma", this::optionDeleteColegeClass));

        return options;
    }

    @Override
    public void run() {
       new MenuExecutor(this).run();
    }

}
