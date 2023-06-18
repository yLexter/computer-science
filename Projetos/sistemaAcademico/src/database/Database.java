package database;

public class Database {
    public final DatabaseStudent students;
    public final DatabaseTeacher teachers;
    public final DatabaseAdmin admin;
    public final DatabaseSubjects subjects;
    public final DatabaseGeneralInformation generalInformation;
    public final DatabaseCollegeClass collegeClass;
    public Database(DatabaseStudent students, DatabaseTeacher teachers, DatabaseAdmin admin, DatabaseSubjects subjects, DatabaseGeneralInformation generalInformation, DatabaseCollegeClass collegeClass) {
        this.students = students;
        this.teachers = teachers;
        this.admin = admin;
        this.subjects = subjects;
        this.generalInformation = generalInformation;
        this.collegeClass = collegeClass;
    }

}
