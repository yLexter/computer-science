package database.DatabaseMemory;

import general.Room;
import interfaces.database.IDatabaseRoom;

public class DatabaseRoom extends DatabaseBase<Room> implements IDatabaseRoom {

    @Override
    public Room findById(String id) {
        return findOne(room -> room.getRoomId().toLowerCase().equals(id));
    }




}
