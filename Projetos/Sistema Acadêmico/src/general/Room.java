package general;

import java.util.UUID;

/**
 * Esta classe representa uma sala utilizada no sistema.
 * Cada sala possui um nome, capacidade e um identificador único.
 */
public class Room {

    private String name; 
    private int capacity; 
    private final String id; 

    /**
     * Construtor da classe Room.
     *
     * @param name     O nome da sala.
     * @param capacity A capacidade da sala.
     */
    public Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.id = UUID.randomUUID().toString(); // Gera um identificador único para a sala
    }

    /**
     * Obtém o nome da sala.
     *
     * @return O nome da sala.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da sala.
     *
     * @param name O nome da sala a ser definido.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém a capacidade da sala.
     *
     * @return A capacidade da sala.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Define a capacidade da sala.
     *
     * @param capacity A capacidade da sala a ser definida.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Obtém o identificador único da sala.
     *
     * @return O identificador único da sala.
     */
    public String getId() {
        return id;
    }

    /**
     * Retorna uma representação em formato de texto da sala.
     *
     * @return Uma representação em formato de texto da sala.
     */
    @Override
    public String toString() {
        return String.format("Sala: %s | Capacidade: %d", name, capacity);
    }

}

