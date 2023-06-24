package interfaces.database;

import java.util.List;
import java.util.function.Predicate;

public interface IDatabaseBase<T> {
    List<T> getAll();
    void update(String id, T data);
    void delete(String id);
    void save(T data);
    List<T> findMany(Predicate<T> callback);
    T findById(String id);
    T findOne(Predicate<T> callback);
    void updateAll(List<T> data);
    void saveMany(List<T> data);
}
