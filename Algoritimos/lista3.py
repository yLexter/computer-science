# Questão 1
def binarioToDecimal(num):
    numeroInteiro = int(num)
    sinalNumero = 1 if num.startswith('-') else 0
    modulo = abs(numeroInteiro)
    maxBits = 8
    maxNumero = math.pow(2, maxBits)

    if (maxNumero - 1) < numeroInteiro or (maxNumero * -1) >= numeroInteiro:
       return print(f'O valor é maior que {maxNumero - 1} ou menor que -{maxNumero - 1}')

    def binarioList(dividendo, lista = []):
        resto = dividendo % 2
        resultado = dividendo // 2

        if resultado <= 1:
            lista += [resto, resultado]
            lista.reverse()
            return lista

        lista.append(resto)
        return binarioList(resultado)

    listaBinario = binarioList(modulo)

    if len(listaBinario) != 8:
       listaBinario = [0] * (maxBits - len(listaBinario)) + listaBinario

    listaBinario[0] = sinalNumero
  
    return listaBinario

# Questão 2
def listaTelefonica():
    agenda = {}
    num = 0
  
    def menuAgenda():
        menus = [
            "Mostra Lista Telefônica",
            "Acrescentar Entrada (Nome, Número)",
            "Retirar Entrada (Nome)" ,
            "Mostra todos os nomes dos contatos",
            "Atualizar contato",
            "Procurar Número",
            "Sair do menu"
        ]

        for index, element in enumerate(menus):
            print(f'{index + 1}° {element}')

    def mostrarAgenda():
        if not len(agenda.keys()):
            return print('Não existe ninguém cadastrado na agenda')

        for index, key in enumerate(agenda) :
            print(f'{index + 1 }. {key} - {agenda[key]}')

    def acrescentarContato():
        nome = input('Digite o nome do contato ')

        if nome.capitalize() in agenda:
            return print('Este contato já existe.')

        numero = int(input('Digite o número do contato '))

        agenda[nome] = numero

        print('Contato adicionado com sucesso')

    def removerContato():
        if not len(agenda.keys()):
            return print('Não existe ninguém cadastrado na agenda')

        nome = input('Digite o nome do contato que deseja excluir ')

        if nome.capitalize() not in agenda:
            return print('O Contato fornecido não existe.')

        del agenda[nome]
        print('O Contato foi deletado com sucesso.')

    def todosContatos():
        if not len(agenda.keys()):
            return print('Não existe ninguém cadastrado na agenda')

        for indice, key in enumerate(agenda):
            print(f'{indice + 1}° {key} - {agenda[key]}')

    def atulizarContato():
        if not len(agenda.keys()):
            return print('Não existe ninguém cadastrado na agenda')

        nome = input('Digite o nome do contato que deseja atualizar ')

        if nome.capitalize() not in agenda:
            return print('O Contato fornecido não existe')

        novoNumero = int(input('Digite o novo número '))
        agenda[nome] = novoNumero

        print('Contato atualizado com sucesso.')

    def sairMenu():
        print('Saindo da agenda... ')

    def opcaoInvalida():
        print('A opção fornecida é invalida, opções válida => 1-6')

    def procurarNumero():
        numero =  int(input('Digite o número que deseja procurar '))

        for key in agenda:
            if agenda[key] == numero:
               return print(f'Nome referente => {key}')

        return print('Número não encontrado')

  
    while num != 7:
        menuAgenda()
      
        num = int(input('Digite a opção que deseja '))
      
        print('\n' * 5)

        opcoes = {
            1: mostrarAgenda,
            2: acrescentarContato,
            3: removerContato,
            4: todosContatos,
            5: atulizarContato,
            6: procurarNumero,
            7: sairMenu
        }

        opcoes.get(num, opcaoInvalida)()
        print('\n' * 3)

# Questão 3
def questionSomaNumero():
    n = int(input())
    m = int(input())
    s = int(input())

    maiorNumero = -1

    def somarNumero(num):
        numList = list(str(num))
        return reduce(lambda acc, x: acc + int(x), numList, 0)

    for i in range(m, n - 1, -1):
        if somarNumero(i) == s:
           maiorNumero = i
           break

    print(maiorNumero)