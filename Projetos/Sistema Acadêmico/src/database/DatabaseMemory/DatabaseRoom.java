package database.DatabaseMemory;

import general.Room;
import interfaces.database.IDatabaseRoom;

/**
 * Esta é uma classe que representa um banco de dados de salas.
 * Ela estende a classe `DatabaseBase` e implementa a interface `IDatabaseRoom`.
 * 
 * Aqui são fornecidas operações de banco de dados específicas para salas.
 * A classe genérica `Room` é utilizada para representar salas.
 * 
 * Os métodos nesta classe permitem a busca de salas pelo ID.
 */
public class DatabaseRoom extends DatabaseBase<Room> implements IDatabaseRoom {

    /**
     * Encontra uma sala pelo seu ID.
     *
     * @param id O ID da sala a ser encontrada.
     * @return A sala encontrada, ou null se nenhuma sala correspondente for encontrada.
     */
    @Override
    public Room findById(String id) {
        return findOne(room -> room.getId().equals(id));
    }


}
