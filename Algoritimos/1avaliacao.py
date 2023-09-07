# Lucas Ferreira Maia e Paulo Ricardo

def questao1():
    numTorcedores = int(input('Quantidade de torcedores '))
    total = 0
    str = 'Tabela de valores \n\n'
    tiposIngressos = {
      "popular": (numTorcedores * 0.10) * 10,
      "geral": (numTorcedores * 0.50) * 50,
      "arquibancada": (numTorcedores * 0.30) * 100,
      "cadeiras": (numTorcedores * 0.10) * 200
    }

    for tipo in tiposIngressos:
      valor = tiposIngressos[tipo]
      str += f'{tipo}: {valor}\n'
      total += valor

    print('-' * 20)
    print(str)
    print('-' * 20)
    print(f'Total: \t {total}')

def questao2():
    g = 1
    kg = 1000 * g
    t = kg * 1000

    gramas = 0
    kilos = 0
    toneladas = 0

    totalMedidas = 3

    for i in range(30):
        num = float(input('Digite o valor da medida '))
        valor = i % totalMedidas

        if valor == 0:
           toneladas += num

        if valor == 1:
           kilos += num

        if valor == 2:
           gramas += num

    if gramas >= 1000:
       kilos += gramas // 1000
       gramas = gramas % 1000

    if kilos >= 1000:
      toneladas += kilos // 1000
      kilos = kilos % 1000

    print(f'{toneladas}t {kilos}kg {gramas}g')

while True:
  num = int(input('Digite o número da questão '))
  question = questions.get(num)

  questions = {
    1: questao1,
    2: questao2
  }

  if not question:
    print('Número invalido')
    continue

  question()