package database;

import erros.DatabaseException;
import erros.ErrorMessage;
import general.Student;
import general.SubjectStudent;
import interfaces.IModelDatabase;

import java.util.List;

public class DatabaseStudent {

    public IModelDatabase<Student> db;
    public DatabaseStudent(IModelDatabase<Student> db) {
        this.db = db;
    }

    public List<SubjectStudent> getSubjectsStudent(String id) {
       Student student = db.findById(id);

       if (student == null)
           throw new DatabaseException(ErrorMessage.EMPLOYEE_NOT_FOUND);

       return student.getSubjects();
    }

    public List<SubjectStudent> getHistoric(String id) {
        Student student = db.findById(id);

        if (student == null)
            throw new DatabaseException(ErrorMessage.EMPLOYEE_NOT_FOUND);

        return student.getHistoric();
    }


}
