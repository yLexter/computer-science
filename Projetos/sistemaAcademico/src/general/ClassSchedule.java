package general;

import utils.Utils;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

public class ClassSchedule {

    private LocalTime time;
    private DayOfWeek dayOfWeek;
    private final String id;
    private String collegeClassId;

    public ClassSchedule(LocalTime time, DayOfWeek dayOfWeek, String collegeClassId) {
        this.time = time;
        this.dayOfWeek = dayOfWeek;
        this.collegeClassId = collegeClassId;
        this.id = UUID.randomUUID().toString();
    }

    public String getCollegeClassId() {
        return collegeClassId;
    }

    public void setCollegeClassId(String collegeClassId) {
        this.collegeClassId = collegeClassId;
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

    @Override
    public String toString() {
        return "Dia: %s | Hórario: %s".formatted(Utils.formatDayOfWeak(dayOfWeek), Utils.formatTime(time));
    }
}