package AbstractData.HashTable.Benchmarks.BenchmarkDTO;

public class BenchmarkInsertDTO extends BenchmarkDTO {
   private String stringInserted;

   public BenchmarkInsertDTO(String hashFunctionName, float time, String stringInserted) {
      super(hashFunctionName, time);
      this.stringInserted = stringInserted;
   }

   public String getStringInserted() {
      return stringInserted;
   }

   @Override
   public String toString() {
      return String.format("%s Tamanho Vetor = %s }", super.toString(), stringInserted);
   }
}
