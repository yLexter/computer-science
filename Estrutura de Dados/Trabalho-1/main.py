import os.path
import random
import time
import copy
import matplotlib.pyplot as plt
import math
import numpy as np
import json
import shutil
from google.colab import drive

drive.mount('/content/drive')

'''
 Ordem => 100_000, 500_000, 1_500_000,  2_500_000, 3_000_000
'''

class BubbleSort:
    nome = "Bubble Sort"

    def run(self, arr):
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

class InsertionSort:
    nome = "Insertion Sort"

    def run(self, arr):
      n = len(arr)

      for i in range(1, n):
          key = arr[i]
          j = i-1

          while j >= 0 and key < arr[j]:
              arr[j+1] = arr[j]
              j -= 1
          arr[j+1] = key

class SelectionSort:
    nome = "Selection Sort"

    def run(self, arr):
      size = len(arr)

      for ind in range(size):
          min_index = ind

          for j in range(ind + 1, size):
              if arr[j] < arr[min_index]:
                  min_index = j
          (arr[ind], arr[min_index]) = (arr[min_index], arr[ind])

class MergeSort:
    nome = "Merge Sort"

    def merge(self, A, aux, esquerda, meio, direita):
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

    def run(self, arr):
       aux = [0] * len(arr)
       self.mergesort(arr, aux, 0, len(arr) - 1)

    def mergesort(self, arr, aux, esquerda, direita):
        if direita <= esquerda:
            return

        meio = (esquerda + direita) // 2
        self.mergesort(arr, aux, esquerda, meio)
        self.mergesort(arr, aux, meio + 1, direita)
        self.merge(arr, aux, esquerda, meio, direita)

class QuickSort:
      nome = "Quick Sort"

      def run(self, arr):
          if len(arr) <= 1:
              return arr

          stack = [(0, len(arr) - 1)]

          while stack:
              low, high = stack.pop()
              if low < high:
                  pivot_index = self.partition(arr, low, high)
                  stack.append((low, pivot_index - 1))
                  stack.append((pivot_index + 1, high))

      def partition(self, arr, low, high):
          pivot = arr[high]
          i = low - 1
          for j in range(low, high):
              if arr[j] <= pivot:
                  i += 1
                  arr[i], arr[j] = arr[j], arr[i]
          arr[i + 1], arr[high] = arr[high], arr[i + 1]
          return i + 1


class CoutingSort:
    nome = "Couting Sort"

    def run(self, arr):
        max_val = max(arr)
        count = [0] * (max_val + 1)

        for num in arr:
            count[num] += 1

        sorted_arr = []

        for i in range(max_val + 1):
            sorted_arr.extend([i] * count[i])

        return sorted_arr

class Vetor:

    @staticmethod
    def obterPorcentagem(size):
        PORCENTAGEM = 90
        return  math.ceil(size * (PORCENTAGEM / 100))

    @staticmethod
    def conversorLista(lista):
        return np.array(lista, dtype=np.int64)

    @staticmethod
    def gerarVetorOrdemCrescente(size):
        lista = Vetor.conversorLista(list(range(size)))
        n = len(lista) - 1
        k = 0

        while k < n:
            numeroAleatorio = random.randint(1, n - 2)
            lista[numeroAleatorio - 1] = lista[numeroAleatorio]
            k += int(size * 0.15)

        return Vetor.conversorLista(lista)

    @staticmethod
    def gerarVetoOrdemDecrescente(size):
        lista = Vetor.gerarVetorOrdemCrescente(size)
        lista = lista[::-1]

        return lista

    @staticmethod
    def gerarVetorOrdemConstante(size):
        lista = [0] * size
        return Vetor.conversorLista(lista)

    @staticmethod
    def gerarVetorCrescenteComFinalAleatorio(size):
        totalPorcentagem = Vetor.obterPorcentagem(size)
        lista1 = Vetor.gerarVetorOrdemCrescente(totalPorcentagem)
        lista2 = Vetor.gerarVetorOrdemAleatoria(size - totalPorcentagem, size)

        return np.concatenate((lista1, lista2))

    @staticmethod
    def gerarVetorCrescenteComComecoAleatorio(size):
        totalPorcentagem = Vetor.obterPorcentagem(size)
        lista1 = Vetor.gerarVetorOrdemAleatoria(size - totalPorcentagem, size)
        lista2 = Vetor.gerarVetorOrdemCrescente(totalPorcentagem)

        return np.concatenate((lista1, lista2))

    @staticmethod
    def gerarVetorOrdemAleatoria(size, intervalo = 0):
        intervalo = intervalo if intervalo != 0 else size
        lista = [random.randint(0, intervalo) for _ in range(size)]
        return Vetor.conversorLista(lista)

