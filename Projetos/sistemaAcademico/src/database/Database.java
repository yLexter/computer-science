package database;

import general.*;
import java.util.List;

public class Database {
    public record AllData(List<Student> students, List<Teacher> teachers, List<Admin> admins, List<Subject> subjects, GeneralInformation generalInformation, List<CollegeClass> collegeClasses) {
        @Override
        public List<Student> students() {
            return students;
        }

        @Override
        public List<Teacher> teachers() {
            return teachers;
        }

        @Override
        public List<Admin> admins() {
            return admins;
        }

        @Override
        public List<Subject> subjects() {
            return subjects;
        }

        @Override
        public GeneralInformation generalInformation() {
            return generalInformation;
        }
        @Override
        public List<CollegeClass> collegeClasses() {
            return collegeClasses;
        }
    }
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

    public AllData findAll() {
        return new AllData(
           students.getAll(),
           teachers.getAll(),
           admin.getAll(),
           subjects.getAll(),
           generalInformation.getData(),
           collegeClass.getAll()
        );
    }

}
