package database.DatabaseMemory;

import general.Student;
import interfaces.database.IDatabaseStudent;

public class DatabaseStudent extends DatabaseEmployee<Student> implements IDatabaseStudent {

    @Override
    public void removeCollegeClassFromStudents(String collegeClassId) {
        for(Student student : getAll()) {
               student
                    .getCollegeClasses()
                    .stream()
                    .filter(id -> id.equals(collegeClassId))
                    .findAny()
                    .ifPresent(id -> student.getCollegeClasses().remove(id));
        }
    }


}
