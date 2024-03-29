package entities;

public class Disciplina {
    protected String codigo;
    protected String nome;
    protected int horas;
    protected int periodo;

    public Disciplina(String codigo, String nome, int horas, int periodo) {

        if (codigo == null || nome == null || horas <= 0)
            throw new IllegalArgumentException("Nome ou codigo não pode ser nulos e hora > 0");

        this.periodo = periodo;
        this.codigo = codigo;
        this.nome = nome;
        this.horas = horas;
    }

    public Disciplina(String codigo, String nome, int periodo) {
        if (codigo == null || nome == null || periodo <= 0)
            throw new IllegalArgumentException("Nome ou codigo não pode ser nulos e periodo > 0");

        this.periodo = periodo;
        this.codigo = codigo;
        this.nome = nome;
        this.horas = 60;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
}
