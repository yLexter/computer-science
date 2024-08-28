import random
import time
import copy
import pandas as pd
import matplotlib.pyplot as plt
import statistics


class OrdenacaoLinear:
  @staticmethod
  def bubbleSort(arr):
      n = len(arr)
      swapped = False

      for i in range(n-1):
          swapped = False

          for j in range(0, n-i-1):
              if arr[j] > arr[j + 1]:
                  swapped = True
                  arr[j], arr[j + 1] = arr[j + 1], arr[j]
          if not swapped:
              return

  @staticmethod
  def insertionSort(arr):
      n = len(arr)

      for i in range(1, n):
          key = arr[i]
          j = i-1

          while j >= 0 and key < arr[j]:
              arr[j+1] = arr[j]
              j -= 1
          arr[j+1] = key

  @staticmethod
  def selectionSort(arr):
      size = len(arr)

      for ind in range(size):
          min_index = ind

          for j in range(ind + 1, size):
              if arr[j] < arr[min_index]:
                  min_index = j
          (arr[ind], arr[min_index]) = (arr[min_index], arr[ind])

class Vetor:
    @staticmethod
    def gerarVetorOrdemCrescente(size):
      return list(range(size))

    @staticmethod
    def gerarVetorOrdemAleatoria(size):
        vetor = Vetor.gerarVetorOrdemCrescente(size)

        random.shuffle(vetor)

        return vetor

# 100, 1_000, 10_000
class AlgoritmoBenchmark:
    TOTAL_EXECUCOES = 5
    VETORES = [
        Vetor.gerarVetorOrdemAleatoria(100),
        Vetor.gerarVetorOrdemAleatoria(1_000),
        Vetor.gerarVetorOrdemAleatoria(5_000),
        Vetor.gerarVetorOrdemAleatoria(10_000),
    ]


    def obterBenchmarks(self):
       return [
          self.obterBenchmark(OrdenacaoLinear.bubbleSort, "Bubble Sort"),
          self.obterBenchmark(OrdenacaoLinear.insertionSort, "Insertion Sort"),
          self.obterBenchmark(OrdenacaoLinear.selectionSort, "Selection Sort")
       ]


    def obterBenchmark(self, algoritmo, nome):
        dados = []

        for vetor in self.VETORES:
            media = 0

            for i in range(self.TOTAL_EXECUCOES):
               copiaVetor = copy.copy(vetor)
               dadosBenchmark = self.benchmark(algoritmo, copiaVetor)
               media += dadosBenchmark["tempo"]

            dados.append({
                "tamanho": len(vetor),
                "tempo": media / self.TOTAL_EXECUCOES
            })

        return {
            "nome": nome,
            "dados": dados
        }


    def benchmark(self, algoritmo, vetor):
       inicio = time.time()
       algoritmo(vetor)
       final = time.time()
       duracao = final - inicio

       return {
          "tempo": duracao,
          "tamanho": len(vetor)
       }

    def mostrarGrafico(self):
        dados = self.obterBenchmarks()

        df = pd.DataFrame(dados)

        plt.figure(figsize=(10, 6))

        for index, dados in df.iterrows():
            nome = dados[0]
            tempos = dados['dados']
            tamanhos = [d['tamanho'] for d in tempos]
            tempos_execucao = [d['tempo'] for d in tempos]

            plt.plot(tamanhos, tempos_execucao, marker='o', label=nome)

        plt.title('Tempos de Execução dos Algoritmos de Ordenação Linear')
        plt.xlabel('Tamanho do Vetor')
        plt.ylabel('Tempo de Execução (segundos)')
        plt.legend()
        plt.show()


algoritmoBenchmark = AlgoritmoBenchmark()
algoritmoBenchmark.mostrarGrafico()