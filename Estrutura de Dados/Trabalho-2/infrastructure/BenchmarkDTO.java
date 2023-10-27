package infrastructure;

public record BenchmarkDTO(
        long time,
        String name,
        int sizeVectorInsertion,
        int heigth,
        int rotations,
        int sizeVectorSearch,
        int doubleRotations
) {}

