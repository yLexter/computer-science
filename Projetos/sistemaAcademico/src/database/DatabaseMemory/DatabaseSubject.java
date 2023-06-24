package database.DatabaseMemory;

import general.Subject;
import interfaces.database.IDatabaseSubject;

public class DatabaseSubject extends DatabaseBase<Subject> implements IDatabaseSubject {

    @Override
    public Subject findById(String code) {
        return findOne(subject -> subject.getCode().equals(code));
    }

}
