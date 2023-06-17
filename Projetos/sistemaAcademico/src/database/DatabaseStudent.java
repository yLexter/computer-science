package database;

import erros.DatabaseException;
import erros.ErrorMessage;
import general.Student;
import general.SubjectStudent;

import java.util.List;

public class DatabaseStudent extends DatabaseEmployee<Student> {

    public List<SubjectStudent> getSubjectsStudent(String id) {
       Student student = findById(id);

       if (student == null)
           throw new DatabaseException(ErrorMessage.EMPLOYEE_NOT_FOUND);

       return student.getSubjects();
    }

    public List<SubjectStudent> getHistoric(String id) {
        Student student = findById(id);

        if (student == null)
            throw new DatabaseException(ErrorMessage.EMPLOYEE_NOT_FOUND);

        return student.getHistoric();
    }



}
