package general;

import utils.Global;
import utils.Utils;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

/**
 * Essa classe representa o cronograma de uma aula. Ela contém informações como horário, dia da semana, identificação,
 * ID da turma e ID da sala.
 */
public class ClassSchedule {
    private LocalTime time;
    private DayOfWeek dayOfWeek;
    private final String id;
    private String collegeClassId;
    private String roomId;

    /**
     * Constrói um objeto ClassSchedule com os parâmetros fornecidos.
     *
     * @param time             O horário da aula.
     * @param dayOfWeek        O dia da semana da aula.
     * @param collegeClassId   O ID da turma da aula.
     * @param roomId           O ID da sala da aula.
     */
    public ClassSchedule(LocalTime time, DayOfWeek dayOfWeek, String collegeClassId, String roomId) {
        this.time = time;
        this.dayOfWeek = dayOfWeek;
        this.collegeClassId = collegeClassId;
        this.id = UUID.randomUUID().toString();
        this.roomId = roomId;
    }

    /**
     * Obtém o ID da sala da aula.
     *
     * @return O ID da sala da aula.
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Obtém a sala da aula.
     *
     * @return A sala da aula.
     */
    public Room getRoom() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        return academicSystem.db.rooms.findById(roomId);
    }

    /**
     * Define o ID da sala da aula.
     *
     * @param roomId O ID da sala da aula a ser definido.
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * Obtém o ID da turma da aula.
     *
     * @return O ID da turma da aula.
     */
    public String getCollegeClassId() {
        return collegeClassId;
    }

    /**
     * Define o ID da turma da aula.
     *
     * @param collegeClassId O ID da turma da aula a ser definido.
     */
    public void setCollegeClassId(String collegeClassId) {
        this.collegeClassId = collegeClassId;
    }

    /**
     * Obtém o horário da aula.
     *
     * @return O horário da aula.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Define o horário da aula.
     *
     * @param time O horário da aula a ser definido.
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Obtém o dia da semana da aula.
     *
     * @return O dia da semana da aula.
     */
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Define o dia da semana da aula.
     *
     * @param dayOfWeek O dia da semana da aula a ser definido.
     */
    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Obtém a identificação da aula.
     *
     * @return A identificação da aula.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtém o dia da semana formatado.
     *
     * @return O dia da semana formatado.
     */
    public String getDayOfWeekFormatted() {
        return Utils.formatDayOfWeak(dayOfWeek);
    }

    /**
     * Obtém o horário formatado.
     *
     * @return O horário formatado.
     */
    public String getTimeFormatted() {
        return Utils.formatTime(time);
    }

    /**
     * Retorna uma representação em formato de string do objeto ClassSchedule.
     *
     * @return Uma string que representa o objeto ClassSchedule.
     */
    @Override
    public String toString() {
        return "Dia: %s | Horário: %s".formatted(getDayOfWeekFormatted(), getTimeFormatted());
    }

}