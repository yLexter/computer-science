package general;

import database.Database;
import erros.DatabaseException;
import erros.ErrorMessage;

public class AcademicSystem {

    public final Database db;

    public AcademicSystem(Database db) {
        this.db = db;
    }

    public Employee autenticate(String id, String password) {

        Employee isAdmin = db.admin.authenticate(id, password);

        if (isAdmin != null)
            return isAdmin;

        Employee isStudent = db.students.authenticate(id, password);

        if (isStudent != null)
            return isStudent;

        Employee isTeatcher = db.teachers.authenticate(id, password);

        if (isTeatcher != null)
            return isTeatcher;

        throw new DatabaseException(ErrorMessage.EMPLOYEE_NOT_AUTHENTICATE);
    }

}
