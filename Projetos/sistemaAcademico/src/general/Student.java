package general;

import utils.Global;
import utils.Role;

import java.time.LocalDate;
import java.util.*;
public class Student extends Employee {
    private List<String> subjects;
    private String course;
    private List<SubjectStudent> historic;
    private EntranceExam entranceExam;

    public Student(String name, String lastName, int age, LocalDate dateOfBirth, String cpf, List<String> subjects, String course, List<SubjectStudent> historic, EntranceExam entranceExam) {
        super(name, lastName, age, dateOfBirth, Role.STUDENT, cpf);
        this.subjects = subjects;
        this.course = course;
        this.historic = historic;
        this.entranceExam = entranceExam;
    }

    public Student(String name, String lastName, int age, LocalDate dateOfBirth, String cpf, List<String> subjects, String course, EntranceExam entranceExam) {
        super(name, lastName, age, dateOfBirth, Role.STUDENT, cpf);
        this.subjects = subjects;
        this.course = course;
        this.entranceExam = entranceExam;
        this.historic = new ArrayList<>();
    }

    public List<SubjectStudent> getSubjects() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        return academicSystem.db.collegeClass.getAllSubjectStudentOfStudent(this);
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public void setEntranceExam(EntranceExam entranceExam) {
        this.entranceExam = entranceExam;
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


    @Override
    public String toString() {
        return String.format("%s | %s", super.toString(), course);
    }

}
