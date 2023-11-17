package AbstractData.HashTable.Benchmarks.BenchmarkDTO;

public class BenchmarkSearchDTO extends BenchmarkDTO {
    /**
     * A string buscada no benchmark de busca.
     */
    private String stringSearched;

    /**
     * Constrói um BenchmarkSearchDTO com o nome da função de hash, o tempo do benchmark e a string buscada especificados.
     *
     * @param hashFunctionName O nome da função de hash.
     * @param time             O tempo do benchmark para a função de hash.
     * @param stringSearched   A string buscada na tabela hash.
     */
    public BenchmarkSearchDTO(String hashFunctionName, double time, String stringSearched) {
        super(hashFunctionName, time);
        this.stringSearched = stringSearched;
    }

    /**
     * Obtém a string buscada no benchmark de busca.
     *
     * @return A string buscada.
     */
    public String getStringSearched() {
        return stringSearched;
    }

    /**
     * Retorna uma representação em string do BenchmarkSearchDTO, incluindo informações da classe pai e a string buscada.
     *
     * @return Uma string formatada representando as informações do benchmark de busca.
     */
    @Override
    public String toString() {
        return String.format("%s String Buscada = %s }", super.toString(), stringSearched);
    }

}