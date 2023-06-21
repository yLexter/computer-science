package database;

import general.CollegeClass;
import general.RegisterClass;
import general.Student;
import general.SubjectStudent;

import java.util.List;


public class DatabaseCollegeClass extends DatabaseBase<CollegeClass> {

    @Override
    public CollegeClass findById(String id) {
        return findOne(collegeClass -> collegeClass.getClassId().equals(id));
    }

    public void saveCall(CollegeClass collegeClass, RegisterClass registerClass) {
         collegeClass.getRegisterClasses().add(registerClass);
         update(registerClass.getClassId(), collegeClass);
    }


    // ToDo terminar de implementar o metodo
    public List<SubjectStudent> getAllSubjectStudentOfStudent(Student student) {
        return null;
    }




}
