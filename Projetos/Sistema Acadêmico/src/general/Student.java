package general;

import utils.Global;
import utils.Role;

import java.time.LocalDate;
import java.util.*;

/**
 * Esta classe representa um estudante, que é um tipo de funcionário do sistema.
 * Um estudante possui informações adicionais, como as disciplinas em que está matriculado,
 * o curso em que está inscrito, o histórico acadêmico e o resultado do vestibular.
 */
public class Student extends Employee {
    private List<String> collegeClasses;
    private String course;
    private List<SubjectStudent> historic;
    private EntranceExam entranceExam;

  /**
     * Construtor da classe Student.
     *
     * @param name          O nome do estudante.
     * @param lastName      O sobrenome do estudante.
     * @param dateOfBirth   A data de nascimento do estudante.
     * @param cpf           O CPF do estudante.
     * @param course        O curso em que o estudante está inscrito.
     * @param entranceExam  O resultado do vestibular do estudante.
     */
    public Student(String name, String lastName, LocalDate dateOfBirth, String cpf, String course, EntranceExam entranceExam) {
        super(name, lastName, dateOfBirth, Role.STUDENT, cpf);
        this.course = course;
        this.entranceExam = entranceExam;
        this.collegeClasses = new ArrayList<>();
        this.historic = new ArrayList<>();
    }
  
/**
     * Obtém as disciplinas em que o estudante está matriculado.
     *
     * @return A lista das disciplinas em que o estudante está matriculado.
     */
    public ArrayList<SubjectStudent> getSubjectStudent() {
        AcademicSystem academicSystem = Global.getAcademicSystem();

        return academicSystem.db.collegeClass.getAllSubjectStudentOfStudent(getId());
    }

    /**
     * Obtém as disciplinas em que o estudante está matriculado.
     *
     * @return A lista das disciplinas em que o estudante está matriculado.
     */
    public List<String> getCollegeClasses() {
        return collegeClasses;
    }
   /**
     * Obtém as IDs das disciplinas em que o estudante está matriculado.
     *
     * @return A lista das IDs das disciplinas em que o estudante está matriculado.
     */
    public List<String> getSubjectsId() {
        return collegeClasses;
    }

  /**
     * Define as disciplinas em que o estudante está matriculado.
     *
     * @param collegeClasses A lista das disciplinas em que o estudante está matriculado.
     */
    public void setCollegeClasses(List<String> collegeClasses) {
        this.collegeClasses = collegeClasses;
    }

  
    /**
     * Define o resultado do vestibular do estudante.
     *
     * @param entranceExam O resultado do vestibular do estudante.
     */
    public void setEntranceExam(EntranceExam entranceExam) {
        this.entranceExam = entranceExam;
    }

   /**
     * Obtém o curso em que o estudante está inscrito.
     *
     * @return O curso em que o estudante está inscrito.
     */
    public String getCourse() {
        return course;
    }

   /**
     * Define o curso em que o estudante está inscrito.
     *
     * @param course O curso em que o estudante está inscrito.
     */
    public void setCourse(String course) {
        this.course = course;
    }

  
    /**
     * Obtém o histórico acadêmico do estudante.
     *
     * @return O histórico acadêmico do estudante.
     */
    public List<SubjectStudent> getHistoric() {
        return historic;
    }
  

    /**
     * Define o histórico acadêmico do estudante.
     *
     * @param historic O histórico acadêmico do estudante.
     */
    public void setHistoric(List<SubjectStudent> historic) {
        this.historic = historic;
    }

  /**
     * Obtém o resultado do vestibular do estudante.
     *
     * @return O resultado do vestibular do estudante.
     */
    public EntranceExam getEntranceExam() {
        return entranceExam;
    }

  /**
     * Adiciona uma disciplina ao histórico acadêmico do estudante.
     *
     * @param subjectStudent A disciplina a ser adicionada ao histórico acadêmico do estudante.
     */
    public void addInHistoric(SubjectStudent subjectStudent) {
        historic.add(subjectStudent);
    }

   /**
     * Calcula o Coeficiente de Rendimento Acadêmico (CRA) do estudante.
     *
     * @return O CRA do estudante.
     */
    public double getCRA() {
        float weightedSum = 0;
        float totalHours = 0;

        for (SubjectStudent subjectStudent : historic) {
            Float note1 = subjectStudent.getNote1();
            Float note2 = subjectStudent.getNote2();
            Float finalExamScore = subjectStudent.getFinalExameScore();
            int hours = subjectStudent.getHours();

            if (finalExamScore != null) {
                float weightedAverage = (note1 + note2 + finalExamScore * 2) / 4;
                weightedSum += weightedAverage * hours;
            } else {
                float weightedAverage = (note1 + note2) / 2;
                weightedSum += weightedAverage * hours;
            }

            totalHours += hours;
        }

        if (totalHours == 0 || weightedSum == 0)
            return 0;

        return weightedSum / totalHours;
    }

  /**
     * Retorna uma representação em formato de texto do estudante.
     *
     * @return Uma representação em formato de texto do estudante.
     */
    @Override
    public String toString() {
        return String.format("%s | %s", super.toString(), course);
    }

}
