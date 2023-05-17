# Aluno: Lucas Ferreira Maia - 1° Periodo computação

# 1° Questão

def somarLista(lista1, lista2):
    def checkNumerosValidos(lista):
        if not len(lista1) or not len(lista2):
          raise Exception('Informe números nas 2 listas')

        for element in lista:
           if not element.isdigit():
             raise Exception('Não é permitido digitos, nem números floats, apenas números inteiros de 1-9')

        lista = list(map(int, lista)) 

        for element in lista:
            if 0 > element or element > 9:
              raise Exception('Voce só pode informar números de 1-9')

        while lista[0] == 0 && len(lista) != 1:
          lista.pop(0)

        return lista

    lista1 = checkNumerosValidos(lista1)
    lista2 = checkNumerosValidos(lista2)

    maiorLista = lista1 if len(lista1) > len(lista2) else lista2
    menorLista = lista2 if maiorLista == lista1 else lista1
    listaSoma = []

    if len(maiorLista) != len(menorLista):
       menorLista = [0] * (len(maiorLista) - len(menorLista)) + menorLista

    for i in range(len(maiorLista) -1, -1, -1):
        listaSoma.append(maiorLista[i] + menorLista[i])

    listaSoma.reverse()

    for i in range(len(listaSoma) -1, -1 , -1):
        elemento = listaSoma[i]
        
        if elemento > 9 and i != 0:
           listaSoma[i] = elemento % 10
           listaSoma[i - 1] += 1
        
        if elemento > 9 and i == 0:
          listaSoma[i] = elemento % 10
          listaSoma.insert(0, elemento // 10)   
   
    print(f'1° número:\t{maiorLista}')
    print(f'2° número:\t{menorLista}')
    print(f'-' * 50)
    print(f'Somátoria:\t{listaSoma}')

    return listaSoma
 
try:
  lista1 = input('Digite os números da 1° lista separado por espaço ').strip().split() 
  lista2 = input('Digite os números da 2° lista separado por espaço ').strip().split()

  somarLista(lista1, lista2)

except Exception as error:
  print(f'Error: {error}')
except KeyboardInterrupt:
  print('Progama encerrado')


# 2 Questão
def determinanteNulo(matriz):

    def checkMatrizValida(matriz):
        for linha in matriz:
          if len(linha) != len(matriz[0]):
            raise Exception("A quantidade de valores informada não é compativel com dimensão da matriz") 
        return matriz

    def elementosIguais(linha):
        for i in linha:
          if i != 0:
            return False
        return True

    def checkarLinhasIguais(matriz):
        for linha in matriz:
          if elementosIguais(linha):
            return True           
        return False

    def checkarColunhasIguais(matriz):
        matrizColunas = []

        for index in range(len(matriz[0])):
          tempLista = []
          for linha in matriz:
                tempLista.append(linha[index])
          matrizColunas.append(tempLista)    
          tempLista = []  

        for matriz in matrizColunas:
            if elementosIguais(matriz):
              return True       
        return False                

    matriz = checkMatrizValida(matriz)

    if checkarLinhasIguais(matriz) or checkarColunhasIguais(matriz):
      return True
    return False

def criarMatriz(dimensao):
    matriz = []
    for index, i in enumerate(range(dimensao)):
        elementosLinha = list(map(float, input(f'Digite os elementos da {index + 1}° linha separado por espaços ').strip().split()))
        matriz.append(elementosLinha)
    return matriz

try:
  dimensaoMatriz = int(input('Digite a dimensão da matriz '))

  if dimensaoMatriz <= 0:
    raise Exception('A dimensão da matriz tem que ser um inteiro maior que 0') 

  matriz = criarMatriz(dimensaoMatriz)

  if determinanteNulo(matriz):
    print('O determinante da matriz É nulo')
  else:
    print('O determinante da matriz NÃO é nulo.')  

except ValueError:
  print('Você não informou um número valido')
except Exception as error:
  print(f'Error: {error}')
except KeyboardInterrupt:
  print('Progama encerrado')
