package database;

import general.Room;
import interfaces.IModelDatabase;

public class DatabaseRooms extends DatabaseBase<Room> {

    @Override
    public Room findById(String id) {
        return findOne(room -> room.getRoomId().equals(id));
    }


}
