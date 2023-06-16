package general;

import database.Database;
import database.DatabaseMemory;
import erros.DatabaseException;
import erros.ErrorMessage;
import interfaces.IModelDatabase;

import java.util.ArrayList;
import java.util.List;

public class AcademicSystem {

    private final Database db;
    public AcademicSystem(Database db) {
        this.db = db;
    }

    public Employee autenticate(String id, String Password) {
        // ToDo Implementar Login do usuario de qualquer tipo
        return null;
    }






}
