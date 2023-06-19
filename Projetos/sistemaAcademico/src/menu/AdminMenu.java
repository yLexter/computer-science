package menu;

import database.Database.AllData;
import general.*;
import interfaces.ISubMenu;
import interfaces.ISubMenuOption;
import utils.DataEntryValidator;
import utils.DataInput;
import utils.Global;
import utils.Role;

import java.util.ArrayList;
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
    public List<ISubMenuOption> getOptions() {

        return List.of(
            new OptionMenu("Ver Estudantes", this::optionShowStudents),
            new OptionMenu("Adicionar estudante", this::optionAddStudent),
            new OptionMenu("Remover estudante", this::optionDeleteStudent),
            new OptionMenu("Ver Professores", this::optionShowTeatchers),
            new OptionMenu("Adicionar Professor", this::optionAddTeatcher),
            new OptionMenu("Remover Professor", this::optionDeleteTeatcher),
            new OptionMenu("Adicionar turma", this::optionCreateCollegeClass),
            new OptionMenu("Deletar turma", this::optionDeleteColegeClass)
        );

    }

    @Override
    public void run() {
       new MenuExecutor(this).run();
    }

}
