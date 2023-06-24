package general;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

public class ClassSchedule {

    private LocalTime time;
    private DayOfWeek dayOfWeek;
    private final String id;

    public ClassSchedule(LocalTime time, DayOfWeek dayOfWeek) {
        this.time = time;
        this.dayOfWeek = dayOfWeek;
        this.id = UUID.randomUUID().toString();
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getId() {
        return id;
    }


}