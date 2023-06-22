package menu;

import database.Database.AllData;
import general.*;
import interfaces.ISubMenu;
import interfaces.ISubMenuOption;
import utils.DataEntryValidator;
import utils.DataInput;
import utils.Global;
import utils.Role;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


// ToDo melhorar a forma  de pegar os horario e pegar informações do vestibular
public class AdminMenu implements ISubMenu {

    private final Admin admin;

    public AdminMenu(Admin admin) {
        this.admin = admin;
    }

    private void optionShowStudents() {

        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<Student> students = academicSystem.db.students.getAll();

        System.out.println("---------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-3s | %-12s | %-11s | %-2s |%n",
                "First Name", "Last Name", "Age", "Date of Birth", "CPF", "Course");
        System.out.println("---------------------------------------------------------");

        for (Student student : students) {
            System.out.printf("| %-10s | %-10s | %-3d | %-12s | %-11s | %-2s |%n",
                    student.getName(), student.getLastName(), student.getAge(),
                    student.getDateOfBirth(), student.getCpf(), student.getCourse());
        }

        System.out.println("---------------------------------------------------------");

    }

    private void optionAddStudent() {

        Employee employee = Employee.createEmployeeByUser(Role.STUDENT);

        AcademicSystem academicSystem = Global.getAcademicSystem();
        AllData allData = academicSystem.db.findAll();

        List<CollegeClass> collegeClasses = DataInput.getElementsFromListByUser(
                allData.collegeClasses(),
                CollegeClass::toString,
               "Escolha a Cadeiras"
        );

        EntranceExam entranceExam = new EntranceExam(
                DataInput.getDataByUser("Humanas", Double::parseDouble, DataEntryValidator::entranceExamCcore),
                DataInput.getDataByUser("natureza", Double::parseDouble, DataEntryValidator::entranceExamCcore),
                DataInput.getDataByUser("linguagens", Double::parseDouble,DataEntryValidator::entranceExamCcore),
                DataInput.getDataByUser("matematica", Double::parseDouble, DataEntryValidator::entranceExamCcore),
                DataInput.getDataByUser("Redação", Double::parseDouble, DataEntryValidator::entranceExamCcore)
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

         student.setSubjects(
             collegeClasses
                     .stream()
                     .map(CollegeClass::getClassId)
                     .toList()
         );

        academicSystem.db.students.save(student);

        System.out.println("Estudante adicionado");
    }

    private void optionDeleteStudent() {
        AcademicSystem academicSystem = Global.getAcademicSystem();

        Student studentRemoved = DataInput.getElementFromListByUser(
                academicSystem.db.students.getAll(),
                Student::toString,
                "Escolha o estudante"
        );

        academicSystem.db.students.delete(studentRemoved.getId());

        System.out.println("Estudante Removido");
    }

    private void optionDeleteTeatcher() {
        AcademicSystem academicSystem = Global.getAcademicSystem();

        Teacher studentRemoved = DataInput.getElementFromListByUser(
                academicSystem.db.teachers.getAll(),
                Teacher::toString,
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
                employee.getCpf()
        );

        academicSystem.db.teachers.save(teacher);

    }

    private void optionShowTeatchers() {


    }

    private void optionCreateCollegeClass() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        AllData allData = academicSystem.db.findAll();
        CollegeClass collegeClass;

        Subject subject = DataInput.getElementFromListByUser(
                allData.subjects(),
                Subject::toString,
                "Escolha a Cadeira"
        );

        Teacher teacher = DataInput.getElementFromListByUser(
             allData.teachers(),
             Teacher::toString,
             "Escolha o professor"
        );


        Room room = DataInput.getElementFromListByUser(
            allData.rooms(),
            (r) -> "Sala: %s | Capacidade: %d".formatted(r.getRoomId(), r.getCapacity()),
            "Escolha uma sala"
        );


        LocalTime time = DataInput.getDataByUser(
                "Digite o horario no formato XX:XX",
                (t) -> {
                   LocalTime validTime = DataEntryValidator.validTime(t);

                   if (!academicSystem.db.classRooms.checkVacantClassCchedule(validTime, room.getRoomId()))
                       throw new IllegalArgumentException("Horario em uso");

                   return validTime;
                }
        );


        DayOfWeek dayOfWeek = DataInput.getElementFromListByUser(
           Arrays.stream(DayOfWeek.values()).toList(),
           day -> day.getDisplayName(TextStyle.FULL, Locale.getDefault()),
           "Escolha o dia da semana"
        );

        ClassRoom classRoom = new ClassRoom(
            room.getRoomId(),
            room.getCapacity(),
            time,
            dayOfWeek
        );

        boolean createWithStudents = DataInput.getConfirmationByUser("Deseja adicionar estudantes?");

        if (createWithStudents) {

            List<Student> students = DataInput.getElementsFromListByUser(
                  allData.students(),
                  Student::toString,
                  "Escolha os estudantes",
                  room.getCapacity()
             );

             collegeClass = new CollegeClass(
                  subject.getCode(),
                  subject.getName(),
                  subject.getHours(),
                  teacher.getId(),
                  null,
                  classRoom.getId()
             );

             collegeClass.setStudents(
                Subject.studentToSubjectStudent(students, collegeClass, classRoom)
             );

         } else {

            collegeClass = new CollegeClass(
                    subject.getCode(),
                    subject.getName(),
                    subject.getHours(),
                    teacher.getId(),
                    null,
                    classRoom.getId()
            );

         }


        academicSystem.db.collegeClass.save(collegeClass);
        academicSystem.db.classRooms.save(classRoom);

        System.out.println("Turma adicionada");
    }

    private void optionDeleteColegeClass() {

        AcademicSystem academicSystem = Global.getAcademicSystem();

        CollegeClass collegeClass = DataInput.getElementFromListByUser(
                academicSystem.db.collegeClass.getAll(),
                CollegeClass::toString,
                "Escolha a turma"
        );

        academicSystem.db.collegeClass.delete(collegeClass.getClassId());
    }

    private void optionCreateRoom() {

        // ToDo Não permitir nomes iguais e melhorar implementação
        AcademicSystem academicSystem = Global.getAcademicSystem();

        String id = DataInput.getDataByUser(
            "Digite o codigo da sala",
                (x) -> {}
        );

        int capacity = DataInput.getDataByUser(
                "Digite o codigo da sala",
                Integer::parseInt,
                (x) -> {}
        );

        academicSystem.db.rooms.save(
             new Room(id, capacity)
        );

    }

    private void optionDeleteRoom() {

        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<Room> rooms = academicSystem.db.rooms.getAll();

        Room roomDeleted = DataInput.getElementFromListByUser(
            rooms,
            Room::toString,
            "Escolha uma sala para deletar"
        );

        academicSystem.db.rooms.delete(roomDeleted.getRoomId());
    }

    private void optionShowRooms() {

        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<Room> rooms = academicSystem.db.rooms.getAll();

        for (Room room : rooms)
            System.out.println(room.toString());

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
            new OptionMenu("Deletar turma", this::optionDeleteColegeClass),
            new OptionMenu("Ver salar", this::optionShowRooms),
            new OptionMenu("Criar Sala", this::optionCreateRoom),
            new OptionMenu("Deletar Sala", this::optionDeleteRoom)
        );

    }

    @Override
    public void run() {
       new MenuExecutor(this).run();
    }

}
