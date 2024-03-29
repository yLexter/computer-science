package database.DatabaseMemory;

import general.Teacher;
import interfaces.database.IDatabaseTeacher;

/**
 * Esta é uma classe que representa um banco de dados de professores.
 * Ela estende a classe `DatabaseEmployee` e implementa a interface `IDatabaseTeacher`.
 * 
 * Aqui são fornecidas operações de banco de dados específicas para professores.
 * A classe genérica `Teacher` é utilizada para representar professores.
 * 
 * Não há métodos adicionais nesta classe, pois todas as operações de banco de dados
 * são herdadas da classe `DatabaseEmployee`.
 */
public class DatabaseTeacher extends DatabaseEmployee<Teacher> implements IDatabaseTeacher {}
