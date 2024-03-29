package interfaces.database;

import general.ClassSchedule;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

/**
 * Esta interface representa um banco de dados para a classe ClassSchedule.
 * Ela estende a interface IDatabaseBase, fornecendo métodos específicos para a classe ClassSchedule.
 */
public interface IDatabaseClassSchedule extends IDatabaseBase<ClassSchedule>{

  /**
     * Obtém uma lista de horários formatados com base nos IDs fornecidos.
     *
     * @param ids Lista de IDs dos horários.
     * @return Lista de horários formatados.
     */
    List<String> getTimeFormated(List<String> ids);

   /**
     * Verifica se um determinado horário está em uso em um dia da semana e hora específicos,
     * levando em consideração uma lista de horários e o ID da sala.
     *
     * @param dayOfWeek Dia da semana.
     * @param time      Hora.
     * @param list      Lista de horários.
     * @param roomId    ID da sala.
     * @return true se o horário está em uso, false caso contrário.
     */
    boolean checkTimeInUse(DayOfWeek dayOfWeek, LocalTime time, List<ClassSchedule> list, String roomId);
}
