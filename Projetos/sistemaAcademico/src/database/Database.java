package database;

public class Database {
    public final DatabaseStudent students;
    public final DatabaseTeatcher teachers;
    public final DatabaseAdmin admin;
    public final DatabaseSubjects subjects;

    public Database(DatabaseStudent students, DatabaseTeatcher teachers, DatabaseAdmin admin, DatabaseSubjects subjects) {
        this.students = students;
        this.teachers = teachers;
        this.admin = admin;
        this.subjects = subjects;
    }


}
