package general;

import utils.Global;
import utils.Role;
import java.time.LocalDate;
import java.util.List;

public class Teacher extends Employee {
    public Teacher(String name, String lastName, int age, LocalDate dateOfBirth, String cpf, double salary) {
        super(name, lastName, age, dateOfBirth, Role.TEACHER, cpf);
    }
    public List<CollegeClass> getCollegeClass() {
        AcademicSystem academicSystem = Global.getAcademicSystem();

        return academicSystem.db.collegeClass.findMany(
                collegeClass -> collegeClass.getTeacher().getId().equals(this.getId())
        );

    }

}

