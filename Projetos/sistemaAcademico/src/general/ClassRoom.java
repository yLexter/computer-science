package general;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.UUID;

public class ClassRoom extends Room {

    private LocalTime time;

    private DayOfWeek dayOfWeek;

    private final String id;

    public ClassRoom(String roomId, int capacity, LocalTime time, DayOfWeek dayOfWeek) {
        super(roomId, capacity);
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

    // ToDo Implementar dia da semana na data formatada
    public String formatTime() {
        return String.format("%s, %s" , time.format(DateTimeFormatter.ofPattern("HH:mm")), dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()));
    }

    public String toString() {
        return String.format("%s | ID: %s | Horario: %s", super.toString(), id, formatTime());
    }






}
