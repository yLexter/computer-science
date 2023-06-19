package menu;

import database.Database.AllData;
import general.*;
import interfaces.ISubMenu;
import interfaces.ISubMenuOption;
import utils.DataEntryValidator;
import utils.DataInput;
import utils.Global;
import utils.Role;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;


// ToDo melhorar a forma  de pegar os horario e pegar informações do vestibular
public class AdminMenu implements ISubMenu {

    private final Admin admin;

    public AdminMenu(Admin admin) {
        this.admin = admin;
    }

    private void optionShowStudents() {}

    private void optionAddStudent() {

        Employee employee = Employee.createEmployeeByUser(Role.STUDENT);

        AcademicSystem academicSystem = Global.getAcademicSystem();
        AllData allData = academicSystem.db.findAll();

        List<CollegeClass> subjects = DataInput.getElementsFromListByUser(
                allData.collegeClasses(),
                CollegeClass::getName,
               "Escolha a Cadeiras"
        );

        EntranceExam entranceExam = new EntranceExam(
                DataInput.getDataByUser("Humanas", DataEntryValidator::entranceExamCcore),
                DataInput.getDataByUser("natureza", DataEntryValidator::entranceExamCcore),
                DataInput.getDataByUser("linguagens", DataEntryValidator::entranceExamCcore),
                DataInput.getDataByUser("matematica", DataEntryValidator::entranceExamCcore),
                DataInput.getDataByUser("Redação", DataEntryValidator::entranceExamCcore)
        );

        Student student = new Student(
            employee.getName(),
            employee.getLastName(),
            employee.getAge(),
            employee.getDateOfBirth(),
            employee.getCpf(),
            null,
            allData.generalInformation().getCourse(),
            entranceExam
        );


        student.setSubjects(Subject.mapAllToSubjectStudent(subjects, student));

        academicSystem.db.students.save(student);
    }

    private void optionDeleteStudent() {
        AcademicSystem academicSystem = Global.getAcademicSystem();

        Student studentRemoved = DataInput.getElementFromListByUser(
                academicSystem.db.students.getAll(),
                Student::getName,
                "Escolha o estudante"
        );

        academicSystem.db.students.delete(studentRemoved.getId());

        System.out.println("Estudante Removido");
    }

    private void optionDeleteTeatcher() {
        AcademicSystem academicSystem = Global.getAcademicSystem();

        Teacher studentRemoved = DataInput.getElementFromListByUser(
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

        CollegeClass collegeClass = DataInput.getElementFromListByUser(
                allData.collegeClasses(),
                CollegeClass::getName,
                "Escolha a Cadeira"
        );

        Teacher teacher = DataInput.getElementFromListByUser(
             allData.teachers(),
             Teacher::getName,
             "Escolha o professor"
        );

        //ToDo Terminar a implementação
    }

    public void optionDeleteColegeClass() {

        AcademicSystem academicSystem = Global.getAcademicSystem();

        CollegeClass collegeClass = DataInput.getElementFromListByUser(
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
