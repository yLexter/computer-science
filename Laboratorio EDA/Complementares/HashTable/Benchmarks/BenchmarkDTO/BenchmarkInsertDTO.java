package AbstractData.HashTable.Benchmarks.BenchmarkDTO;

public class BenchmarkInsertDTO extends BenchmarkDTO {
   private int sizeVector;

   public BenchmarkInsertDTO(String hashFunctionName, long time, int sizeVector) {
      super(hashFunctionName, time);
      this.sizeVector = sizeVector;
   }

   @Override
   public String toString() {
      return "BenchmarkInsertDTO = { " +
              "Tempo=" + time +
              " | Tamanho do Vetor=" + sizeVector +
              " }";
   }
}
