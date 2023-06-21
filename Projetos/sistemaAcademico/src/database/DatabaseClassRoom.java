package database;

import general.ClassRoom;
import general.Room;
import general.Subject;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseClassRoom extends DatabaseBase<ClassRoom>{

    @Override
    public ClassRoom findById(String id) {
        return findOne(classRoom -> classRoom.getId().equals(id));
    }

     // ToDo implementar metodo
    public boolean checkVacantClassCchedule(LocalTime time, String roomId) {
        return true;
    }


}
