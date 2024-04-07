package entities;

import java.util.UUID;

public class Funcionario {
    protected String nome;

    protected String sobrenome;

    protected String matricula;

    protected String cpf;

    public Funcionario(String nome, String sobrenome, String cpf) {

        if (nome == null || sobrenome == null) {
            throw new IllegalArgumentException("Nome e sobrenome não podem ser nulos");
        }

        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.matricula = UUID.randomUUID().toString();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "%s %s (%s)".formatted(nome, sobrenome, matricula);
    }
}
