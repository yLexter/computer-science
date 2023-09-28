import random
import time
import copy
import pandas as pd
import matplotlib.pyplot as plt
import statistics

class QuickSort:

    @staticmethod
    def partition(lst, start, end):
        pos = start

        for i in range(start, end):
            if lst[i] < lst[end]:
                lst[i],lst[pos] = lst[pos],lst[i]
                pos += 1

        lst[pos],lst[end] = lst[end],lst[pos]

        return pos

    @staticmethod
    def quickSort(lst, start, end):
          if start < end:
            pos = QuickSort.partition(lst, start, end)
            QuickSort.quickSort(lst, start, pos - 1)
            QuickSort.quickSort(lst, pos + 1, end)

class MergeSort:

    @staticmethod
    def merge(A, aux, esquerda, meio, direita):
      for k in range(esquerda, direita + 1):
          aux[k] = A[k]
      i = esquerda
      j = meio + 1

      for k in range(esquerda, direita + 1):
          if i > meio:
              A[k] = aux[j]
              j += 1
          elif j > direita:
              A[k] = aux[i]
              i += 1
          elif aux[j] < aux[i]:
              A[k] = aux[j]
              j += 1
          else:
              A[k] = aux[i]
              i += 1

    @staticmethod
    def mergesort(arr, aux, esquerda, direita):
         if direita <= esquerda:
            return

         meio = (esquerda + direita) // 2
         MergeSort.mergesort(arr, aux, esquerda, meio)
         MergeSort.mergesort(arr, aux, meio + 1, direita)
         MergeSort.merge(arr, aux, esquerda, meio, direita)


class AlgoritmoOrdenacao:

    @staticmethod
    def mergeSort(arr):
            aux = [0] * len(arr)
            MergeSort.mergesort(arr, aux, 0, len(arr) - 1)

    @staticmethod
    def quickSort(arr):
        QuickSort.quickSort(arr, 0, len(arr) - 1)

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
    def gerarVetorOrdemAleatoria(size):
        return [random.randint(0,size) for _ in range(size)]

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
          self.obterBenchmark(AlgoritmoOrdenacao.quickSort, "Quick Sort"),
          self.obterBenchmark(AlgoritmoOrdenacao.mergeSort, "Merge Sort"),
          self.obterBenchmark(AlgoritmoOrdenacao.bubbleSort, "Bubble Sort"),
          self.obterBenchmark(AlgoritmoOrdenacao.insertionSort, "Insertion Sort"),
          self.obterBenchmark(AlgoritmoOrdenacao.selectionSort, "Selection Sort")
       ]


    def obterBenchmark(self, algoritmo, nome):
        dados = []

        for vetor in self.VETORES:
            media = 0

            for i in range(self.TOTAL_EXECUCOES):
               copiaVetor = copy.copy(vetor)
               dadosBenchmark = self.benchmark(algoritmo, copiaVetor)
               media += dadosBenchmark["tempo"]

            print(f"Vetor de Tamanho ({len(vetor)}) -  Algoritimo {nome} - Ordenado com sucesso.")

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
          "tempo": round(duracao, 5),
          "tamanho": len(vetor)
       }

    def mostrarDados(self):
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

        for i, dados in df.iterrows():
            nome = dados["nome"]
            dados = dados["dados"]

            print(f"Algoritmo - {nome}")
            print(pd.DataFrame(dados))
            print()
            print()



algoritmoBenchmark = AlgoritmoBenchmark()
algoritmoBenchmark.mostrarDados()