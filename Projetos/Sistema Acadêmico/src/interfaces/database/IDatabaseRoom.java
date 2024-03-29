package interfaces.database;
import general.Room;

/**
 * Esta interface representa um banco de dados para as salas (Room).
 * Ela estende a interface IDatabaseBase, fornecendo métodos específicos para as salas.
 *
 * @param <Room> O tipo de sala a ser manipulada.
 */
public interface IDatabaseRoom extends IDatabaseBase<Room>{}
