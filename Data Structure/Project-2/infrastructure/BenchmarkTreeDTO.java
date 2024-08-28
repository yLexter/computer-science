package infrastructure;

public record BenchmarkTreeDTO(
        long time,
        String name,
        int sizeVectorInsertion,
        int heigth,
        int rotations,
        int sizeVectorSearch,
        int doubleRotations
) {
   
    @Override
    public String toString() {
        float time = (float) time();

        return String.format("Benchmark: time=%.5f | name= %s | sizeVectorInsertion= %d, heigth=%d | rotations=%d | doubleRotations=%d | sizeVectorSearch=%d",
                time,
                name(),
                sizeVectorInsertion(),
                heigth(),
                rotations(),
                doubleRotations(),
                sizeVectorSearch()
        );
    }
    
}

