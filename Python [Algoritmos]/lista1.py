import math

# Questão 1: Faça um programa que solicite dois números ao usuário e os exiba na tela.
def exercicio1():
    numero1 = float(input('Digite o primeiro número '))
    numero2 = float(input('Digite o segundo número '))

    print(f'Os números que você digitou foram {numero1} e {numero2}.')


# Questão 2: Faça um programa que solicite dois números ao usuário, some-os e exiba o resultado na tela.
def exercicio2():
    numero1 = float(input('Digite o primeiro número '))
    numero2 = float(input('Digite o segundo número '))
    somaNumeros = numero1 + numero2

    print(f'A soma dos números {numero1} e {numero2} é {somaNumeros}')


# Questão 3: Faça um programa que solicite um número em metros ao usuário, converta-o para centímetros e exiba o resultado na tela.
def exercicio3():
    numeroMetros = float(input('Digite o número que deseja converter para centímetros '))
    conversao = numeroMetros * 100

    print(f'{numeroMetros}m é igual a {conversao} centímetros')


# Questão 4: Faça um programa que solicite o raio de um círculo ao usuário, calcule a área e exiba o resultado na tela.
def exercicio4():
    raioCirculo = float(input('Digite o valor do raio do círculo '))
    areaCirculo = math.pi * (math.pow(raioCirculo, 2))

    print(f'A área do círculo é {areaCirculo}')


# Questão 5: Faça um programa que solicite as notas de um aluno em quatro disciplinas, calcule a média e exiba o resultado na tela.
def exercicio5():
    nota1 = float(input('Digite a primeira nota '))
    nota2 = float(input('Digite a segunda nota '))
    nota3 = float(input('Digite a terceira nota '))
    nota4 = float(input('Digite a quarta nota '))

    media = (nota1 + nota2 + nota3 + nota4) / 4

    print(f'A média do aluno é {media}')


# Questão 6: Faça um programa que solicite o valor do lado de um quadrado, calcule a área dobrada e exiba o resultado na tela.
def exercicio6():
    valorLadoQuadrado = float(input('Digite o valor do lado do quadrado '))
    areaDobrada = math.pow(valorLadoQuadrado, 2) * 2

    print(f'O valor da área do quadrado dobrada é {areaDobrada}')


# Questão 7: Faça um programa que solicite o ganho por hora e a quantidade de horas trabalhadas em um mês, calcule o salário total e exiba o resultado na tela.
def exercicio7():
    ganhoPorHora = float(input('Digite quanto você ganha por hora '))
    horasTrabalhadas = float(input('Digite quantas horas você trabalhou no mês '))
    salarioTotal = ganhoPorHora * horasTrabalhadas

    print(f'Você ganha {salarioTotal} por mês')


# Questão 8: Faça um programa que solicite um valor em Fahrenheit, converta-o para Celsius e exiba o resultado na tela.
def exercicio8():
    valorFarenheit = float(input('Digite o valor em °F para converter para °C '))
    temperaturaEmCelsius = (5 * (valorFarenheit - 32)) / 9

    print(f'{valorFarenheit}°F é igual a {temperaturaEmCelsius}°C.')


# Questão 9: Faça um programa que solicite um valor em Celsius, converta-o para Fahrenheit e exiba o resultado na tela.
def exercicio9():
    valorCelsius = float(input('Digite o valor em °C para converter para °F '))
    temperaturaEmFahrenheit = (9 * valorCelsius + 160) / 5

    print(f'{valorCelsius}°C é igual a {temperaturaEmFahrenheit}°F.')


# Questão 10: Faça um programa que solicite um valor em real, a cotação do dólar atualmente, faça a conversão e exiba o resultado na tela.
def exercicio10():
    valorConversao = float(input('Digite o valor para converter para real '))
    cotacaoDolar = float(input('Digite a cotação do dólar atualmente '))
    conversaoFinal = valorConversao * cotacaoDolar

    print(f'R$ {valorConversao} está valendo atualmente US${conversaoFinal}')

 # Questão 11: Faça um programa que solicite um valor a ser depositado, aplique um juros de 0,7 e exiba o valor com juros na tela.
def exercicio11():
    valorDepositado = float(input('Digite o valor a ser depositado '))
    valorComJuros = valorDepositado + (valorDepositado * 0.7)

    print(f'O valor com juros de {valorDepositado} é {valorComJuros}')


# Questão 12: Faça um programa que solicite o valor de uma compra e calcule o valor das prestações, dividindo o valor total em 5 parcelas.
def exercicio12():
    valorCompra = float(input('Digite o valor da compra '))
    valorPrestacao = valorCompra / 5

    print(f'As prestações do produto ficarão no valor de {valorPrestacao}')


