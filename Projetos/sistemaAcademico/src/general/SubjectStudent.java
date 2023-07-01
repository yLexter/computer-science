package general;

import utils.Global;
import utils.StudentSubjectStatus;
import utils.Utils;

import java.util.List;

/**
 * Esta classe representa a relação entre um estudante e uma disciplina.
 * Ela herda os atributos e comportamentos da classe Subject.
 */
public class SubjectStudent extends Subject {
    private Float note1 = null;
    private Float note2 = null;
    private Float finalExameScore = null;
    private StudentSubjectStatus status;
    private String studentId;
    private Integer absences = 0;
    private final String period;
    private String idCollegeClass;
    public static final double finalWeight = 0.4;

   /**
     * Construtor da classe SubjectStudent.
     *
     * @param code            O código da disciplina.
     * @param name            O nome da disciplina.
     * @param hours           A carga horária da disciplina.
     * @param studentId       O ID do estudante.
     * @param idCollegeClass  O ID da turma relacionada ao estudante.
     */
    public SubjectStudent(String code, String name, int hours, String studentId, String idCollegeClass) {
        super(code, name, hours);
        this.studentId = studentId;
        this.status = StudentSubjectStatus.PENDING;
        this.idCollegeClass = idCollegeClass;
        this.period = Global.getAcademicSystem().getCurrentPeriod();
    }

  /**
     * Obtém o ID da turma relacionada ao estudante.
     *
     * @return O ID da turma relacionada ao estudante.
     */
    public String getIdCollegeClass() {
        return idCollegeClass;
    }

   /**
     * Define o ID da turma relacionada ao estudante.
     *
     * @param idCollegeClass O ID da turma relacionada ao estudante.
     */
    public void setIdCollegeClass(String idCollegeClass) {
        this.idCollegeClass = idCollegeClass;
    }

   /**
     * Obtém a média do estudante na disciplina.
     *
     * @return A média do estudante na disciplina.
     */
    public Float getAverage() {
        if (note1 == null || note2 == null)
            return null;
        return (note1 + note2) / 2;
    }

   /**
     * Obtém a primeira nota do estudante.
     *
     * @return A primeira nota do estudante.
     */
    public Float getNote1() {
        return note1;
    }

   /**
     * Define a primeira nota do estudante.
     *
     * @param note1 A primeira nota do estudante.
     */
    public void setNote1(Float note1) {
        this.note1 = note1;
    }

  /**
     * Obtém a segunda nota do estudante.
     *
     * @return A segunda nota do estudante.
     */
    public Float getNote2() {
        return note2;
    }

  /**
     * Define a segunda nota do estudante.
     *
     * @param note2 A segunda nota do estudante.
     */
    public void setNote2(Float note2) {
        this.note2 = note2;
    }

  
 /**
     * Obtém a quantidade de faltas do estudante.
     *
     * @return A quantidade de faltas do estudante.
     */
    public Integer getAbsences() {
        return absences;
    }

   /**
     * Obtém a nota do exame final do estudante.
     *
     * @return A nota do exame final do estudante.
     */
    public Float getFinalExameScore() {
        return finalExameScore;
    }

  /**
     * Define a nota do exame final do estudante.
     *
     * @param finalExameScore A nota do exame final do estudante.
     */
    public void setFinalExameScore(Float finalExameScore) {
        this.finalExameScore = finalExameScore;
    }

  /**
     * Obtém o status do estudante na disciplina.
     *
     * @return O status do estudante na disciplina.
     */
    public StudentSubjectStatus getStatus() {
        return status;
    }

   /**
     * Obtém o ID do estudante.
     *
     * @return O ID do estudante.
     */
    public String getStudentId() {
        return studentId;
    }

  /**
    Seta as faltas dp estudante em relação a disciplina.
     */
    private void setAbsences(Integer absences) {
        this.absences = absences;
    }

  /**
     * Incrementa o número de faltas do estudante com base nas configurações do sistema acadêmico.
     */
    public void increaseAbsences() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        Integer total = academicSystem.getSettings().getTotalAbsemcesPerClass();

        setAbsences(absences + total);
    }

  /**
     * Obtém o objeto Student relacionado ao estudante.
     *
     * @return O objeto Student relacionado ao estudante.
     */
    public Student getStudent() {
       AcademicSystem academicSystem = Global.getAcademicSystem();

       return academicSystem.db.students.findById(studentId);
    }

  /**
     * Define o status do estudante na disciplina.
     *
     * @param status O status do estudante na disciplina.
     */
    public void setStatus(StudentSubjectStatus status) {
        this.status = status;
    }

   /**
     * Obtém o período atual.
     *
     * @return O período atual.
     */
    public String getPeriod() {
        return period;
    }

  /**
     * Obtém uma lista de notas do estudante.
     *
     * @return Uma lista de notas do estudante.
     */
    public List<String> getListNotes() {
        return List.of(
           Utils.objectToString(note1),
           Utils.objectToString(note2),
           Utils.objectToString(finalExameScore)
        );
    }

  /**
     * Verifica se o estudante está aprovado na disciplina.
     *
     * @return true se o estudante está aprovado, false caso contrário.
     */
    public boolean checkIsPassing() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        Float average = getAverage();

        double overallAverage  = average * (1 - finalWeight) + average * finalWeight;

        return overallAverage >= academicSystem.getSettings().getMinimumAverage();
    }

   /**
     * Retorna uma representação em formato de texto do objeto SubjectStudent.
     *
     * @return Uma representação em formato de texto do objeto SubjectStudent.
     */
    public String toString() {
        return String.format("%s", super.toString());
    }

}
