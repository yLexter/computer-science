package AbstractData.HashTable.Benchmarks;

public enum BenchamarkOperation {
    insert("Inserção"),
    search("Busca");

    private String description;

    BenchamarkOperation(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
