package AbstractData.HashTable.Benchmarks.BenchmarkDTO;

public class BenchmarkSearchDTO extends BenchmarkDTO {
    private String stringSearched;

    public BenchmarkSearchDTO(String hashFunctionName, long time, String stringSearched) {
        super(hashFunctionName, time);
        this.stringSearched = stringSearched;
    }

    @Override
    public String toString() {
        return "BenchmarkSearchDTO = { " +
                "Tempo=" + time +
                " | String Buscada='" + stringSearched + '\'' +
                " }";
    }
}
