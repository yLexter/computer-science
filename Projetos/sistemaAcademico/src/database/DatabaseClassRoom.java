package database;

import general.ClassRoom;

public class DatabaseClassRoom extends DatabaseBase<ClassRoom>{

    @Override
    public ClassRoom findById(String id) {
        return findOne(classRoom -> classRoom.getId().equals(id));
    }

}
