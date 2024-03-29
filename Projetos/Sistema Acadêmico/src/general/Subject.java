package general;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Subject {
    private String code;
    private String name;
    private int hours;

   /**
     * Construtor da classe Subject.
     *
     * @param code   O código da disciplina.
     * @param name   O nome da disciplina.
     * @param hours  A carga horária da disciplina.
     */
    public Subject(String code, String name, int hours) {
        this.code = code;
        this.name = name;
        this.hours = hours;
    }

    /**
     * Obtém o código da disciplina.
     *
     * @return O código da disciplina.
     */
    public String getCode() {
        return code;
    }

   /**
     * Define o código da disciplina.
     *
     * @param code O código da disciplina.
     */
    public void setCode(String code) {
        this.code = code;
    }

   /**
     * Obtém o nome da disciplina.
     *
     * @return O nome da disciplina.
     */
    public String getName() {
        return name;
    }

   /**
     * Define o nome da disciplina.
     *
     * @param name O nome da disciplina.
     */
    public void setName(String name) {
        this.name = name;
    }

   /**
     * Obtém a carga horária da disciplina.
     *
     * @return A carga horária da disciplina.
     */
    public int getHours() {
        return hours;
    }

   /**
     * Define a carga horária da disciplina.
     *
     * @param hours A carga horária da disciplina.
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

  /**
     * Converte um objeto Student para um objeto SubjectStudent.
     *
     * @param subject         A disciplina relacionada ao objeto SubjectStudent.
     * @param student         O estudante relacionado ao objeto SubjectStudent.
     * @param idCollegeClass  O ID da turma relacionada ao objeto SubjectStudent.
     * @return Um objeto SubjectStudent criado a partir dos parâmetros fornecidos.
     */
    public static SubjectStudent studentToSubjectStudent(Subject subject, Student student, String idCollegeClass) {
        return new SubjectStudent(
                subject.getCode(),
                subject.getName(),
                subject.getHours(),
                student.getId(),
                idCollegeClass
        );
    }

  /**
     * Converte uma lista de objetos Student para uma lista de objetos SubjectStudent.
     *
     * @param list            A lista de estudantes relacionada aos objetos SubjectStudent.
     * @param subject         A disciplina relacionada aos objetos SubjectStudent.
     * @param idCollegeClass  O ID da turma relacionada aos objetos SubjectStudent.
     * @return Uma lista de objetos SubjectStudent criados a partir dos parâmetros fornecidos.
     */
    public static List<SubjectStudent> studentToSubjectStudent(List<Student> list, Subject subject, String idCollegeClass) {
        return list
                .stream()
                .map(student -> studentToSubjectStudent(subject, student, idCollegeClass))
                .collect(Collectors.toList());
    }

  /**
     * Converte um objeto Subject para um objeto CollegeClass.
     *
     * @param subject    A disciplina relacionada ao objeto CollegeClass.
     * @param teacherId  O ID do professor relacionado ao objeto CollegeClass.
     * @param roomId     O ID da sala de aula relacionada ao objeto CollegeClass.
     * @return Um objeto CollegeClass criado a partir dos parâmetros fornecidos.
     */
    public static CollegeClass subjectToCollegeClass(Subject subject, String teacherId, String roomId) {
        return new CollegeClass(
          subject.getCode(),
          subject.getName(),
          subject.getHours(),
          teacherId
        );
    }

    /**
     * Converte uma lista de objetos Subject para uma lista de objetos CollegeClass.
     *
     * @param subjects    A lista de disciplinas relacionadas aos objetos CollegeClass.
     * @param teacherId   O ID do professor relacionado aos objetos CollegeClass.
     * @param roomId      O ID da sala de aula relacionada aos objetos CollegeClass.
     * @return Uma lista de objetos CollegeClass criados a partir dos parâmetros fornecidos.
     */
    public static List<CollegeClass> subjectToCollegeClass(List<Subject> subjects, String teacherId, String roomId) {
        return subjects
                .stream()
                .map(subject -> subjectToCollegeClass(subject, teacherId, roomId))
                .collect(Collectors.toList());
    }

 /**
     * Retorna uma representação em formato de texto da disciplina.
     *
     * @return Uma representação em formato de texto da disciplina.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s | %d ", code, name, hours);
    }

}