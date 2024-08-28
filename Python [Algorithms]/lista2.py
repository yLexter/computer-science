
import math

# Questão da reposição 2
def checkInputValido(medida):    
    listMedida = list(medida)
    listAngulos = []
    angulos = ['g', 'm', 's']


    if medida.isnumeric():
       raise Exception('Valor informado sem a medida necessária')

    for element in listMedida:
        if (element not in angulos) and not element.isdigit():
            raise Exception('Valor de medida inválido')

    for valor in listMedida:
        if valor in angulos:
            listAngulos.append(valor)
            
    for value in angulos:
        if listAngulos.count(value) > 1:
          raise Exception('Medida de angulo repetida')

    copyAngulo = medida

    for medidaAngulo in angulos:
       if medidaAngulo in copyAngulo:
         valor, resto = copyAngulo.split(medidaAngulo)

         if not valor.isnumeric():
           raise Exception('Valor não é um número, ou angulo não está na ordem => g,m,s')

         copyAngulo = resto   

def getDicionario(angulos):
    dic = {}
    medidas = ['g', 'm' , 's']

    for angulo in angulos:
        dic[angulo] = []

    for angulo in angulos:
        copyAngulo = angulo
        for medidaAngulo in medidas:
            if medidaAngulo in copyAngulo:   
                valor, resto = copyAngulo.split(medidaAngulo)
                dic[angulo].append(valor)
                copyAngulo = resto
            else:
              dic[angulo].append('0')   

    for key in dic:
        dic[key] = tuple(map(int,dic[key]))
    return dic

def somarAngulos(angulos):
    [graus,minutos,segundos] = [0,0,0]
    medidas = ['g', 'm' , 's']

    for angulo in angulos:
        copyAngulo = angulo

        for medidaAngulo in medidas:
            if medidaAngulo in copyAngulo:

                valor, resto = copyAngulo.split(medidaAngulo)   

                if medidaAngulo == medidas[0]:
                    graus += int(valor)
                if medidaAngulo == medidas[1]:
                    minutos += int(valor)
                if medidaAngulo == medidas[2]:
                    segundos += int(valor)

                copyAngulo = resto

    if segundos > 59:
       minutos += segundos // 60
       segundos = segundos % 60

    if minutos > 59:
       graus += minutos // 60
       minutos = minutos % 60
  
    return f'{graus} Graus - {minutos} Minutos - {segundos} Segundos'

def main():
    angulos = input('Digite os agulos ').lower().strip().split()

    for angulo in angulos:
        checkInputValido(angulo)

    dicTupla = getDicionario(angulos)
    somaAngulos = somarAngulos(angulos)

    print(dicTupla)
    print(somaAngulos)



# Questão 1
def fatorial(number): 
    if number <=1:
      return 1
    return number * fatorial(number - 1)


# Questão 2
k = 0
soma = 0
constante = (2 * math.sqrt(2)) / 9801

while soma < 1e-15:
    numerador = fatorial(4 * k) * (1103 + 26390 * k)
    denominador = (fatorial(k) ** 4) * (369 ** (4*k))

    soma += constante * (numerador / denominador)
    k+= 1

pi = 1 / soma
print(pi)

# Questão 3
maiorGrau = 0
maiorMinuto = 0
maiorSegundo = 0
count = 0

while count < 2:
    graus = int(input('Valor do graus '))
    minutos = int(input('Valor do minutos '))
    segundos = int(input('Valor do segundos '))
    print()

    if graus < 0 or minutos < 0 or segundos < 0:
      print('Medidas de angulos não podem ser negativas')
      continue 

    if count == 0:
       maiorGrau = graus
       maiorMinuto = minutos
       maiorSegundo = segundos

    count += 1

    grausIguais = graus == maiorGrau
    minutosIguais = minutos == maiorMinuto 

    if graus > maiorGrau or (grausIguais and minutos > maiorMinuto) or (grausIguais and minutosIguais and segundos > maiorSegundo):
        maiorGrau = graus
        maiorMinuto = minutos
        maiorSegundo = segundos

print(f'Maior Grau => {maiorGrau}, Maior Minuto => {maiorMinuto}, Maior Segundo => {maiorSegundo}')

if maiorSegundo > 59:
  maiorMinuto += maiorSegundo // 60
  maiorSegundo = maiorSegundo % 60

if maiorMinuto > 59:
  maiorGrau += maiorMinuto // 60
  maiorMinuto = maiorMinuto % 60

print(f'Conversão => {maiorGrau} Grau - {maiorMinuto} Minutos - {maiorSegundo} Segundos')

