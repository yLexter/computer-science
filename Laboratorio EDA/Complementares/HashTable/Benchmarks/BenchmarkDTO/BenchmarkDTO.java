package AbstractData.HashTable.Benchmarks.BenchmarkDTO;

public abstract class BenchmarkDTO {
    protected String hashFunctionName;
    protected long time;
    public BenchmarkDTO(String hashFunctionName, long time) {
        this.hashFunctionName = hashFunctionName;
        this.time = time;
    }

}
