"""Classes que simulam o ambiente do agente."""

# Parte 2

from buscagenerica import aestrela, largura, profundidade, percurso, Noh
from typing import List, Callable, Optional, NamedTuple
from enum import Enum
import random

# Celular é uma grade 2D de Celula(s) forma o labirinto
# Uma Celula é uma enumeração com valores str
class Celula(str, Enum):
    INICIAL = 'S'
    OBJETIVO = 'G'
    CAMINHO = '*'
    VAZIA = ' '
    BLOQUEADA = 'X'

# Referenciar uma posição individual no labirinto com NamedTuple
class Coordenadas(NamedTuple):
    linha: int
    coluna: int

# Classe Labirinto mantém internamente o controle de uma grade (lista de listas)
# que representa o seu estado
# disperso: o quão esparso é o labirinto (20% por default de posições bloqueadas)
class Labirinto:
    def __init__(self, linhas: int = 10, colunas: int = 20,
                 disperso: float = 0.20,
                 inicial: Coordenadas = Coordenadas(0, 0),
                 objetivo: Coordenadas = Coordenadas(9, 19)) -> None:

                 self._linhas: int = linhas
                 self._colunas: int = colunas
                 self.inicial: Coordenadas = inicial
                 self.objetivo: Coordenadas = objetivo

                 self._grade = [ [Celula.VAZIA for c in range(colunas)] for l in range(linhas)]
                 self.preenche_aleatoriamente(linhas, colunas, disperso)
                 self._grade[inicial.linha][inicial.coluna] = Celula.INICIAL
                 self._grade[objetivo.linha][objetivo.coluna] = Celula.OBJETIVO

    def preenche_aleatoriamente(self, linhas: int, colunas: int, disperso: float):
        for linha in range(linhas):
            for coluna in range(colunas):
                if random.uniform(0, 1.0) < disperso:
                    self._grade[linha][coluna] = Celula.BLOQUEADA

    # Com o labirinho pronto, vamos exibir sua estrutura
    def __str__(self) -> str:
        saida: str = ""
        for i in self._grade:
            saida += "".join([c.value for c in i]) + "\n"
        
        return saida

    # Vamos verificar se o agente atingiu o objetivo durante a busca
    def teste_objetivo(self, posicao: Coordenadas) -> bool:
        return posicao == self.objetivo
    

    # Método de Labirinto que verifica posições válidas de Coordenadas em um Labirinto
    # em busca de espaços vazios para ir a partir dessa posição
    def sucessores(self, posicao: Coordenadas) -> List[Coordenadas]:
        posicoes: List[Coordenadas] = []

        if posicao.linha + 1 < self._linhas and self._grade[posicao.linha + 1][posicao.coluna] != Celula.BLOQUEADA:
            posicoes.append(Coordenadas(posicao.linha + 1, posicao.coluna))
        if posicao.linha - 1 >= 0 and self._grade[posicao.linha - 1][posicao.coluna] != Celula.BLOQUEADA:
            posicoes.append(Coordenadas(posicao.linha - 1, posicao.coluna))
        if posicao.coluna + 1 < self._colunas and self._grade[posicao.linha][posicao.coluna + 1] != Celula.BLOQUEADA:
            posicoes.append(Coordenadas(posicao.linha, posicao.coluna + 1))
        if posicao.coluna - 1 >= self._colunas and self._grade[posicao.linha][posicao.coluna - 1] != Celula.BLOQUEADA:
            posicoes.append(Coordenadas(posicao.linha, posicao.coluna - 1))

        return posicoes

    # Fim da Parte 2. Vá para buscagenerica.py  (Parte 3)

    # Parte 4, retornando de buscagenerica.py
    # Marcar o labirinto com o caminho com sucesso, estado inicial e objetivo
    def demarcar(self, caminho: List[Coordenadas]):
        for pos_labirinto in caminho:
            self._grade[pos_labirinto.linha][pos_labirinto.coluna] = Celula.CAMINHO
        self._grade[self.inicial.linha][self.inicial.coluna] = Celula.INICIAL
        self._grade[self.objetivo.linha][self.objetivo.coluna] = Celula.OBJETIVO
    
    # Remover um caminho para testar algoritmos diferentes no mesmo labirinto
    def remover(self, caminho: List[Coordenadas]):
        for pos_labirinto in caminho:
            self._grade[pos_labirinto.linha][pos_labirinto.coluna] = Celula.VAZIA
        self._grade[self.inicial.linha][self.inicial.coluna] = Celula.INICIAL
        self._grade[self.objetivo.linha][self.objetivo.coluna] = Celula.OBJETIVO
    
# Fim da (Parte 4) ir para main e testar a busca em profundidade
# Após o teste, ir para a Parte 5 em labirinto.py

# Parte 7: criar as funções de distância

# Retorna uma função distancia() que captura o objetivo Coordenadas recebido por distancia_euclidiana()
# distancia() recebe a posição inicial no labirinto como argumento e "conhece" o objetivo

from math import sqrt

# Distância 1
def distancia_euclidiana(objetivo):
    def distancia(localizacao):
        x = localizacao.coluna - objetivo.coluna
        y = localizacao.linha - objetivo.linha
        return sqrt((x * x) + (y * y))
    return distancia

# Distância 2
def distancia_manhattan(objetivo):
    def distancia(localizacao):
        x = abs(localizacao.coluna - objetivo.coluna)
        y = abs(localizacao.linha - objetivo.linha)
        return (x + y)
    return distancia

# Fim da parte 7, ir para a parte 8 em buscagenerica.py

if __name__ == "__main__":
    # Teste profundidade
    testeLabirinto: Labirinto = Labirinto()
    print(testeLabirinto)

    buscaProfundidade: Optional[Noh[Coordenadas]] = profundidade(testeLabirinto.inicial, testeLabirinto.teste_objetivo,
                        testeLabirinto.sucessores)
    if buscaProfundidade is None:
        print('----------')
        print('Sem solução usando busca em profundidade!')
    else:
        print('----------')
        caminhoDFS: List[Coordenadas] = percurso(buscaProfundidade)
        testeLabirinto.demarcar(caminhoDFS)
        print(testeLabirinto)
        testeLabirinto.remover(caminhoDFS)
    
    # Teste largura
    solucaoLargura: Optional[Noh[Coordenadas]] = largura(testeLabirinto.inicial, testeLabirinto.teste_objetivo,
                        testeLabirinto.sucessores)
    if solucaoLargura is None:
        print('----------')
        print("Sem solução usando busca em largura!")
    else:
        print('----------')
        caminhoLargura: List[Coordenadas] = percurso(solucaoLargura)
        testeLabirinto.demarcar(caminhoLargura)
        print(testeLabirinto)
        testeLabirinto.remover(caminhoLargura)
    
    distancia = distancia_manhattan(testeLabirinto.objetivo)
    distanciaE = distancia_euclidiana(testeLabirinto.objetivo)
    
    # Teste A*
    solucaoA = aestrela(testeLabirinto.inicial, testeLabirinto.teste_objetivo, testeLabirinto.sucessores, distanciaE)

    if solucaoA is None:
        print('----------')
        print("Sem solução com A*!")
    else:
        print('----------')
        caminhoA = percurso(solucaoA)
        testeLabirinto.demarcar(caminhoA)
        print(testeLabirinto)

# Adaptado de:
# Problemas Clássicos de Ciência da Computação com Python
# Copyright 2018 David Kopec