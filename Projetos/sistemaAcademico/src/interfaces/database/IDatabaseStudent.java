package interfaces.database;

import general.Student;

public interface IDatabaseStudent extends IDatabaseEmployee<Student> {
    void removeCollegeClassFromStudents(String collegeClassId);
}
