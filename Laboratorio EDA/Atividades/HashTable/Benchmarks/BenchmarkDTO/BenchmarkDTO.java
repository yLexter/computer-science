package AbstractData.HashTable.Benchmarks.BenchmarkDTO;

public abstract class BenchmarkDTO {
    protected String hashFunctionName;
    protected float time;
    public BenchmarkDTO(String hashFunctionName, float time) {
        this.hashFunctionName = hashFunctionName;
        this.time = time;
    }

    public String getHashFunctionName() {
        return hashFunctionName;
    }

    public float getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("Benchmark = { Tempo: %.5f |", time);
    }

}
