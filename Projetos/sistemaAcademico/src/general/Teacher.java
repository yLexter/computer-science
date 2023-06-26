package general;

import utils.Global;
import utils.Role;
import java.time.LocalDate;
import java.util.List;

public class Teacher extends Employee {

    public Teacher(String name, String lastName, LocalDate dateOfBirth, String cpf) {
        super(name, lastName, dateOfBirth, Role.TEACHER, cpf);
    }


    public List<CollegeClass> getCollegeClasses() {
        AcademicSystem academicSystem = Global.getAcademicSystem();

        return academicSystem.db.collegeClass.findMany(
                collegeClass -> collegeClass.getTeacher().getId().equals(this.getId())
        );
    }


    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }


}

