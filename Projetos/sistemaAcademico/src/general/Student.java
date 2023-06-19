package general;

import utils.Role;

import java.time.LocalDate;
import java.util.*;
public class Student extends Employee {
    private List<SubjectStudent> subjects;
    private String course;
    private List<SubjectStudent> historic;

    private EntranceExam entranceExam;

    public Student(String name, String lastName, int age, LocalDate dateOfBirth, String cpf, List<SubjectStudent> subjects, String course, List<SubjectStudent> historic, EntranceExam entranceExam) {
        super(name, lastName, age, dateOfBirth, Role.STUDENT, cpf);
        this.subjects = subjects;
        this.course = course;
        this.historic = historic;
        this.entranceExam = entranceExam;
    }

    public Student(String name, String lastName, int age, LocalDate dateOfBirth, String cpf, List<SubjectStudent> subjects, String course, EntranceExam entranceExam) {
        super(name, lastName, age, dateOfBirth, Role.STUDENT, cpf);
        this.subjects = subjects;
        this.course = course;
        this.historic = new ArrayList<>();
        this.entranceExam = entranceExam;
    }

    public List<SubjectStudent> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectStudent> subjects) {
        this.subjects = subjects;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public List<SubjectStudent> getHistoric() {
        return historic;
    }

    public void setHistoric(List<SubjectStudent> historic) {
        this.historic = historic;
    }




}