# Questão 13: Faça um programa que solicite o valor de um produto e um percentual de acréscimo, calcule o valor final com o acréscimo e exiba o resultado na tela.
def exercicio13():
    precoProduto = float(input('Valor do produto '))
    percentual = int(input('Digite o percentual '))
    precoFinal = precoProduto + (precoProduto * (percentual / 100))
    
    print(f'O valor do produto com o acréscimo é {precoFinal}')


# Questão 14: Faça um programa que solicite a distância percorrida por um automóvel e o total de combustível gasto, calcule a média de consumo e exiba o resultado na tela.
def exercicio14():
    distanciaPercorrida = float(input('Digite a distância percorrida do automóvel '))
    combustivelGasto = float(input('Digite o total de combustível '))
    consumoMedio = distanciaPercorrida / combustivelGasto

    print(f'A média de combustível gasto pelo automóvel é {consumoMedio}')


# Questão 15: Faça um programa que solicite três valores, A, B e C, e calcule o valor final da expressão (A + B)² + (B + C)² e exiba o resultado na tela.
def exercicio15():
    valorA = float(input('Digite o valor A '))
    valorB = float(input('Digite o valor B '))
    valorC = float(input('Digite o valor C '))

    def quadradoSoma(x, y):
        return math.pow(x, 2) + (2 * x * y) + math.pow(y, 2)  

    r = quadradoSoma(valorA, valorB)
    s = quadradoSoma(valorB, valorC)
    d = (r + s) / 2 

    print(f'O valor final da expressão é {d}')


# Questão 16: Faça um programa que solicite o nome de um vendedor, seu salário fixo e o total de vendas realizadas. Calcule o salário final, considerando uma comissão de 15% sobre o total de vendas, e exiba os resultados na tela.
def exercicio16():
    nomeVendedor = input('Digite seu nome ')
    salarioFixo = float(input('Digite seu salário fixo '))
    totalVendas = float(input('Digite seu total de vendas '))
    comissao = totalVendas * 0.15
    salarioFimMes = salarioFixo + comissao

    print(f'Nome: {nomeVendedor}\nSalário Fixo: {salarioFixo}\nSalário Fim do mês: {salarioFimMes}')


# Questão 17: Faça um programa que solicite três valores: um inteiro (valorInteiro1), outro inteiro (valorInteiro2) e um valor real (valorReal). Calcule e exiba na tela:
# - O produto do dobro do primeiro com metade do segundo.
# - A soma do triplo do primeiro com o terceiro.
# - O terceiro elevado ao cubo.
def exercicio17():
    valorInteiro1 = int(input('Digite o primeiro valor inteiro '))
    valorInteiro2 = int(input('Digite o segundo valor inteiro '))
    valorReal = float(input('Digite o terceiro valor real '))

    conta1 = (valorInteiro1 * 2) * (valorInteiro2 / 2)
    conta2 = (valorInteiro1 * 3) + valorReal
    conta3 = math.pow(valorReal, 3)

    print(f'O produto do dobro do primeiro com metade do segundo = {conta1}')
    print(f'A soma do triplo do primeiro com o terceiro = {conta2}')
    print(f'O terceiro elevado ao cubo = {conta3}')


# Questão 18: Faça um programa que solicite a altura de uma pessoa e calcule o peso ideal, utilizando a fórmula: (72.7 * altura) - 58. Exiba o resultado na tela.
def exercicio18():
    altura = float(input('Digite o valor da sua altura '))
    resultado = (72.7 * altura) - 58

    print(f'O seu peso ideal seria {resultado}')


# Questão 19: Faça um programa que solicite dois valores, A e B, e realize a troca dos valores, ou seja, o valor de A passa a ser o valor de B e vice-versa. Exiba os resultados na tela.
def exercicio19():
    valorA = float(input('Digite o valor A '))
    valorB = float(input('Digite o valor B '))

    auxiliar = valorA
    valorA = valorB
    valorB = auxiliar 
    
    print(f'Valor A = {valorA} e valor B = {valorB}')


# Questão 20: Faça um programa que solicite o valor ganho por hora e a quantidade de horas trabalhadas no mês. Calcule e exiba o salário bruto, descontando os seguintes impostos:
# - INSS: 8% do salário bruto
# - Sindicato: 5% do salário bruto
# - Imposto de Renda: 11% do salário bruto
# Calcule e exiba também o salário líquido, ou seja, o salário bruto descontado dos impostos.
def exercicio20():
    ganhoPorHora = float(input('Digite quanto você ganha por hora '))
    horasTrabalhadas = float(input('Digite quantas horas você trabalhou no mês '))
    salarioBruto = ganhoPorHora * horasTrabalhadas

    inss = salarioBruto * 0.08
    sindicato = salarioBruto * 0.05
    impostoRenda = salarioBruto * 0.11

    salarioLiquido = salarioBruto - (inss + sindicato + impostoRenda)

    print(f'INSS: {inss}\nSindicato: {sindicato}\nImposto de Renda: {impostoRenda}')
    print(f'Salário Bruto: {salarioBruto}\nSalário Líquido: {salarioLiquido}')   