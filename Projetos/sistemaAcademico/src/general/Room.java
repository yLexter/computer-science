package general;

public class Room {

    private String roomId;

    private int capacity;

    public Room(String roomId, int capacity) {
        this.roomId = roomId;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return String.format("Sala: %s | Capacidade: %d", roomId, capacity);
    }

}
