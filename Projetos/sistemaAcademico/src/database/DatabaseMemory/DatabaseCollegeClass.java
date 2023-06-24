package database.DatabaseMemory;

import general.CollegeClass;
import general.RegisterClass;
import general.Student;
import general.SubjectStudent;
import interfaces.database.IDatabaseCollegeClass;

import java.util.List;


public class DatabaseCollegeClass extends DatabaseBase<CollegeClass> implements IDatabaseCollegeClass {

    @Override
    public CollegeClass findById(String id) {
        return findOne(collegeClass -> collegeClass.getClassId().equals(id));
    }

    @Override
    public void saveCall(CollegeClass collegeClass, RegisterClass registerClass) {
         collegeClass.getRegisterClasses().add(registerClass);
         update(registerClass.getClassId(), collegeClass);
    }


    // ToDo terminar de implementar o metodo
    @Override
    public List<SubjectStudent> getAllSubjectStudentOfStudent(Student student) {
        return null;
    }




}
