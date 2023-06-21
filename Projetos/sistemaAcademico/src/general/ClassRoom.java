package general;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClassRoom {

    private int capacity;

    private String id;

    private LocalDate time;

    public ClassRoom(int capacity, String id, LocalDate time) {
        this.capacity = capacity;
        this.id = id;
        this.time = time;
    }


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }


    public String formatTime() {
        return time.format(DateTimeFormatter.ofPattern("EEEE HH:mm"));
    }


}
