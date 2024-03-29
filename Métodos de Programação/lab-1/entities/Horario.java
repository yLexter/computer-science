package entities;

public class Horario {
    private String horario;

    private String idTurma;

    public Horario(String horario, String idTurma) {
        this.horario = horario;
        this.idTurma = idTurma;
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

    @Override
    public String toString() {
        return "%s".formatted(horario);
    }
}
