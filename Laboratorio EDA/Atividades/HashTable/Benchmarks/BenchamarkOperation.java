package AbstractData.HashTable.Benchmarks;

public enum BenchamarkOperation {
    /**
     * Representa a operação de inserção em um benchmark.
     */
    insert("Inserção"),

    /**
     * Representa a operação de busca em um benchmark.
     */
    search("Busca");

    /**
     * Descrição associada à operação de benchmark.
     */
    private String description;

    /**
     * Construtor que associa uma descrição à operação de benchmark.
     *
     * @param description A descrição da operação de benchmark.
     */
    BenchamarkOperation(String description) {
        this.description = description;
    }

    /**
     * Obtém a descrição associada à operação de benchmark.
     *
     * @return A descrição da operação de benchmark.
     */
    public String getDescription() {
        return description;
    }

}