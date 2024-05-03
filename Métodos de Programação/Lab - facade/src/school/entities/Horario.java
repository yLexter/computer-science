package school.entities;

public class Horario {
    private String horario;

    private String idTurma;

    private String salaId;

    public Horario(String horario, String idTurma, String salaId) {
        this.horario = horario;
        this.idTurma = idTurma;
        this.salaId = salaId;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(String idTurma) {
        this.idTurma = idTurma;
    }

    public String getSalaId() {
        return salaId;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }

    @Override
    public String toString() {
        return "Horario: [%s] | Sala: %s".formatted(horario, salaId);
    }
}