class AlgoritmoBenchmark:
    TAMANHO_VETORES = [100_000, 500_000, 1_500_000,  2_000_000, 3_000_000] # Botar os valores aqui 100_000, 500_000, 1_500_000,  2_000_000, 3_000_000
    ALGORITMOS = [
        BubbleSort(),
        InsertionSort(),
        SelectionSort(),
        MergeSort(),
        QuickSort(),
        CoutingSort()
    ]
    TIPOS_VETORES = [
        {
            "nome": "Ordem Crescente",
            "funcao": Vetor.gerarVetorOrdemCrescente
        },
        {
            "nome": "Ordem Decrescente",
            "funcao": Vetor.gerarVetoOrdemDecrescente
        },
        {
            "nome": "Ordem Aleatoria",
            "funcao": Vetor.gerarVetorOrdemAleatoria
        },
        {
            "nome": "Ordem Constante",
            "funcao": Vetor.gerarVetorOrdemConstante
        },
        {
            "nome": "Ordem Crescente com Começo Aleatorio",
            "funcao": Vetor.gerarVetorCrescenteComComecoAleatorio
        },
        {
            "nome": "Ordem Crescente com Final Aleatorio",
            "funcao": Vetor.gerarVetorCrescenteComFinalAleatorio
        }
    ]
    VETORES = None

    def setarVetores(self):
        vetores = {}

        for tipo in self.TIPOS_VETORES:
            vetores[tipo["nome"]] = [tipo["funcao"](tam) for tam in self.TAMANHO_VETORES]

        self.VETORES = vetores

    def obterDadosDeVetores(self):
        dadosFinal = []
        dicVetores = self.VETORES

        for tipo, vetores in dicVetores.items():

            dadosVetores = { "tipo": tipo, "dados": [] }

            for algoritmo in self.ALGORITMOS:

                dadosAlgoritmo = { "algoritmo": algoritmo.nome, "benchmarks": [] }

                for vetor in vetores:
                    copiaVetor = copy.copy(vetor)
                    benchmark = self.benchmark(algoritmo, copiaVetor)
                    dadosAlgoritmo["benchmarks"].append(benchmark)
                    print(f"Ordenção {tipo} - {algoritmo.nome} - Tamanho: {len(vetor)} finalizada com sucesso")


                dadosVetores["dados"].append(dadosAlgoritmo)


            dadosFinal.append(dadosVetores)

        return dadosFinal

    def benchmark(self, algoritmo, vetor):
        inicio = time.time()
        algoritmo.run(vetor)
        final = time.time()
        duracao = final - inicio

        return {
            "tempo": duracao,
            "tamanho": len(vetor)
        }

    def salvarGraficos(self, dadosVetores):
        dir = "graficos"

        for dadoVetor in dadosVetores:
            (_, tipo_ordenacao), (_, dadosAlgoritmos) = dadoVetor.items()

            plt.figure(figsize=(10, 5))

            for dadosAlgoritmo in dadosAlgoritmos:
                (__, nome), (__, benchmarks) = dadosAlgoritmo.items()

                tamanhos = [benchmark["tamanho"] for benchmark in benchmarks]
                tempos_execucao = [benchmark["tempo"] for benchmark in benchmarks]

                plt.plot(tamanhos, tempos_execucao, marker='o', label=nome)

            plt.title(f'Tempo de Execução Com Vetor de {tipo_ordenacao}')
            plt.xlabel('Tamanho do Vetor')
            plt.ylabel('Tempo (segundos)')
            plt.legend()

            if not os.path.exists(dir):
               os.mkdir(dir)

            path = os.path.join(dir, f"Gráfico-{tipo_ordenacao}.png")
            plt.savefig(path)

    def obterDadosTabela(self, dados):
        dadosAlgoritmos = {}

        for j, coluna in enumerate(dados):
            algoritmo = coluna["dados"][j]
            nomeAlgoritmo = algoritmo["algoritmo"]
            dadosAlgoritmos[nomeAlgoritmo] = { "benchmarks": [] }

            for i, linha in enumerate(dados):
                tipoVetor =  linha["tipo"]
                benchmarks = linha["dados"][j]["benchmarks"]

                dadosAlgoritmos[nomeAlgoritmo]["benchmarks"].append({
                    "tipo": tipoVetor,
                    "benchmark": benchmarks
                })

        return dadosAlgoritmos

    def salvarDados(self, dadosVetores):
        dadosTabela = self.obterDadosTabela(dadosVetores)
        dir = "dados"

        if not os.path.exists(dir):
           os.mkdir(dir)

        path_vetores = os.path.join(dir, 'vetores.npy')
        path_tabela = os.path.join(dir, 'tabela.npy')

        np.save(path_tabela, dadosTabela)
        np.save(path_vetores, self.VETORES)

        shutil.copytree('graficos', '/content/drive/My Drive/graficos')
        shutil.copytree('dados', '/content/drive/My Drive/dados')


    def run(self):
        self.setarVetores()

        dadosVetores = self.obterDadosDeVetores()

        self.salvarGraficos(dadosVetores)
        self.salvarDados(dadosVetores)

algoritmoBenchmark = AlgoritmoBenchmark()
algoritmoBenchmark.teste()