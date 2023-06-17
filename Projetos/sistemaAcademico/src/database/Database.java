package database;

public class Database {
    public final DatabaseStudent students;
    public final DatabaseTeatcher teachers;
    public final DatabaseAdmin admin;
    public final DatabaseSubjects subjects;
    public final DatabaseGeneralInformation generalInformation;

    public Database(DatabaseStudent students, DatabaseTeatcher teachers, DatabaseAdmin admin, DatabaseSubjects subjects, DatabaseGeneralInformation generalInformation) {
        this.students = students;
        this.teachers = teachers;
        this.admin = admin;
        this.subjects = subjects;
        this.generalInformation = generalInformation;
    }


}
