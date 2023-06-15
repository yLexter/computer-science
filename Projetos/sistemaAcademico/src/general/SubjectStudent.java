package general;

import java.util.List;

public class SubjectStudent extends Subject {

    private Float note1 = null;
    private Float note2 = null;
    private Integer absences = 0;
    private Float finalExameScore = null;
    private String status = "Pending";
    private List<SubjectStudent> subjects;

    public SubjectStudent(String code, String name, int hours) {
        super(code, name, hours);
    }
    public SubjectStudent(String code, String name, int hours, Float note1, Float note2, Integer absences, Float finalExameScore, String status, List<SubjectStudent> subjects) {
        super(code, name, hours);
        this.note1 = note1;
        this.note2 = note2;
        this.absences = absences;
        this.finalExameScore = finalExameScore;
        this.status = status;
        this.subjects = subjects;
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

    public void setAbsences(Integer absences) {
        this.absences = absences;
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

    public List<SubjectStudent> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectStudent> subjects) {
        this.subjects = subjects;
    }

    public Teacher getTeacher() {
        // ToDo: Buscar no banco de dados o professor referente a esta máteria
        return null;
    }








}
