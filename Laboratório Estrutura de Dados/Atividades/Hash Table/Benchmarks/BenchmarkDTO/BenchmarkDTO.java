package AbstractData.HashTable.Benchmarks.BenchmarkDTO;

public abstract class BenchmarkDTO {
    /**
     * O nome da função de hash associado ao benchmark.
     */
    protected String hashFunctionName;

    /**
     * O tempo do benchmark para a função de hash associada.
     */
    protected double time;

    /**
     * Constrói um BenchmarkDTO com o nome da função de hash e o tempo do benchmark especificados.
     *
     * @param hashFunctionName O nome da função de hash.
     * @param time             O tempo do benchmark para a função de hash.
     */
    public BenchmarkDTO(String hashFunctionName, double time) {
        this.hashFunctionName = hashFunctionName;
        this.time = time;
    }

    /**
     * Obtém o nome da função de hash associado ao benchmark.
     *
     * @return O nome da função de hash.
     */
    public String getHashFunctionName() {
        return hashFunctionName;
    }

    /**
     * Obtém o tempo do benchmark para a função de hash.
     *
     * @return O tempo do benchmark.
     */
    public double getTime() {
        return time;
    }

    /**
     * Retorna uma representação em string do BenchmarkDTO.
     *
     * @return Uma string formatada representando as informações do benchmark.
     */
    @Override
    public String toString() {
        return String.format("Benchmark = { Tempo: %.5f |", time);
    }
}
