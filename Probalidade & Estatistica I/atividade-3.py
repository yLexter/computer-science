'''
   Feito junto com Lisa Ashley (https://github.com/lisacsiqueira)
'''

import math
from functools import reduce

class Utils:
    @staticmethod
    def fatorial(n):
        sum = 1

        for i in range(1, n + 1):
            sum *= i

        return sum

    @staticmethod
    def combinacao(n,p):
        return Utils.fatorial(n) / (Utils.fatorial(p) * Utils.fatorial(n - p))


class Distribuicao:

      @staticmethod
      def prBinomial (n, k, p):
          return Utils.combinacao(n,k) * math.pow(p,k) * math.pow(1 - p, n - k)

      @staticmethod
      def binomial(n, p, comecoIntervalo, fimIntervalo):
          listaPr = [Distribuicao.prBinomial(n,k,p) for k in range(comecoIntervalo, fimIntervalo)]

          return {
              "pR": {  f"Pr(X = {index})": pr for index, pr in enumerate(listaPr, start= comecoIntervalo)},
              "total": reduce(lambda acc, x: acc + x, listaPr)
          }

      @staticmethod
      def prPoison(x, Lambda):
          return (math.pow(math.e, (-1) * Lambda) * math.pow(Lambda, x)) / Utils.fatorial(x)

      @staticmethod
      def poison(comecoIntervalo, fimIntervalo, Lambda):
          listaPr = [Distribuicao.prPoison(x, Lambda) for x in range(comecoIntervalo, fimIntervalo)]

          return {
              "pR": {  f"Pr(X = {index})": pr for index, pr in enumerate(listaPr, start=comecoIntervalo)},
              "total": reduce(lambda acc, x: acc + x, listaPr)
          }

class Questao1:
      QUESTAO = 1
      p = 0.6
      n = 12
      k = 6

      def resultado(self):
          resultado = Distribuicao.binomial(self.n, self.p, self.k, self.n + 1)
          pR, soma = resultado.values()

          for string, pR in pR.items():
              print(f'{string} = {pR}')

          print()
          print(f"Soma: {soma}")

class Questao2:
      QUESTAO = 2
      LAMBDA = 12
      TOTAL_CARROS = 5

      def resultado(self):
          resultado = Distribuicao.poison(0, self.TOTAL_CARROS, self.LAMBDA)
          pR, soma = resultado.values()

          for string, pR in pR.items():
              print(f'{string} = {pR}')

          print()
          print(f"Soma: {soma}")


class Main:
      def __init__(self, questoes) -> None:
          self.questoes = questoes

      def mostrarResultado(self):
          for questao in self.questoes:
              print("-" * 49)
              print("-" * 18, f"Questao: {questao.QUESTAO} ", "-" * 18)
              print()
              questao.resultado()
              print()
              print("-" * 49)
              print()

main = Main([
    Questao1(),
    Questao2()
])

main.mostrarResultado()


'''
   Questão 3 e 4 feito por Lisa Ashley  
'''
from scipy.stats import hypergeom

# Parâmetros da distribuição hipergeométrica
M = 6  # Número total de elementos na população
N = 10  # Tamanho total da população
n = 5   # Tamanho da amostra
k = 3   # Número de sucessos desejados na amostra

# Calculando a probabilidade
probability = hypergeom.pmf(k, N, M, n)

print(f"Questao 03)\nA probabilidade de obter {k} sucessos na amostra é: {probability:.4f}")

from math import comb, sqrt

def binomial(n, k, p):
  probability = comb(n, k) * (p**k) * ((1 - p)**(n - k))
  return probability


def calculate_mean_and_stddev(n, p):
    mean = n * p
    stddev = sqrt(n * p * (1 - p))
    return mean, stddev


print("\nQuestão 04)")
print(f"a) P(X=1) = : {binomial(4, 1, 0.2):.4f}")
print(f"b) P(X=0) = : {binomial(4, 0, 0.2):.4f}")
print(f"c) P(X<=2) = : {((binomial(4, 1, 0.2)*2) + binomial(4, 2, 0.2)):.4f}")
mean, stddev = calculate_mean_and_stddev(4, 0.2)
print(f"d) Média: {mean:.4f} e Desvio Padrão: {stddev:.4f}")