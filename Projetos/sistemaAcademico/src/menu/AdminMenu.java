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
import java.util.stream.Collectors;

/**
 * A classe AdminMenu representa um menu específico para um usuário administrador em um sistema acadêmico.
 * Ela fornece várias opções de menu para gerenciar estudantes, professores, turmas, salas e horários das aulas.
 */
public class AdminMenu implements IMenuEmployee<Admin> {

    private final String adminId;

   /**
     * Constrói um objeto AdminMenu com o ID do administrador fornecido.
     *
     * @param adminId o ID do usuário administrador
     */
    public AdminMenu(String adminId) {
        this.adminId = adminId;
    }

   /**
     * Busca o usuário administrador no banco de dados.
     *
     * @return o usuário administrador
     */
    @Override
    public Admin getUser() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        return academicSystem.db.admin.findById(adminId);
    }


    /**
     * Mostra a lista de estudantes.
     */  
    private void optionShowStudents() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<Student> students = academicSystem.db.students.getAll();

        if (students.size() == 0) {
            Decoration.showMessageAndClearScreen("Lista de estudantes vazia");
            return;
        }

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

    /**
     * Adiciona um novo estudante.
     */

    private void optionAddStudent() {

        Employee employee = Employee.createEmployeeByUser(Role.STUDENT);

        AcademicSystem academicSystem = Global.getAcademicSystem();
        AllData allData = academicSystem.db.findAll();
        List<CollegeClass> allCollegeClass = allData.collegeClasses();

        if (allCollegeClass.size() == 0) {
            Decoration.showMessageAndClearScreen("A lista de turmas está vazia");
            return;
        }

        List<CollegeClass> collegeClasses = DataInput.getElementsFromListByUser(
                allCollegeClass,
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
            allData.academicSystemSettings().getCourse(),
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

        Decoration.showMessageAndClearScreen("Estudante adicionado com sucesso");
    }

    /**
     * Remove um estudante.
     */
  
    private void optionDeleteStudent() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<Student> students = academicSystem.db.students.getAll();

        if (students.size() == 0) {
            Decoration.showMessageAndClearScreen("Lista de turmas vazia");
            return;
        }

        Student studentRemoved = DataInput.getElementFromListByUser(
                students,
                Student::toString,
                "Escolha o estudante"
        );

        academicSystem.db.collegeClass.removeStudentFromCollegeClasses(studentRemoved.getId());
        academicSystem.db.students.delete(studentRemoved.getId());

        Decoration.showMessageAndClearScreen("Estudante removido com sucesso");

    }


  /**
     * Remove um professor.
     */
    private void optionDeleteTeatcher() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<Teacher> teachers = academicSystem.db.teachers.getAll();

        if (teachers.size() == 0) {
            Decoration.showMessageAndClearScreen("Lista de de professores vazia");
            return;
        }

        Teacher teacherRemoved = DataInput.getElementFromListByUser(
                teachers,
                Teacher::toString,
                "Escolha o Professor`"
        );

        academicSystem.db.collegeClass.removeTeacherFromCollegeClasses(teacherRemoved.getId());
        academicSystem.db.teachers.delete(teacherRemoved.getId());

        Decoration.showMessageAndClearScreen("Professor removido com sucesso");
    }


  /**
     * Adiciona um professor.
     */
    private void optionAddTeatcher() {

        AcademicSystem academicSystem = Global.getAcademicSystem();
        Employee employee = Employee.createEmployeeByUser(Role.TEACHER);

        List<CollegeClass> collegeClassesWithoutTeacher = academicSystem.db.collegeClass.findMany(
                collegeClass -> collegeClass.getTeacher() == null
        );

        Teacher teacher = new Teacher(
                employee.getName(),
                employee.getLastName(),
                employee.getDateOfBirth(),
                employee.getCpf()
        );

        if (collegeClassesWithoutTeacher.size() != 0) {

            List<CollegeClass> collegeClasses = DataInput.getElementsFromListByUser(
                    collegeClassesWithoutTeacher,
                    CollegeClass::toString,
                    "Escolha uma turma para o professor"
            );

            academicSystem.db.collegeClass.addTeacherFromCollegeClasses(collegeClasses, teacher.getId());
        }


        academicSystem.db.teachers.save(teacher);

        Decoration.showMessageAndClearScreen("Professor adicionado com sucesso");
    }

   /**
     * Mostra todos professor.
     */
    private void optionShowTeatchers() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<Teacher> teachers = academicSystem.db.teachers.getAll();

        if (teachers.size() == 0) {
            Decoration.showMessageAndClearScreen("Lista de Professores vazio");
            return;
        }

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

     /**
     * Cria uma nova turma
     */
    private void optionCreateCollegeClass() {

        AcademicSystem academicSystem = Global.getAcademicSystem();
        AllData allData = academicSystem.db.findAll();
        CollegeClass collegeClass;
        String idCollegeClass = UUID.randomUUID().toString();
        List<Teacher> teachers = academicSystem.db.teachers.getAll();

        Subject subject = DataInput.getElementFromListByUser(
                allData.subjects(),
                Subject::toString,
                "Escolha a Cadeira"
        );

        if (teachers.size() == 0) {
            Decoration.showMessageAndClearScreen("Lista de Professores vazio");
            return;
        }

        Teacher teacher = DataInput.getElementFromListByUser(
             teachers,
             Teacher::toString,
             "Escolha o professor"
        );

        List<ClassSchedule> classSchedules = DataInput.getObjectInstancesByUser(
                (currentList) -> {

                Room room = DataInput.getElementFromListByUser(
                        allData.rooms(),
                        Room::toString,
                        "Escolha uma sala"
                );

                LocalTime time = DataInput.getDataByUser(
                        "Digite o horario no formato XX:XX",
                        DataEntryValidator::validTime
                );

                DayOfWeek dayOfWeek = DataInput.getElementFromListByUser(
                        Arrays.stream(DayOfWeek.values()).toList(),
                        Utils::formatDayOfWeak,
                        "Escolha o dia da semana"
                );

                boolean timeRepetead = academicSystem.db.classSchedule.checkTimeInUse(
                        dayOfWeek,
                        time,
                        currentList,
                        room.getId()
                );

                if (timeRepetead)
                    throw new IllegalArgumentException("Você já forneceu este horario, ou colidi com os fornecidos");

                boolean timeInUse = academicSystem.db.classSchedule.checkTimeInUse(
                        dayOfWeek,
                        time,
                        allData.classSchedules(),
                        room.getId()
                );

                if (timeInUse)
                    throw new IllegalArgumentException("hórario em uso por outra tuma");

                return new ClassSchedule(time, dayOfWeek, idCollegeClass, room.getId());
           }, 2
        );

        collegeClass = new CollegeClass(
                subject.getCode(),
                subject.getName(),
                subject.getHours(),
                teacher.getId(),
                idCollegeClass
        );

        collegeClass.setClassSchedules(
            classSchedules
                    .stream()
                    .map(ClassSchedule::getId)
                    .collect(Collectors.toList())
        );

        academicSystem.db.collegeClass.save(collegeClass);
        academicSystem.db.classSchedule.saveMany(classSchedules);

        Decoration.showMessageAndClearScreen("Turma adicionada com sucesso");
    }

  /**
     * Mostra todas as turmas turma
     */
    public void optionShowCollegeClass() {

        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<CollegeClass> collegeClasses = academicSystem.db.collegeClass.getAll();

        if (collegeClasses.size() == 0) {
            Decoration.showMessageAndClearScreen("Lista de turmas vazia");
            return;
        }

        List<String> headers = List.of(
                "Codigo",
                "Nome",
                "Carga Horaria",
                "Professor",
                "Total de Alunos",
                "ID"
        );

        Function<CollegeClass, List<?>> getRow = (collegeClass -> {
            Teacher teacher = collegeClass.getTeacher();
            String nameTeacher = teacher == null ? "--" : teacher.getFullName();

            return List.of(
               collegeClass.getCode(),
               collegeClass.getName(),
               collegeClass.getHours(),
               nameTeacher,
               collegeClass.getStudents().size(),
               collegeClass.getCollegeClassId()
            );

        });

        Utils.printTable(collegeClasses, getRow, headers);
    }


    /**
     * deleta uma  turma
     */
    private void optionDeleteColegeClass() {

        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<CollegeClass> collegeClasses = academicSystem.db.collegeClass.getAll();

        if (collegeClasses.size() == 0) {
            Decoration.showMessageAndClearScreen("Lista de turmas vazia");
            return;
        }

        CollegeClass collegeClass = DataInput.getElementFromListByUser(
                collegeClasses,
                CollegeClass::toString,
                "Escolha a turma"
        );

        academicSystem.db.students.removeCollegeClassFromStudents(collegeClass.getCollegeClassId());
        academicSystem.db.collegeClass.delete(collegeClass.getCollegeClassId());

        Decoration.showMessageAndClearScreen("Turma deletada com sucesso");
    }

    /**
     * Cria uma nova sala
     */
    private void optionCreateRoom() {

        AcademicSystem academicSystem = Global.getAcademicSystem();

        String name = DataInput.getDataByUser(
            "Digite o nome da sala",
                (roomId) -> {

                  boolean nameRepetead = academicSystem.db.rooms.has(
                          room -> room.getName().equalsIgnoreCase(roomId)
                  );

                  if(nameRepetead)
                      throw new IllegalArgumentException("Este nome já está sendo usado");

                  return roomId;
                }
        );

        int capacity = DataInput.getDataByUser(
                "Digite a capacidade da sala",
                Integer::parseInt,
                DataEntryValidator::validNumberIsPositive
        );

        academicSystem.db.rooms.save(new Room(name, capacity));

        Decoration.showMessageAndClearScreen("Sala criada com sucesso");
    }

   /**
     * Mostra todas as salas
     */
  
    private void optionShowRooms() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<Room> rooms = academicSystem.db.rooms.getAll();

        if (rooms.size() == 0) {
            Decoration.showMessageAndClearScreen("Lista de salas vazia");
            return;
        }

        List<String> headers = List.of(
                "Sala",
                "Capacidade"
        );

        Function<Room, List<?>> callBack = (room -> {
            return List.of(
                    room.getName(),
                    room.getCapacity()
            );
        });

        Utils.printTable(rooms, callBack, headers);
    }

     /**
     * Mostra todos os horarios de aula das turmas
     */
    public void optionShowClassSchedule() {

        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<ClassSchedule> classSchedules = academicSystem.db.classSchedule.getAll();

        if (classSchedules.size() == 0) {
            Decoration.showMessageAndClearScreen("Lista de horários de aula vazia");
            return;
        }

        List<String> headers = List.of(
                "Sala",
                "Capacidade",
                "Professor",
                "Dia",
                "Hórario"
        );

        Function<ClassSchedule, List<?>> callBack = (classSchedule -> {
            CollegeClass collegeClass = academicSystem.db.collegeClass.findById(classSchedule.getCollegeClassId());
            Room room = classSchedule.getRoom();
            Teacher teacher = collegeClass.getTeacher();

            return List.of(
                room.getName(),
                room.getCapacity(),
                teacher.getFullName(),
                classSchedule.getDayOfWeekFormatted(),
                classSchedule.getTimeFormatted()
            );
        });


        Utils.printTable(classSchedules, callBack, headers);
    }

    /**
     * Retorna as opções do menu.
     *
     * @return as opções do menu
     */
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

            new OptionMenu("Ver horarios das turmas", this::optionShowClassSchedule)
        );

    }

  /**
     * Obtem o cabeçalho do menu.
     */
    @Override
    public String getHeader() {
        Admin admin = getUser();
        return Decoration.generateWelcomeHeader(admin.getFullName());
    }

   /**
     * Executa o menu.
     */
    @Override
    public void run() {
       new MenuExecutor(this).run();
    }

}
