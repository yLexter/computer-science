package general;

import utils.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubjectStudent extends Subject {
    private Float note1 = null;
    private Float note2 = null;
    private Float finalExameScore = null;
    private String status = "Pending";
    private Student student;
    private String classId;
    private Integer absences;
    public SubjectStudent(String code, String name, int hours, Student student, String classId) {
        super(code, name, hours);
        this.student = student;
        this.classId = classId;
    }

    public SubjectStudent(String code, String name, int hours, Float note1, Float note2, Integer absences, Float finalExameScore, String status, Student student, String classId) {
        super(code, name, hours);
        this.note1 = note1;
        this.note2 = note2;
        this.finalExameScore = finalExameScore;
        this.status = status;
        this.student = student;
        this.absences = absences;
        this.classId = classId;
    }

    public Float getAverage() {
        if (note1 == null || note2 == null)
            return null;
        return (note1 + note2) / 2;
    }

    public Float getNote1() {
        return note1;
    }

    public void setNote1(Float note1) {
        this.note1 = note1;
    }

    public Float getNote2() {
        return note2;
    }

    public void setNote2(Float note2) {
        this.note2 = note2;
    }

    public Integer getAbsences() {
        return absences;
    }

    public Float getFinalExameScore() {
        return finalExameScore;
    }

    public void setFinalExameScore(Float finalExameScore) {
        this.finalExameScore = finalExameScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    private void setAbsences(Integer absences) {
        this.absences = absences;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    public void increaseAbsences() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        setAbsences(absences + academicSystem.db.generalInformation.data.getTotalAbsemcesPerClass());
    }


}
