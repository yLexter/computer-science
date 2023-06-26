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

    public static String getCurrentPeriod() {
        LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());
        String currentPeriod = currentDate.getMonthValue() > 6 ? "2" : "1";

        return String.format("%d.%s", currentDate.getYear(), currentPeriod);
    }

    public static SubjectStudent studentToSubjectStudent(Subject subject, Student student, String idCollegeClass) {
        return new SubjectStudent(
                subject.getCode(),
                subject.getName(),
                subject.getHours(),
                student.getId(),
                idCollegeClass
        );
    }

    public static List<SubjectStudent> studentToSubjectStudent(List<Student> list, Subject subject, String idCollegeClass) {
        return list
                .stream()
                .map(student -> studentToSubjectStudent(subject, student, idCollegeClass))
                .collect(Collectors.toList());
    }


    public static CollegeClass subjectToCollegeClass(Subject subject, String teacherId, ClassRoom classRoom) {
        return new CollegeClass(
          subject.getCode(),
          subject.getName(),
          subject.getHours(),
          teacherId,
          null,
          null
        );
    }

    public static List<CollegeClass> subjectToCollegeClass(List<Subject> subjects, String teacherId, ClassRoom classRoom) {
        return subjects
                .stream()
                .map(subject -> subjectToCollegeClass(subject, teacherId, classRoom))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %d ", code, name, hours);
    }

}