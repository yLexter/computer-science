# Análise de Algoritmos de Ordenação

- Grupo: Mateus Vitor, Joana Dark e João Gabriel

Neste projeto, implementamos e analisamos os seguintes algoritmos de ordenação em Python:

1. **Bubble Sort**
   - 100.000
     - Valores Desordenados: Tempo de execução elevado em amostras maiores, uso significativo de memória.
     - Valores Ordenados Crescentemente: Melhor desempenho, mas ainda lento em amostras maiores.
     - Valores Ordenados Decrescentemente: Desempenho semelhante aos valores desordenados.
     - Valores Constantes: Desempenho razoável.
     - Valores Parcialmente Ordenados (Final Desordenado): Tempo de execução razoável.
     - Valores Parcialmente Ordenados (Início Desordenado): Tempo de execução razoável.

2. **Selection Sort**
   - 100.000
     - Valores Desordenados: Tempo de execução elevado em amostras maiores, uso moderado de memória.
     - Valores Ordenados Crescentemente: Desempenho semelhante aos valores desordenados.
     - Valores Ordenados Decrescentemente: Melhor desempenho, mas ainda lento em amostras maiores.
     - Valores Constantes: Desempenho razoável.
     - Valores Parcialmente Ordenados (Final Desordenado): Tempo de execução razoável.
     - Valores Parcialmente Ordenados (Início Desordenado): Tempo de execução razoável.

3. **Insertion Sort**
   - 100.000
     - Valores Desordenados: Tempo de execução razoável, uso moderado de memória.
     - Valores Ordenados Crescentemente: Melhor desempenho, rápido mesmo em amostras maiores.
     - Valores Ordenados Decrescentemente: Desempenho semelhante aos valores desordenados.
     - Valores Constantes: Desempenho razoável.
     - Valores Parcialmente Ordenados (Final Desordenado): Tempo de execução razoável.
     - Valores Parcialmente Ordenados (Início Desordenado): Tempo de execução razoável.

4. **Merge Sort**
   - 100.000
     - Valores Desordenados: Bom desempenho, uso moderado de memória.
     - Valores Ordenados Crescentemente: Tempo de execução razoável.
     - Valores Ordenados Decrescentemente: Tempo de execução razoável.
     - Valores Constantes: Bom desempenho.
     - Valores Parcialmente Ordenados (Final Desordenado): Bom desempenho.
     - Valores Parcialmente Ordenados (Início Desordenado): Bom desempenho.

5. **Quick Sort**
   - 100.000
     - Valores Desordenados: Bom desempenho em amostras maiores, uso moderado de memória.
     - Valores Ordenados Crescentemente: Tempo de execução razoável.
     - Valores Ordenados Decrescentemente: Tempo de execução razoável.
     - Valores Constantes: Bom desempenho.
     - Valores Parcialmente Ordenados (Final Desordenado): Bom desempenho.
     - Valores Parcialmente Ordenados (Início Desordenado): Bom desempenho.

6. **Counting Sort**
   - 100.000
     - Valores Desordenados: Excelente desempenho em amostras maiores, uso mínimo de memória.
     - Valores Ordenados Crescentemente: Excelente desempenho.
     - Valores Ordenados Decrescentemente: Excelente desempenho.
     - Valores Constantes: Excelente desempenho.
     - Valores Parcialmente Ordenados (Final Desordenado): Excelente desempenho.
     - Valores Parcialmente Ordenados (Início Desordenado): Excelente desempenho.

## Conclusão

Os resultados da análise indicam que o algoritmo Counting Sort é o mais eficiente em termos de tempo de execução e uso de memória na maioria dos cenários de entrada. No entanto, a escolha do algoritmo de ordenação depende das características específicas dos dados e dos requisitos de desempenho do seu aplicativo.

É importante notar que os resultados podem variar com base na implementação e no ambiente de execução, e é sempre aconselhável realizar testes específicos para determinar o algoritmo mais adequado para o seu caso de uso.
