# Por enquanto, 50% de acerto

import re
import json
import copy
from itertools import product

def calcular_tabelas_possiveis(tabela, jogos_faltantes, resultados, tabela_atual=None, idx=0, tabelas_possiveis=[]):
    if tabela_atual is None:
        tabela_atual = {time: dados.copy() for time, dados in tabela.items()}

    if idx == len(jogos_faltantes):
        tabelas_possiveis.append({time: dados.copy() for time, dados in tabela_atual.items()})
        return

    jogo = jogos_faltantes[idx]
    time_casa, time_fora = jogo

    for resultado in resultados:
        pontos_casa, pontos_fora = 0, 0
        if resultado == 'V':
            pontos_casa, pontos_fora = 3, 0
            tabela_atual[time_casa][1] += 10
        elif resultado == 'E':
            pontos_casa, pontos_fora = 1, 1
        else:
            tabela_atual[time_fora][1] += 10

        tabela_atual[time_casa][0] += pontos_casa
        tabela_atual[time_fora][0] += pontos_fora
        tabela_atual[time_casa][2] += 1
        tabela_atual[time_fora][2] += 1

        calcular_tabelas_possiveis_recursivo(tabela, jogos_faltantes, resultados, tabela_atual, idx + 1, tabelas_possiveis)

        tabela_atual[time_casa][0] -= pontos_casa
        tabela_atual[time_fora][0] -= pontos_fora
        tabela_atual[time_casa][2] -= 1
        tabela_atual[time_fora][2] -= 1

        tabela_atual[time_fora][1] += 10

    return tabelas_possiveis

def buscar_time(tabela, buscar_maior=True):
    melhor_time = None
    melhor_pontuacao = float('-inf') if buscar_maior else float('inf')

    for time, dados in tabela.items():
        pontos = dados[0]
        if (buscar_maior and pontos > melhor_pontuacao) or (not buscar_maior and pontos < melhor_pontuacao):
            melhor_time = time
            melhor_pontuacao = pontos

    return melhor_time, melhor_pontuacao

def criar_tabela(jogos):
    tabela = {}
    jogos_por_time = {}

    for jogo in jogos:
        time1, time2, gols_time1, gols_time2 = jogo.split()

        if time1 not in jogos_por_time:
            jogos_por_time[time1] = []
        if time2 not in jogos_por_time:
            jogos_por_time[time2] = []

        jogos_por_time[time1].append((time2, int(gols_time1), int(gols_time2)))
        jogos_por_time[time2].append((time1, int(gols_time2), int(gols_time1)))

    for time, jogos_time in jogos_por_time.items():
        pontos = 0
        saldo_gols = 0
        total_jogos = len(jogos_time)

        for adversario, gols_marcados, gols_sofridos in jogos_time:
            saldo_gols += gols_marcados - gols_sofridos
            if gols_marcados > gols_sofridos:
                pontos += 3
            elif gols_marcados == gols_sofridos:
                pontos += 1

        tabela[time] = [pontos, saldo_gols, total_jogos, []]

    for time, jogos_time in jogos_por_time.items():
      adversarios_jogados = [adversario for adversario, _, _ in jogos_time]

      for adversario, _ in jogos_por_time.items():
          if adversario != time:
             total_jogos_adversario = adversarios_jogados.count(adversario)
             tabela[time][3].extend([adversario] * (2 - total_jogos_adversario))

    return tabela

def buscar_colocacao_time(tabelas, time_busca):
    melhor_posicao = float('inf')
    pior_posicao = float('-inf')

    for tabela in tabelas:
        lista = sorted(tabela.keys(), key=lambda x: (tabela[x][0], tabela[x][1]), reverse=True)
        posicao = 0

        if time_busca in lista:
           posicao = lista.index(time_busca) + 1

        if posicao < melhor_posicao:
            melhor_posicao = posicao
        if posicao > pior_posicao:
            pior_posicao = posicao

    return melhor_posicao, pior_posicao

    caso_numero = 1
    resultados = ['V', 'E', 'D']

while True:
    n = int(input())

    if n == 0:
        break

    jogos = []

    times = [input().strip().lower() for _ in range(n)]

    g = int(input())

    for _ in range(g):
        jogos.append(input())

    print(f"Grupo #{caso_numero}")
    caso_numero += 1

    tabela = criar_tabela(jogos)

    jogos_faltantes = []

    for time in tabela:
        for adv in tabela[time][3]:

            if time in tabela[adv][3]:
              tabela[adv][3].remove(time)

            tabela[time][3].remove(adv)
            jogos_faltantes.append((time, adv))

    todos_casos = calcular_tabelas_possiveis(tabela, jogos_faltantes, resultados)

    for time in tabela:
        melhor, pior = buscar_colocacao_time(todos_casos, time)
        print(time, f"{melhor}-{pior}")

import itertools
import copy

def calcular_tabelas_possiveis_recursivo(tabela, jogos_faltantes, resultados, tabela_atual=None, idx=0, tabelas_possiveis=[]):
    if tabela_atual is None:
        tabela_atual = {time: dados.copy() for time, dados in tabela.items()}

    if idx == len(jogos_faltantes):
        tabelas_possiveis.append({time: dados.copy() for time, dados in tabela_atual.items()})
        return

    jogo = jogos_faltantes[idx]
    time_casa, time_fora = jogo

    for resultado in resultados:
        pontos_casa, pontos_fora = 0, 0
        if resultado == 'V':
            pontos_casa, pontos_fora = 3, 0
            tabela_atual[time_casa][1] += 10
        elif resultado == 'E':
            pontos_casa, pontos_fora = 1, 1

        tabela_atual[time_casa][0] += pontos_casa
        tabela_atual[time_fora][0] += pontos_fora
        tabela_atual[time_casa][2] += 1
        tabela_atual[time_fora][2] += 1

        calcular_tabelas_possiveis_recursivo(tabela, jogos_faltantes, resultados, tabela_atual, idx + 1, tabelas_possiveis)

        tabela_atual[time_casa][0] -= pontos_casa
        tabela_atual[time_fora][0] -= pontos_fora
        tabela_atual[time_casa][2] -= 1
        tabela_atual[time_fora][2] -= 1

        tabela_atual[time_casa][1] += 10

    return tabelas_possiveis

tabela = {
  "Fin": [3, -2,  6,  ["Mol"]],
  "Mol": [0, -10, 7, ["Fin"]],
  "Moa": [0, -10, 7, []],
  "Moc": [0, -10, 7, []],
}

def buscar_time(tabela, buscar_maior=True):
    melhor_time = None
    melhor_pontuacao = float('-inf') if buscar_maior else float('inf')

    for time, dados in tabela.items():
        pontos = dados[0]
        if (buscar_maior and pontos > melhor_pontuacao) or (not buscar_maior and pontos < melhor_pontuacao):
            melhor_time = time
            melhor_pontuacao = pontos

    return melhor_time, melhor_pontuacao

jogos_faltantes = [("Mol", "Fin"), ("Fin", "Mol")]
resultados = ['V', 'E', 'D']

tabelas_possiveis = calcular_tabelas_possiveis_recursivo(tabela, jogos_faltantes, resultados)

for idx, resultado in enumerate(tabela):
    print(buscar_time(tabela, time))

for idx, t in enumerate(tabelas_possiveis):
    print(f"Tabela {idx + 1}:")
    for time, dados in t.items():
        print(f"  {time}: {dados}")

print()