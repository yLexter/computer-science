package general;

import java.time.LocalDate;
import java.util.List;

/**
 * Essa classe representa o registro de presença em uma aula. Ela contém a descrição da aula, a data em que ocorreu,
 * uma lista de chamada dos alunos e o identificador da turma.
 */
public class RegisterClass {
 /**
     * Essa classe interna representa o registro de chamada   de um aluno em uma aula. Ela armazena o ID do aluno, se ele
     * faltou à aula e se a falta está justificada.
     */
  
    public record StudentCallLog (String id, boolean isMissed, boolean isJustified) {
        @Override
        public String id() {
            return id;
        }

        @Override
        public boolean isMissed() {
            return isMissed;
        }

        @Override
        public boolean isJustified() {
            return isJustified;
        }
    }

  
    private String description;
    private LocalDate date;
    private List<StudentCallLog> listCall;
    private String classId;

  /**
     * Cria um novo objeto RegisterClass com a descrição da aula, a data em que ocorreu, a lista de chamada dos alunos
     * e o id da turma.
     *
     * @param description A descrição da aula.
     * @param date        A data em que a aula ocorreu.
     * @param listCall    A lista de chamada dos alunos.
     * @param classId     O id da turma.
     */
    public RegisterClass(String description, LocalDate date, List<StudentCallLog> listCall, String teacherId) {
        this.description = description;
        this.date = date;
        this.listCall = listCall;
        this.classId = teacherId;
    }

  
     /**
     * Obtém a descrição da aula.
     *
     * @return A descrição da aula.
     */
    public String getDescription() {
        return description;
    }
  
    /**
     * Define a descrição da aula.
     *
     * @param description A descrição da aula a ser definida.
     */
    public void setDescription(String description) {
        this.description = description;
    }

     /**
     * Obtém a data em que a aula ocorreu.
     *
     * @return A data da aula.
     */
    public LocalDate getDate() {
        return date;
    }

  
     /**
     * Define a data em que a aula ocorreu.
     *
     * @param date A data da aula a ser definida.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

   /**
     * Obtém a lista de chamada dos alunos.
     *
     * @return A lista de chamada dos alunos.
     */
    public List<StudentCallLog> getListCall() {
        return listCall;
    }

   /**
     * Define a lista de chamada dos alunos.
     *
     * @param listCall A lista de chamada dos alunos a ser definida.
     */
    public void setListCall(List<StudentCallLog> listCall) {
        this.listCall = listCall;
    }

  /**
     * Obtém o identificador da turma.
     *
     * @return O identificador da turma.
     */
    public String getClassId() {
        return classId;
    }

  /**
     * Define o identificador da turma.
     *
     * @param classId O identificador da turma a ser definido.
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }

   /**
     * Obtém o número total de alunos que faltaram à aula.
     *
     * @return O número total de alunos que faltaram à aula.
     */
    public int getTotalStudentsSissed() {
        return listCall
                .stream()
                .reduce(0, (acc, call) -> {
                  return acc +=  call.isMissed() ? 1 : 0;
                }, (acc1, acc2) -> acc1);
    }


}
