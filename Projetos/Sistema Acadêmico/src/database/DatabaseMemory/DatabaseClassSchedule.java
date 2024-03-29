package database.DatabaseMemory;

import general.AcademicSystem;
import general.ClassSchedule;
import interfaces.database.IDatabaseBase;
import interfaces.database.IDatabaseClassSchedule;
import utils.Global;
import utils.Utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Implementação da interface IDatabaseClassSchedule que representa um banco de dados em memória para objetos ClassSchedule.
 */
public class DatabaseClassSchedule extends DatabaseBase<ClassSchedule> implements IDatabaseClassSchedule {

  /**
     * Localiza um objeto ClassSchedule no banco de dados com base em seu ID.
     *
     * @param id O ID do objeto ClassSchedule a ser localizado.
     * @return O objeto ClassSchedule correspondente ao ID, ou null se nenhum objeto for encontrado.
     */
    @Override
    public ClassSchedule findById(String id) {
        return findOne(classSchedule -> classSchedule.getId().equals(id));
    }


   /**
     * Obtém os horários formatados de uma lista de IDs de ClassSchedule.
     *
     * @param ids A lista de IDs de ClassSchedule.
     * @return Uma lista de horários formatados correspondentes aos IDs de ClassSchedule.
     */

    @Override
    public List<String> getTimeFormated(List<String> ids) {
        return findMany(classSchedule -> {
            return ids.stream().anyMatch(id -> classSchedule.getId().equals(id));
        })
           .stream()
           .map(classSchedule -> "[Dia: %s | Hórario: %s]".formatted(Utils.formatDayOfWeak(classSchedule.getDayOfWeek()), Utils.formatTime(classSchedule.getTime())))
           .toList();
    }

  
    /**
     * Verifica se um determinado horário está em uso para uma determinada sala.
     *
     * @param dayOfWeek O dia da semana do horário.
     * @param time      O horário a ser verificado.
     * @param list      A lista de ClassSchedule existente.
     * @param roomId    O ID da sala a ser verificada.
     * @return true se o horário estiver em uso para a sala, caso contrário, false.
     */
    @Override
    public boolean checkTimeInUse(DayOfWeek dayOfWeek, LocalTime time, List<ClassSchedule> list, String roomId) {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        int hourPerClass = academicSystem.getSettings().getHourPerClass();

        return list
                .stream()
                .anyMatch(classSchedule -> {
                   LocalTime initalTime = classSchedule.getTime();

                   LocalTime timeFinal = LocalTime.of(
                           (hourPerClass + initalTime.getHour()) - 1,
                           59
                   );

                   boolean sameDay = classSchedule.getDayOfWeek().equals(dayOfWeek);
                   boolean sameTime = time.isAfter(initalTime) && time.isBefore(timeFinal) || initalTime.equals(time) || timeFinal.equals(time);
                   boolean sameRoom = classSchedule.getRoomId().equals(roomId);

                    System.out.println(sameDay);
                    System.out.println(sameTime);
                    System.out.println(sameRoom);

                   return sameDay && sameTime && sameRoom;
           }
        );

    }

}
