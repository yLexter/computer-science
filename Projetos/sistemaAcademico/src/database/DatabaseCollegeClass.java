package database;

import general.CollegeClass;
import interfaces.IModelDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class DatabaseCollegeClass implements IModelDatabase<CollegeClass> {

    private List<CollegeClass> collegeClasses;

    public DatabaseCollegeClass(List<CollegeClass> collegeClasses) {
        this.collegeClasses = collegeClasses;
    }

    public DatabaseCollegeClass() {
        this.collegeClasses = new ArrayList<>();
    }

    @Override
    public List<CollegeClass> getAll() {
        return new ArrayList<>(collegeClasses);
    }

    @Override
    public void update(String id, CollegeClass data) {
        delete(id);
        save(data);
    }

    @Override
    public void delete(String id) {
        CollegeClass collegeClass = findById(id);

        collegeClasses.remove(collegeClass);
    }

    @Override
    public void save(CollegeClass data) {
        collegeClasses.add(data);
    }

    @Override
    public List<CollegeClass> findMany(Predicate<CollegeClass> callback) {
        return collegeClasses
                .stream()
                .filter(callback)
                .collect(Collectors.toList());
    }

    @Override
    public CollegeClass findById(String id) {
        return findOne(collegeClass -> collegeClass.getCode().equals(id));
    }

    @Override
    public CollegeClass findOne(Predicate<CollegeClass> callback) {
         return collegeClasses
                .stream()
                .filter(callback)
                .findAny()
                .orElse(null);
    }

    @Override
    public void updateAll(List<CollegeClass> data) {
        this.collegeClasses = data;
    }


}
