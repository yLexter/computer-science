package database.DatabaseMemory;

import general.Subject;
import interfaces.database.IDatabaseSubject;

/**
 * Esta é uma classe que representa um banco de dados de disciplinas.
 * Ela estende a classe `DatabaseBase` e implementa a interface `IDatabaseSubject`.
 * 
 * Aqui são fornecidas operações de banco de dados específicas para disciplinas.
 * A classe genérica `Subject` é utilizada para representar disciplinas.
 * 
 * Os métodos nesta classe permitem encontrar uma disciplina pelo código.
 */
public class DatabaseSubject extends DatabaseBase<Subject> implements IDatabaseSubject {

  /**
     * Encontra uma disciplina pelo código.
     *
     * @param code O código da disciplina a ser encontrado.
     * @return A disciplina encontrada ou null se não encontrada.
     */
    @Override
    public Subject findById(String code) {
        return findOne(subject -> subject.getCode().equals(code));
    }

}
