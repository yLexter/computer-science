# Análise de Desempenho de Algoritmos de Ordenação

Nesta análise de desempenho, examinamos o tempo de execução e o número de comparações para dois algoritmos do Bubble Sort: "Classico" e "Otimizado". 

# Especificações do Computador 
- Processador Intel Core i5 2410M
- 8 GB de RAM

## Para um vetor de 1.000 elementos

### Vetores Desordenados

- Algoritmo: Classico
  - Tempo de Execução: 16ms
  - Total de Comparações: 999.000

- Algoritmo: Otimizado
  - Tempo de Execução: 12ms
  - Total de Comparações: 499.175

### Vetores Ordenados

- Algoritmo: Classico
  - Tempo de Execução: 3ms
  - Total de Comparações: 999.000

- Algoritmo: Otimizado
  - Tempo de Execução: 0ms
  - Total de Comparações: 999

## Para um vetor de 10.000 elementos

### Vetores Desordenados

- Algoritmo: Classico
  - Tempo de Execução: 385ms
  - Total de Comparações: 99.990.000

- Algoritmo: Otimizado
  - Tempo de Execução: 322ms
  - Total de Comparações: 49.992.515

### Vetores Ordenados

- Algoritmo: Classico
  - Tempo de Execução: 238ms
  - Total de Comparações: 99.990.000

- Algoritmo: Otimizado
  - Tempo de Execução: 1ms
  - Total de Comparações: 9.999

## Para um vetor com 100.000 elementos

### Vetores Desordenados

- Algoritmo: Classico
  - Tempo de Execução: 30.559ms
  - Total de Comparações: 1.409.965.408

- Algoritmo: Otimizado
  - Tempo de Execução: 22.839ms
  - Total de Comparações: 704.867.744

### Vetores Ordenados

- Algoritmo: Classico
  - Tempo de Execução: 18.017ms
  - Total de Comparações: 1.409.965.408

- Algoritmo: Otimizado
  - Tempo de Execução: 1ms
  - Total de Comparações: 99.999

## Complexidades

- **Otimizado**
  - Ordenado: O(n)
  - Não ordenado: O(≅ n²/2)

- **Tradicional**
  - Ordenado: O(n²)
  - Não ordenado: O(n²)
