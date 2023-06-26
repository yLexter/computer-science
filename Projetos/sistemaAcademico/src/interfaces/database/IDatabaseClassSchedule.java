package interfaces.database;

import general.ClassSchedule;

import java.util.List;

public interface IDatabaseClassSchedule extends IDatabaseBase<ClassSchedule>{
    List<String> getTimeFormated(List<String> ids);
}
