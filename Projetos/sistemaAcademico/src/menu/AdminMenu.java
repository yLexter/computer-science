package menu;

import database.Database.AllData;
import general.*;
import interfaces.menu.IMenuEmployee;
import interfaces.menu.ISubMenuOption;
import utils.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;


// ToDo melhorar a forma  de pegar os horario e pegar informações do vestibular
public class AdminMenu implements IMenuEmployee<Admin> {

    private final String adminId;

    public AdminMenu(String adminId) {
        this.adminId = adminId;
    }

    @Override
    public Admin getUser() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        return academicSystem.db.admin.findById(adminId);
    }

    private void optionShowStudents() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<Student> students = academicSystem.db.students.getAll();

        List<String> headers = List.of(
                "Nome",
                "SobreNome",
                "Idade",
                "Data Nascimento",
                "CPF",
                "Curso"
        );

        Function<Student, List<?>> callBack = (student -> {
            return List.of(
                    student.getName(),
                    student.getLastName(),
                    student.getAge(),
                    student.getFormatedDateOfBirth(),
                    student.getCpf(),
                    student.getCourse()
            );
        });

        Utils.printTable(students, callBack, headers);
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
            employee.getDateOfBirth(),
            employee.getCpf(),
            null,
            allData.generalInformation().getCourse(),
            entranceExam
        );

         student.setCollegeClasses(
             collegeClasses
                     .stream()
                     .map(CollegeClass::getCollegeClassId)
                     .toList()
         );

        academicSystem.db.collegeClass.addStudentToCollegesClasses(student, collegeClasses);
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

        academicSystem.db.collegeClass.removeStudentFromCollegeClasses(studentRemoved.getId());
        academicSystem.db.students.delete(studentRemoved.getId());

        System.out.println("Estudante Removido");
    }

    private void optionDeleteTeatcher() {
        AcademicSystem academicSystem = Global.getAcademicSystem();

        Teacher teacherRemoved = DataInput.getElementFromListByUser(
                academicSystem.db.teachers.getAll(),
                Teacher::toString,
                "Escolha o Professor`"
        );

        academicSystem.db.collegeClass.removeCollegeClassFromTeacher(teacherRemoved.getId());
        academicSystem.db.teachers.delete(teacherRemoved.getId());

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
                employee.getDateOfBirth(),
                employee.getCpf()
        );

        academicSystem.db.teachers.save(teacher);

    }

    private void optionShowTeatchers() {

        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<Teacher> teachers = academicSystem.db.teachers.getAll();

        List<String> headers = List.of(
                "Mátricula",
                "Nome",
                "SobreNome",
                "Idade",
                "Data Nascimento",
                "CPF"
        );

        Function<Teacher, List<?>> callBack = (teacher -> {
             return List.of(
                    teacher.getId(),
                    teacher.getName(),
                    teacher.getLastName(),
                    teacher.getAge(),
                    teacher.getFormatedDateOfBirth(),
                    teacher.getCpf()
            );
        });

        Utils.printTable(teachers, callBack, headers);
    }

    // ToDo pegar os horarios da aula do usuario
    private void optionCreateCollegeClass() {

        AcademicSystem academicSystem = Global.getAcademicSystem();
        AllData allData = academicSystem.db.findAll();
        CollegeClass collegeClass;
        String idCollegeClass = UUID.randomUUID().toString();

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
            Room::toString,
            "Escolha uma sala"
        );

        List<ClassSchedule> classSchedules = DataInput.getObjectInstancesByUser(
                () -> {

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
                        Utils::formatDayOfWeak,
                        "Escolha o dia da semana"
                );

                // ToDo pegar o id da college class e setar na class  Schedule

                return new ClassSchedule(time, dayOfWeek, idCollegeClass);
                }, 2
        );

        ClassRoom classRoom = new ClassRoom(
            room.getRoomId(),
            room.getCapacity(),
            classSchedules
                    .stream()
                    .map(ClassSchedule::getId)
                    .toList()
        );

        collegeClass = new CollegeClass(
                subject.getCode(),
                subject.getName(),
                subject.getHours(),
                teacher.getId(),
                null,
                classRoom.getId(),
                idCollegeClass
        );

        boolean createWithStudents = DataInput.getConfirmationByUser("Deseja adicionar estudantes?");

        if (createWithStudents) {

            List<Student> students = DataInput.getElementsFromListByUser(
                  allData.students(),
                  Student::toString,
                  "Escolha os estudantes",
                  room.getCapacity()
             );

             collegeClass.setStudents(
                Subject.studentToSubjectStudent(students, collegeClass, idCollegeClass)
             );
         }

        academicSystem.db.collegeClass.save(collegeClass);
        academicSystem.db.classRooms.save(classRoom);
        academicSystem.db.classSchedule.saveMany(classSchedules);

        System.out.println("Turma adicionada");
    }

    public void optionShowCollegeClass() {

        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<CollegeClass> collegeClasses = academicSystem.db.collegeClass.getAll();

        CollegeClass collegeClass = DataInput.getElementFromListByUser(
              collegeClasses,
              CollegeClass::toString,
              "Escolha uma turma"
        );

        System.out.println(collegeClass.toString());

        List<SubjectStudent> subjectStudents = collegeClass.getStudents();

        List<String> headers = List.of(
                "Matricula",
                "Nome",
                "Nota 1",
                "Nota 2",
                "Final",
                "Faltas",
                "Status",
                "Periodo"
        );

        Function<SubjectStudent, List<?>> getRow = (subjectStudent -> {
            Student student = subjectStudent.getStudent();
            List<String> notes = subjectStudent.getListNotes();

            return List.of(
                student.getId(),
                student.getFullName(),
                notes.get(0),
                notes.get(1),
                notes.get(2),
                subjectStudent.getAbsences(),
                subjectStudent.getStatus().get(),
                subjectStudent.getPeriod()
            );

        });

        Utils.printTable(subjectStudents, getRow, headers);
    }

    private void optionDeleteColegeClass() {

        AcademicSystem academicSystem = Global.getAcademicSystem();

        CollegeClass collegeClass = DataInput.getElementFromListByUser(
                academicSystem.db.collegeClass.getAll(),
                CollegeClass::toString,
                "Escolha a turma"
        );

        academicSystem.db.students.removeCollegeClassFromStudents(collegeClass.getCollegeClassId());
        academicSystem.db.collegeClass.delete(collegeClass.getCollegeClassId());
    }

    private void optionCreateRoom() {

        // ToDo Não permitir nomes iguais e melhorar implementação
        AcademicSystem academicSystem = Global.getAcademicSystem();

        String id = DataInput.getDataByUser(
            "Digite o codigo da sala",
                (x) -> {}
        );

        int capacity = DataInput.getDataByUser(
                "Digite a capacidade da sala",
                Integer::parseInt,
                DataEntryValidator::validNumberIsPositive
        );

        academicSystem.db.rooms.save(new Room(id, capacity));
    }

    private void optionShowRooms() {

        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<Room> rooms = academicSystem.db.rooms.getAll();

        List<String> headers = List.of(
                "Sala",
                "Capacidade"
        );

        Function<Room, List<?>> callBack = (room -> {
            return List.of(
                    room.getRoomId(),
                    room.getCapacity()
            );
        });

        Utils.printTable(rooms, callBack, headers);
    }

    public void optionUpdateRoom() {

        AcademicSystem academicSystem = Global.getAcademicSystem();

        Room changedRoom = DataInput.getElementFromListByUser(
             academicSystem.db.rooms.getAll(),
             Room::toString,
             "Escolha a sala que deseja altera"
        );

        List<String> options = List.of(
                "Sala",
                "Capacidade"
        );

        String option = DataInput.getElementFromListByUser(
             options,
             String::toString,
             "Escolha oque deseja atualizar"
        );

        if (option.equals(options.get(0))) {

            // Verificar id existente
            String newIdRoom = DataInput.getDataByUser(
                 "Digite o novo id",
                    (newId) -> {
                       Room alreadyExistId = academicSystem.db.rooms.findById(newId.toLowerCase());

                       if (alreadyExistId != null)
                          throw new IllegalArgumentException("Id Existente");
                    }
            );

            changedRoom.setRoomId(newIdRoom);

            academicSystem.db.rooms.update(changedRoom.getRoomId(), changedRoom);

            return;
        }

        if (option.equals(options.get(1))) {

            int newCapacity = DataInput.getDataByUser(
                    "Digite A capacidade",
                    Integer::parseInt,
                    DataEntryValidator::validNumberIsPositive
            );

            changedRoom.setCapacity(newCapacity);
            academicSystem.db.rooms.update(changedRoom.getRoomId(), changedRoom);
            return;
        }

        System.out.println("Sala atualizada com sucesso");


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

            new OptionMenu("Ver Turmas", this::optionShowCollegeClass),
            new OptionMenu("Adicionar turma", this::optionCreateCollegeClass),
            new OptionMenu("Deletar turma", this::optionDeleteColegeClass),

            new OptionMenu("Ver salas", this::optionShowRooms),
            new OptionMenu("Adicionar Sala", this::optionCreateRoom),
            new OptionMenu("Atualizar sala", this::optionUpdateRoom)
        );

    }

    @Override
    public void run() {
       new MenuExecutor(this).run();
    }

}
