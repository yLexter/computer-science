package general;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public static SubjectStudent createSubjectStudent(Subject subject) {
        return new SubjectStudent(
                subject.getCode(),
                subject.getName(),
                subject.getHours()
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


}