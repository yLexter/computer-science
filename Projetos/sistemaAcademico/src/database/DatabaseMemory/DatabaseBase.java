package database.DatabaseMemory;

import interfaces.database.IDatabaseBase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class DatabaseBase<T> implements IDatabaseBase<T> {

    protected List<T> data;

    public DatabaseBase() {
        this.data = new ArrayList<>();
    }

    public DatabaseBase(List<T> data) {
        this.data = new ArrayList<>(data);
    }

    @Override
    public List<T> getAll() {
       return new ArrayList<>(data);
    }


    @Override
    public void update(String id, T employee) {
        delete(id);
        save(employee);
    }

    @Override
    public void delete(String id) {
        T content = findById(id);

        data.remove(content);
    }

    @Override
    public void save(T content) {
        data.add(content);
    }

    @Override
    public T findOne(Predicate<T> callback) {
        return getAll()
                .stream()
                .filter(callback)
                .findAny()
                .orElse(null);
    }

    @Override
    public List<T> findMany(Predicate<T> callback) {
        return getAll()
                .stream()
                .filter(callback)
                .collect(Collectors.toList());
    }

    @Override
    public abstract T findById(String id);

    @Override
    public void updateAll(List<T> data) {
        this.data = new ArrayList<>(data);
    }


}
