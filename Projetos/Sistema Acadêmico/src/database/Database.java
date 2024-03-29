package database;

import general.*;
import interfaces.database.*;

import java.util.List;

/**
 * Esta classe representa um banco de dados geral que contém todos os dados do sistema acadêmico.
 * Ela possui registros para estudantes, professores, administradores, disciplinas, configurações do sistema acadêmico,
 * turmas, salas e horários das aulas.
 * 
 * Os dados são armazenados em instâncias das interfaces `IDatabaseStudent`, `IDatabaseTeacher`, `IDatabaseAdmin`,
 * `IDatabaseSubject`, `IDatabaseAcademicSystemSettings`, `IDatabaseCollegeClass`, `IDatabaseRoom` e `IDatabaseClassSchedule`.
 * 
 * A classe `AllData` é um registro que contém listas dos dados de cada tipo, proporcionando um acesso conveniente a eles.
 * 
 * Além disso, a classe fornece métodos para encontrar todos os registros e para obter um funcionário pelo CPF.
 */
public class Database {

    /**
     * Classe interna que representa todos os dados do banco de dados.
     * Contém listas dos registros de estudantes, professores, administradores, disciplinas,
     * configurações do sistema acadêmico, turmas, salas e horários das aulas.
     */
    public record AllData(List<Student> students, List<Teacher> teachers, List<Admin> admins, List<Subject> subjects, AcademicSystemSettings academicSystemSettings, List<CollegeClass> collegeClasses, List<Room> rooms, List<ClassSchedule> classSchedules) {
        @Override
        public List<Student> students() {
            return students;
        }

        @Override
        public List<Teacher> teachers() {
            return teachers;
        }

        @Override
        public List<Admin> admins() {
            return admins;
        }

        @Override
        public List<Subject> subjects() {
            return subjects;
        }

        public AcademicSystemSettings academicSystemSettings() {
            return academicSystemSettings;
        }
        @Override
        public List<CollegeClass> collegeClasses() {
            return collegeClasses;
        }

        @Override
        public List<Room> rooms() {
            return rooms;
        }

        @Override
        public List<ClassSchedule> classSchedules() {
            return classSchedules;
        }
    }
  
    public final IDatabaseStudent students;
    public final IDatabaseTeacher teachers;
    public final IDatabaseAdmin admin;
    public final IDatabaseSubject subjects;
    public final IDatabaseAcademicSystemSettings AcademicSystemSettings;
    public final IDatabaseCollegeClass collegeClass;
    public final IDatabaseRoom rooms;
    public final IDatabaseClassSchedule classSchedule;

  /**
     * Cria uma nova instância do banco de dados.
     *
     * @param students             Banco de dados de estudantes.
     * @param teachers             Banco de dados de professores.
     * @param admin                Banco de dados de administradores.
     * @param subjects             Banco de dados de disciplinas.
     * @param academicSystemSettings Banco de dados de configurações do sistema acadêmico.
     * @param collegeClass         Banco de dados de turmas.
     * @param rooms                Banco de dados de salas.
     * @param classSchedule        Banco de dados de horários das aulas.
     */
    public Database(IDatabaseStudent students, IDatabaseTeacher teachers, IDatabaseAdmin admin, IDatabaseSubject subjects, IDatabaseAcademicSystemSettings AcademicSystemSettings, IDatabaseCollegeClass collegeClass, IDatabaseRoom rooms, IDatabaseClassSchedule classSchedule) {
        this.students = students;
        this.teachers = teachers;
        this.admin = admin;
        this.subjects = subjects;
        this.AcademicSystemSettings = AcademicSystemSettings;
        this.collegeClass = collegeClass;
        this.rooms = rooms;
        this.classSchedule = classSchedule;
    }

     /**
     * Retorna todos os dados do banco de dados.
     *
     * @return Objeto `AllData` contendo todos os dados.
     */
    public AllData findAll() {
        return new AllData(
           students.getAll(),
           teachers.getAll(),
           admin.getAll(),
           subjects.getAll(),
           AcademicSystemSettings.getData(),
           collegeClass.getAll(),
           rooms.getAll(),
           classSchedule.getAll()
        );
    }

   /**
     * Obtém um funcionário pelo CPF.
     *
     * @param cpf CPF do funcionário a ser buscado.
     * @return Funcionário correspondente ao CPF ou null se não encontrado.
     */
    public Employee getEmployeeByCpf(String cpf) {
        Employee isAdmin = admin.findByCpf(cpf);

        if (isAdmin != null)
            return isAdmin;

        Employee isStudent = students.findByCpf(cpf);

        if (isStudent != null)
            return isStudent;

        return teachers.findByCpf(cpf);
    }

}
