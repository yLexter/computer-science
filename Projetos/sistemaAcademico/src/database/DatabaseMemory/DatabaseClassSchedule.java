package database.DatabaseMemory;

import general.ClassSchedule;
import interfaces.database.IDatabaseBase;
import interfaces.database.IDatabaseClassSchedule;

public class DatabaseClassSchedule extends DatabaseBase<ClassSchedule> implements IDatabaseClassSchedule {

    @Override
    public ClassSchedule findById(String id) {
        return findOne(classSchedule -> classSchedule.getId().equals(id));
    }

}
