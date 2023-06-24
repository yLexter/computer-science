package general;

import utils.Global;
import utils.StudentSubjectStatus;
import utils.Utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubjectStudent extends Subject {
    private Float note1 = null;
    private Float note2 = null;
    private Float finalExameScore = null;
    private StudentSubjectStatus status;
    private String studentId;
    private String classId;
    private Integer absences = 0;
    private String period;

    public SubjectStudent(String code, String name, int hours, String studentId, String classId) {
        super(code, name, hours);
        this.studentId = studentId;
        this.classId = classId;
        this.status = StudentSubjectStatus.PENDING;
        this.period = getCurrentPeriod();
    }

    public SubjectStudent(String code, String name, int hours, Float note1, Float note2, Integer absences, Float finalExameScore, StudentSubjectStatus status, String studentId, String classId) {
        super(code, name, hours);
        this.note1 = note1;
        this.note2 = note2;
        this.finalExameScore = finalExameScore;
        this.status = status;
        this.studentId = studentId;
        this.absences = absences;
        this.classId = classId;
        this.period = getPeriod();
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

    public StudentSubjectStatus getStatus() {
        return status;
    }

    public String getStudentId() {
        return studentId;
    }

    private void setAbsences(Integer absences) {
        this.absences = absences;
    }

    public void increaseAbsences() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        setAbsences(absences + academicSystem.db.generalInformation.getData().getTotalAbsemcesPerClass());
    }

    public Student getStudent() {
       AcademicSystem academicSystem = Global.getAcademicSystem();

       return academicSystem.db.students.findById(studentId);
    }
    public CollegeClass getCollegeClass(String id) {
        AcademicSystem academicSystem = Global.getAcademicSystem();

        return academicSystem.db.collegeClass.findById(classId);
    }

    public void setStatus(StudentSubjectStatus status) {
        this.status = status;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public List<String> getListNotes() {
        return List.of(
           Utils.numberToString(note1),
           Utils.numberToString(note2)
        );
    }



    public String toString() {
        return String.format("%s", super.toString());
    }

}
