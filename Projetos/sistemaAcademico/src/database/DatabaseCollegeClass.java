package database;

import general.CollegeClass;
import general.SubjectStudent;
import interfaces.IModelDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class DatabaseCollegeClass extends DatabaseBase<CollegeClass> {

    @Override
    public CollegeClass findById(String id) {
        return findOne(collegeClass -> collegeClass.getCode().equals(id));
    }

    public void saveCall(List<SubjectStudent> list) {


    }


}
