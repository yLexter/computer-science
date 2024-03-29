package general;

import utils.Global;
import utils.Role;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends Employee {

  
 /**
     * Construtor da classe Teacher.
     *
     * @param name        O nome do professor.
     * @param lastName    O sobrenome do professor.
     * @param dateOfBirth A data de nascimento do professor.
     * @param cpf         O CPF do professor.
     */
    public Teacher(String name, String lastName, LocalDate dateOfBirth, String cpf) {
        super(name, lastName, dateOfBirth, Role.TEACHER, cpf);
    }

    /**
     * Obtém as turmas em que o professor leciona.
     *
     * @return Uma lista de objetos CollegeClass representando as turmas do professor.
     */
    public List<CollegeClass> getCollegeClasses() {
        AcademicSystem academicSystem = Global.getAcademicSystem();

        return academicSystem.db.collegeClass.findMany(
                collegeClass -> collegeClass.getTeacher().getId().equals(this.getId())
        );
    }

 /**
     * Retorna uma representação em formato de texto do objeto Teacher.
     *
     * @return Uma representação em formato de texto do objeto Teacher.
     */
    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }


}

