package AbstractData.HashTable.Benchmarks.BenchmarkDTO;

public class BenchmarkInsertDTO extends BenchmarkDTO {
   /**
    * A string inserida no benchmark de inserção.
    */
   private int vectorLength;

   /**
    * Constrói um BenchmarkInsertDTO com o nome da função de hash, o tempo do benchmark e a string inserida especificados.
    *
    * @param hashFunctionName O nome da função de hash.
    * @param time             O tempo do benchmark para a função de hash.
    * @param vectorLength   A string inserida na tabela hash
    */
   public BenchmarkInsertDTO(String hashFunctionName, double time, int vectorLength) {
      super(hashFunctionName, time);
      this.vectorLength = vectorLength;
   }

   /**
    * @return tamanho do vetor de inserção
    */

   public int getVectorLength() {
      return vectorLength;
   }

   /**
    * Retorna uma representação em string do BenchmarkInsertDTO, incluindo informações da classe pai e a string inserida.
    *
    * @return Uma string formatada representando as informações do benchmark de inserção.
    */
   @Override
   public String toString() {
      return String.format("%s Tamanho Vetor = %d }", super.toString(), vectorLength);
   }

}
