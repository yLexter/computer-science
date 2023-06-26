package general;

import java.util.List;
import java.util.UUID;

public class ClassRoom extends Room  {
    private List<String> classSchedules;
    private final String id;
    public ClassRoom(String roomId, int capacity, List<String> classSchedules) {
        super(roomId, capacity);
        this.classSchedules = classSchedules;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    // ToDo Implementar dia da semana na data formatada
    public String formatTime() {
        return "\n";
    }


    public String toString() {
        return String.format("%s | ID: %s | Horario: %s", super.toString(), id, formatTime());
    }

}
