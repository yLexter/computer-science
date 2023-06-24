package interfaces.database;

import general.CollegeClass;
import general.RegisterClass;
import general.Student;
import general.SubjectStudent;

import java.util.List;

public interface IDatabaseCollegeClass extends IDatabaseBase<CollegeClass> {
    void saveCall(CollegeClass collegeClass, RegisterClass registerClass);
    List<SubjectStudent>  getAllSubjectStudentOfStudent(Student student);
}
