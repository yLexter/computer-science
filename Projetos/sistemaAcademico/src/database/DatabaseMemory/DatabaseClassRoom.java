package database.DatabaseMemory;

import general.ClassRoom;
import interfaces.database.IDatabaseClassRoom;

import java.time.LocalTime;

public class DatabaseClassRoom extends DatabaseBase<ClassRoom> implements IDatabaseClassRoom {

    @Override
    public ClassRoom findById(String id) {
        return findOne(classRoom -> classRoom.getId().equals(id));
    }

     // ToDo implementar metodo
    public boolean checkVacantClassCchedule(LocalTime time, String roomId) {
        return true;
    }


}
