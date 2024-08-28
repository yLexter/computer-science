from __future__ import annotations

"""
Classes que resolvem buscas: A*, profundidade e largura.
"""

# Trabalhamos com type hints (ver PEP 484)

# (Parte 1: comece por aqui)

# Necessitamos do módulo 'typing_extensions' para acessar 'Protocol'
# Comando para instalar: 
# >> pip install typing_extensions 
# ou 
# >> pip3 install typing_extensions
# from typing import Protocol (em uma versão futura, conforme PEP 544, 
# não precisaremos instalar typing_extensions)

from typing_extensions import Protocol
from typing import TypeVar, Iterable, Optional

T = TypeVar('T')

# Busca linear para teste
def busca_linear(iterador: Iterable[T], chave: T) -> bool:
    for item in iterador:
        if item == chave:
            return True
    return False

from typing import Any, Sequence

C = TypeVar('C', bound='Comparable')

class Comparable(Protocol):
    def __eq__(self: C, other: Any) -> bool:
        ...

    def __lt__(self: C, other: C) -> bool:
        ...

    def __gt__(self: C, other: C) -> bool:
        return (not self < other) and self != other

    def __le__(self: C, other: C) -> bool:
        return self < other or self == other

    def __ge__(self: C, other: C) -> bool:
        return not self < other

# Implementação da busca binária para teste
def busca_binaria(sequencia: Sequence[C], chave: C) -> bool:
    inicio: int = 0
    fim: int = len(sequencia) - 1
    while inicio <= fim:
        meio: int = (inicio + fim) // 2
        if sequencia[meio] < chave:
            inicio = meio + 1
        elif sequencia[meio] > chave:
            fim = meio - 1
        else:
            return True
    return False

# Fim da (Parte 1)
# Ir para labirinto.py (Parte 2)

# Retornando de labirinto: Parte 3
from typing import Generic

class Pilha(Generic[T]):
    def __init__(self) -> None:
        self._container: List[T] = []

    @property
    def vazia(self) -> bool:
        return not self._container
    
    def push(self, item: T) -> None:
        return self._container.append(item)

    def pop(self) -> T:
        return self._container.pop()

    def __repr__(self) -> str:
        return repr(self._container)
    
# Classe Noh para manter o controle de como passar de um estado a outro
# ou de uma posição a outra à medida em que a busca é realizada
# Estados são do tipo "Coordenadas"

class Noh(Generic[T]):
    def __init__(self, estado: T, pai: Optional[Noh], custo: float = 0.0, heuristica: float = 0.0) -> None:
        self.estado: T = estado
        self.pai: Optional[Noh] = pai
        self.custo: float = custo
        self.heuristica: float = heuristica
    
    def __lt__(self, other: Noh) -> bool:
        return (self.custo + self.heuristica) < (other.custo + other.heuristica)


# Implementação da busca em profundidade (dfs)
def profundidade(inicial: T, teste_objetivo: Callable[[T], bool], sucessores: Callable[[T], List[T]]) -> Optional[Noh[T]]:
    # borda ou fronteira: posições ainda não visitadas
    fronteira: Pilha[Noh[T]] = Pilha()
    fronteira.push(Noh(inicial, None))
    
    # conjunto de posições já visitadas
    explorados: Set[T] = {inicial}

    # continua a busca enquanto houver posições para visitar
    while not fronteira.vazia:
        noh_atual: Noh[T] = fronteira.pop()
        estado_atual: T = noh_atual.estado

        # Objetivo encontrado? Critério de parada.
        if teste_objetivo(estado_atual):
            return noh_atual
        
        # Verifica a próxima posição possível a ser visitada
        for filho in sucessores(estado_atual):
            if filho in explorados: # se estiver no conjunto, ignore (já visitada)
                continue

            explorados.add(filho)
            fronteira.push(Noh(filho, noh_atual))
    
    return None # se não houver solução, chegará aqui


# Com a propriedade 'pai' temos o caminho inverso, de Noh aos antecessores
def percurso(noh_: Noh[T]) -> List[T]:
    caminho: List[T] = [noh_.estado]
    while noh_.pai is not None:
        noh_ = noh_.pai
        caminho.append(noh_.estado)
    caminho.reverse()

    return caminho

