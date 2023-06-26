package interfaces.database;

import general.CollegeClass;
import general.RegisterClass;
import general.Student;
import general.SubjectStudent;

import java.util.List;

public interface IDatabaseCollegeClass extends IDatabaseBase<CollegeClass> {
    void saveCall(CollegeClass collegeClass, RegisterClass registerClass);
    List<SubjectStudent> getAllSubjectStudentOfStudent(String studentId);
    void addStudentToCollegesClasses(Student student, List<CollegeClass> collegeClasses);
    void removeStudentFromCollegeClasses(String studentId);
    void removeCollegeClassFromTeacher(String teacherId);
}
