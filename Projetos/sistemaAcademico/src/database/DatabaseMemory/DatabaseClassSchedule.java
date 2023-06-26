package database.DatabaseMemory;

import general.ClassSchedule;
import interfaces.database.IDatabaseBase;
import interfaces.database.IDatabaseClassSchedule;
import utils.Utils;

import java.util.List;

public class DatabaseClassSchedule extends DatabaseBase<ClassSchedule> implements IDatabaseClassSchedule {

    @Override
    public ClassSchedule findById(String id) {
        return findOne(classSchedule -> classSchedule.getId().equals(id));
    }
    @Override
    public List<String> getTimeFormated(List<String> ids) {
        return findMany(classSchedule -> {
            return ids.stream().anyMatch(id -> classSchedule.getId().equals(id));
        })
           .stream()
           .map(classSchedule -> "Dia: %s | Hórario: %s".formatted(Utils.formatDayOfWeak(classSchedule.getDayOfWeek()), Utils.formatTime(classSchedule.getTime())))
           .toList();
    }

}
