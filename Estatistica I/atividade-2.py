# Questão 2

def numeroImpar(numero):
    return numero % 2 != 0

def obterIntersecao(arr1, arr2):
    intersecao = []

    for i in arr1:
      for j in arr2:
          if i == j:
            intersecao.append(i)
    return intersecao

def obterEspaçoAmostralDado():
    matriz = []

    for i in range(1,7):
       for j in range(1,7):
          matriz.append([i,j])

    return matriz

espacoAmostral = obterEspaçoAmostralDado()
tamanhoEspacoAmostral = len(espacoAmostral)

somaIgual7 = list(filter(lambda x: x[0] + x[1] == 7, espacoAmostral))
numerosImpares = list(filter(lambda x: numeroImpar(x[0]) and numeroImpar(x[1]), espacoAmostral))
produtoIgual12 =  list(filter(lambda x: x[0] * x[1] == 12, espacoAmostral))
INTERSECAO_A_B = obterIntersecao(somaIgual7, numerosImpares)
INTERSECAO_A_C = obterIntersecao(somaIgual7, produtoIgual12)

P_A = len(somaIgual7) / tamanhoEspacoAmostral
P_B = len(numerosImpares) / tamanhoEspacoAmostral
P_C = len(produtoIgual12) / tamanhoEspacoAmostral
P_A_INTERSECAO_B = len(INTERSECAO_A_B) / tamanhoEspacoAmostral
P_A_INTERSECAO_C = len(INTERSECAO_A_C) / tamanhoEspacoAmostral

P_A_U_B = P_A + P_B - P_A_INTERSECAO_B
P_A_U_C = P_A + P_C - P_A_INTERSECAO_C

print(f"P(AUB) = {P_A_U_B}")
print(f"P(AUC) = {P_A_U_C}")