# Fim da (Parte 3), ir para labirinto.py (Parte 4)

# Parte 5, retornando de buscagenerica.py
from typing import Deque

class Fila(Generic[T]):
    def __init__(self) -> None:
        self._container: Deque[T] = Deque()

    @property
    def vazia(self) -> bool:
        return not self._container  # se container tiver vazio, retorne True
    
    def push(self, item: T) -> None:
        return self._container.append(item)

    def pop(self) -> T:
        return self._container.popleft() # 1 2 3 4 ...

    def __repr__(self) -> str:
        return repr(self._container)


# Implementação da busca em largura
def largura(inicial: T, teste_objetivo: Callable[[T], bool], sucessores: Callable[[T], List[T]]) -> Optional[Noh[T]]:
    # borda ou fronteira: posições ainda não visitadas
    fronteira: Fila[Noh[T]] = Fila()
    fronteira.push(Noh(inicial, None))

    # conjunto de posições já visitadas
    explorados: Set[T] = {inicial}

    # continua a busca enquanto houver posições para visitar
    while not fronteira.vazia:
        noh_atual: Noh[T] = fronteira.pop()
        estado_atual: T = noh_atual.estado

        # Objetivo encontrado? Critério de parada.
        if teste_objetivo(estado_atual):
            return noh_atual
        
        # Verifica a próxima posição possível a ser visitada
        for filho in sucessores(estado_atual):
            if filho in explorados:  # se estiver no conjunto, ignore (já visitada)
                continue

            explorados.add(filho)
            fronteira.push(Noh(filho, noh_atual))
    
    return None  # se não houver solução, chegará aqui

# Fim da (Parte 5) ir para main (em labirinto.py) e testar a busca em largura
# Após o teste, ir para a Parte 6

# Parte 6

from heapq import heappop, heappush

class FilaDePrioridade(Generic[T]):
    def __init__(self) -> None:
        self._container: List[T] = []

    @property
    def vazia(self) -> bool:
        return not self._container
    
    def push(self, item: T) -> None:
        heappush(self._container, item)

    def pop(self) -> T:
        return heappop(self._container)

    def __repr__(self) -> str:
        return repr(self._container)
    

# Fim da Parte 6, ir para a Parte 7 em labirinto.py


# Parte 8

# Alterações em relação a BFS:
# 1. Modificar a fronteira (de fila para fila de prioridades)
# 2. Utilizar um dicionário (alterar o conjunto explorado)
# heuristica: função que recebe como parâmetro a pontuação heurística para Node

from typing import Dict

def aestrela(inicial: T, teste_objetivo: Callable[[T], bool], sucessores: Callable[[T], List[T]], heuristica: Callable[[T, float]] ) -> Optional[Noh[T]]:
    # borda ou fronteira: posições ainda não visitadas
    fronteira: FilaDePrioridade[Noh[T]] = FilaDePrioridade()
    fronteira.push(Noh(inicial, None, 0.0, heuristica(inicial)))

    # conjunto de posições já visitadas
    explorados: Dict[T, float] = {inicial: 0.0}

    # continua a busca enquanto houver posições para visitar
    while not fronteira.vazia:
        noh_atual: Noh[T] = fronteira.pop()
        estado_atual: T = noh_atual.estado

        # Objetivo encontrado? Critério de parada.
        if teste_objetivo(estado_atual):
            return noh_atual
        
        # Verifica a próxima posição possível a ser visitada
        for filho in sucessores(estado_atual):
            novo_custo = noh_atual.custo + 1

            if filho not in explorados or explorados[filho] > novo_custo:
                explorados[filho] = novo_custo
                fronteira.push(Noh(filho, noh_atual, novo_custo, heuristica(filho)))

    return None  # se não houver solução, chegará aqui

# Fim da (Parte 8) ir para main (em labirinto.py) e testar a busca A*

if __name__ == "__main__": # (Parte 1)
    #print(busca_linear([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], -1))
    #print(busca_linear(['s', 'ss'], 's'))

    print(busca_binaria([1, 2, 3, 4, 5], 5))
    print(busca_binaria(['s', 'ss'], 's'))




# Adaptado de:
# Problemas Clássicos de Ciência da Computação com Python
# Copyright 2018 David Kopec