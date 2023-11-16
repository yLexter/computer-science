package AbstractData.HashTable.Benchmarks.BenchmarkDTO;

public class BenchmarkSearchDTO extends BenchmarkDTO {
    private String stringSearched;

    public BenchmarkSearchDTO(String hashFunctionName, float time, String stringSearched) {
        super(hashFunctionName, time);
        this.stringSearched = stringSearched;
    }

    public String getStringSearched() {
        return stringSearched;
    }

    @Override
    public String toString() {
        return String.format("%s String Buscada =  %s }", super.toString(), stringSearched);
    }
}
