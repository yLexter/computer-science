package general;

import utils.Global;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Subject {
    private String code;
    private String name;
    private int hours;

    public Subject(String code, String name, int hours) {
        this.code = code;
        this.name = name;
        this.hours = hours;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public static SubjectStudent createSubjectStudent(Subject subject, Student student) {
        return new SubjectStudent(
                subject.getCode(),
                subject.getName(),
                subject.getHours(),
                student
        );
    }

    public static CollegeClass createCollegeClass(Subject subject, Teacher teacher, LocalDateTime time) {
        return new CollegeClass(
            subject.getCode(),
            subject.getName(),
            subject.getHours(),
            teacher,
            time
        );
    }

    public static List<SubjectStudent> mapAllToSubjectStudent(Student student) {
        AcademicSystem academicSystem = Global.getAcademicSystem();

        return  academicSystem.db.subjects.getAll()
                .stream()
                .map(subject -> Subject.createSubjectStudent(subject, student))
                .collect(Collectors.toList());
    }



}