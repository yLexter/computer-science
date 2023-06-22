package database;

import general.Subject;
import interfaces.IModelDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DatabaseSubjects extends DatabaseBase<Subject> {

    @Override
    public Subject findById(String code) {
        return findOne(subject -> subject.getCode().equals(code));
    }


}
