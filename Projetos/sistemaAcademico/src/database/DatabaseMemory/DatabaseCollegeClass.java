package database.DatabaseMemory;

import general.*;
import interfaces.database.IDatabaseCollegeClass;

import java.util.List;


public class DatabaseCollegeClass extends DatabaseBase<CollegeClass> implements IDatabaseCollegeClass {

    @Override
    public CollegeClass findById(String id) {
        return findOne(collegeClass -> collegeClass.getCollegeClassId().equals(id));
    }

    @Override
    public void saveCall(CollegeClass collegeClass, RegisterClass registerClass) {
         collegeClass.getRegisterClasses().add(registerClass);
         update(registerClass.getClassId(), collegeClass);
    }

    @Override
    public void addStudentToCollegesClasses(Student student, List<CollegeClass> collegeClasses) {

        for(CollegeClass collegeClass : collegeClasses) {

            SubjectStudent subjectStudent = Subject.studentToSubjectStudent(
                    collegeClass,
                    student,
                    collegeClass.getCollegeClassId()
            );

           collegeClass.addSubjectStudent(subjectStudent);

           update(collegeClass.getCollegeClassId(), collegeClass);
        }

    }

    @Override
    public void removeStudentFromCollegeClasses(String studentId) {

        for(CollegeClass collegeClass : getAll()) {
            collegeClass
                    .getStudents()
                    .stream()
                    .filter(x -> x.getStudent().getId().equals(studentId))
                    .findAny()
                    .ifPresent(subjectStudent -> collegeClass.getStudents().remove(subjectStudent));
        }

    }

    @Override
    public void removeCollegeClassFromTeacher(String teacherId) {

        for(CollegeClass collegeClass : getAll()) {

            String idTeacherCollegeClass = collegeClass.getTeacher().getId();

            if (idTeacherCollegeClass.equals(teacherId))
                collegeClass.setTeacherId(null);

        }
    }

    // ToDo implementar metodo
    @Override
    public List<SubjectStudent> getAllSubjectStudentOfStudent(String studentId) {
        return null;
    }




}
