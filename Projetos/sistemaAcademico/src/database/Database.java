package database;

import general.Coordinator;
import general.Student;
import general.Teacher;
import interfaces.IModelDatabase;

public class Database {

    // ToDo Construir o construtores e definir as variaveis como finais
    public DatabaseStudent student;
    public IModelDatabase<Coordinator> coordinators;
    public IModelDatabase<Teacher> teachers;

    public DatabaseGeneralInformation generalInformation;


}
