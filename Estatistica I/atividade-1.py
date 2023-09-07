import statistics
from functools import reduce


print("#-----------------------------------------------")
print("# Questão 4")
print("#-----------------------------------------------")
print()

questoes = [
  [20, 22, 20, 18, 25, 23, 27, 24, 24, 28, 20],
  [20, 22, 20, 18, 25, 23, 27, 24, 24, 200, 20],
  [5, 4, 5, 7, 2, 1, 8, 4, 9, 5, 4, 1, 1, 4, 5, 1],
  [113, 105, 108, 107, 110, 105, 113, 109]
]

for index, AI in enumerate(questoes, start=1):
    stringModa = ', '.join(str(x) for x in statistics.multimode(AI))

    print(f"Letra A{index}")
    print(f"- Media: {statistics.mean(AI)}")
    print(f"- Mediana: {statistics.median(AI)}")
    print(f"- Moda: {stringModa}")
    print()

print("#-----------------------------------------------")
print("# Questão 1")
print("#-----------------------------------------------")
print()

def obterDados(frequencias):
    arr = []
    idades = range(17,28)

    for index, freq in enumerate(frequencias):
       arr += [idades[index]] * freq

    return arr

turmas = [
    obterDados([0,4,4,6,12,10,8,0,0,0,0]),
    obterDados([4,6,6,8,10,3,3,2,1,0,1])
]

for index, turma in enumerate(turmas, start=1):
    stringModa = ', '.join(str(x) for x in statistics.multimode(turma))

    print(f"Turma {index}")
    print(f"- Media: {statistics.mean(turma)}")
    print(f"- Mediana: {statistics.median(turma)}")
    print(f"- Moda: {stringModa}")
    print(f"- Variância: {statistics.variance(turma)}")
    print(f"- Desvio Padrão: {statistics.stdev(turma)}")
    print()