package interfaces.database;

import general.ClassRoom;

import java.time.LocalTime;

public interface IDatabaseClassRoom extends IDatabaseBase<ClassRoom> {
    boolean checkVacantClassCchedule(LocalTime time, String roomId);
}
