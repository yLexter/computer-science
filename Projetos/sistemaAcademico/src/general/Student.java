package general;

import utils.Global;
import utils.Role;

import java.time.LocalDate;
import java.util.*;
public class Student extends Employee {
    private List<String> collegeClasses;
    private String course;
    private List<SubjectStudent> historic;
    private EntranceExam entranceExam;

    public Student(String name, String lastName, LocalDate dateOfBirth, String cpf, List<String> collegeClasses, String course, List<SubjectStudent> historic, EntranceExam entranceExam) {
        super(name, lastName, dateOfBirth, Role.STUDENT, cpf);
        this.collegeClasses = collegeClasses;
        this.course = course;
        this.historic = historic;
        this.entranceExam = entranceExam;
    }

    public Student(String name, String lastName, LocalDate dateOfBirth, String cpf, List<String> collegeClasses, String course, EntranceExam entranceExam) {
        super(name, lastName, dateOfBirth, Role.STUDENT, cpf);
        this.collegeClasses = collegeClasses;
        this.course = course;
        this.entranceExam = entranceExam;
        this.historic = new ArrayList<>();
    }

    public List<SubjectStudent> getSubjectStudent() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        return academicSystem.db.collegeClass.getAllSubjectStudentOfStudent(getId());
    }

    public List<String> getCollegeClasses() {
        return collegeClasses;
    }

    public List<String> getSubjectsId() {
        return collegeClasses;
    }

    public void setCollegeClasses(List<String> collegeClasses) {
        this.collegeClasses = collegeClasses;
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

    public EntranceExam getEntranceExam() {
        return entranceExam;
    }

    @Override
    public String toString() {
        return String.format("%s | %s", super.toString(), course);
    }

}
