# Funções uteis e importações de blibiotecas

import random
import pandas as pd
from openpyxl import *
from termcolor import *
from functools import reduce
from datetime import date

def formatarNumero(valor):
    return f'R$: {float(valor):.2f}'.replace('.', ',')

def quebraLinha(quantidade=1):
    print('\n' * quantidade)


def marcador(tamanho=78):
    print('=' * tamanho)

def verProdutos(db, adicionais={}):
    db.notVazio()
    marcador()
    print(db.tabela)
    marcador()
    quebraLinha()


def verLogs(db, adicionais={}):
    dbLogs = adicionais['dbLogs']
    dbLogs.notVazio()
    marcador()
    print(dbLogs.tabela)
    marcador()
    quebraLinha()  

def checkarDecisao(decisao):
    if decisao.lower() in ["não", 'nao', 'nn', 'n']:
       return False 

    if decisao.lower() in ['sim', 'ss', 's']:
       return True 

    raise Exception('Decisão inválida, apenas permitida Sim/Não')

# Classes

class Client:
    def __init__(self, db):
        self.carrinho = []
        self.db = db

    def addInCarrinho(self, produto):
        self.carrinho.append(produto)

    def verCarrinho(self):
        self.checkCarrinhoVazio()

        marcador(50)
        cprint('Produtos do Carrinho'.center(50), 'blue')

        for index, produto in enumerate(self.carrinho):
            quebraLinha()
            cprint(f'{index + 1}.', 'white')
            cprint(f'{self.db.tbNome}: {produto[self.db.tbNome]}\n'
                   f'{self.db.tbId}: {produto[self.db.tbId]}\n'
                   f'{self.db.tbQuantidade}: {produto[self.db.tbQuantidade]}\n'
                   f'{self.db.tbPrecoCompras}: {formatarNumero(produto[self.db.tbPrecoCompras])}\n'
                   f'{self.db.tbTotal}: {formatarNumero(produto[self.db.tbTotal])}'
                   )

        marcador(50)
        cprint(f'Preço Total:\t\t\t{formatarNumero(self.getPreçoTotal())}', 'blue')
        quebraLinha()

    def removerFromCarrinho(self, id):
        achouProduto = False

        self.checkCarrinhoVazio()

        for produto in self.carrinho:
            if produto[self.db.tbId] == id:
                achouProduto = produto
                break

        if not achouProduto:
            raise Exception('O ID do produto fornecido é inválido')

        self.carrinho.remove(achouProduto)

    def limparCarrinho(self):
        self.carrinho = []

    def getPreçoTotal(self):
        return reduce(lambda acc, produto: acc + produto[self.db.tbTotal], self.carrinho, 0)

    def checkCarrinhoVazio(self):
        if not len(self.carrinho):
            raise Exception('O Carrinho está vazio')


class Database:
    def __init__(self, nomeArquivo):
        self.nomeArquivo = f'{nomeArquivo}.xlsx'
        self.tabela = pd.read_excel(self.nomeArquivo, engine="openpyxl", index_col=[0])
        self.tbId = 'ID'
        self.prefixoId = '1'
        self.head = list(self.tabela.head())
        self.tamanhoId = 4

    def editarElemento(self, id, propiedade, novoValor):
        todosID = self.getTodosID()

        self.checkID(id)

        if propiedade not in self.head:
            raise Exception("A Propiedade fornecida é inválido.")

        linha = todosID.index(id)

        self.tabela.loc[linha, propiedade] = novoValor
        self.saveArquivo()

    def removerElemento(self, id):
        todosID = self.getTodosID()

        self.checkID(id)

        linha = todosID.index(id)

        self.tabela.drop([linha], inplace=True)
        self.tabela.reset_index(drop=True, inplace=True)
        self.saveArquivo()

    def criarNovoElemento(self, novoElemento):
        self.tabela = self.tabela.append(novoElemento, ignore_index=True)
        self.saveArquivo()

    def gerarID(self):
        id = self.prefixoId

        for i in range(self.tamanhoId):
            id += str(random.randint(0, 9))

        if int(id) not in self.getTodosID():
           return int(id)

        return self.gerarID()

    def getTodosID(self):
        return list(self.tabela[self.tbId])

    def saveArquivo(self):
        self.tabela.to_excel(self.nomeArquivo)

    def checkID(self, id):
        todosID = self.getTodosID()

        if id not in todosID:
            raise Exception("O ID fornecido é inválido.")

    def getColuna(self, propiedade):
        if propiedade not in self.head:
            raise Exception('Propiedade inválida')

        return list(self.tabela[propiedade])

    def getValor(self, id, prop):
        self.checkID(id)
        indexId = self.getTodosID().index(id)
        dados = self.getColuna(prop)

        return dados[indexId]

    def notVazio(self):
        if self.tabela.empty:
            raise Exception('Banco de Dados está vázio')


class DatabaseEstoque(Database):
    def __init__(self, nomeArquivo):
        super().__init__(nomeArquivo)
        self.tbQuantidade = 'Quantidade (Kg)'
        self.tbNome = 'Produto'
        self.tbPrecoCompras = 'Preço Kg'
        self.tbPrecoVendas = 'Preço Estoque (Kg)'
        self.tbTotal = 'Total' 
        self.minimoProduto = 15
        self.quantidadeRepor = self.minimoProduto * 2

    def subtrairQuantidade(self, idProduto, quantidade):
        self.checkID(idProduto)

        novoValor = self.getValor(idProduto, self.tbQuantidade) - quantidade

        if novoValor < 0:
            raise Exception('O valor informado é maior que ao que tem no estoque')

        self.editarElemento(idProduto, self.tbQuantidade, novoValor)

    def adicionarQuantidade(self, idProduto, quantidade):
        self.checkID(idProduto)

        if quantidade < 0:
            raise Exception('O valor fornecido é inválido')

        novoValor = self.getValor(idProduto, self.tbQuantidade) + quantidade
        self.editarElemento(idProduto, self.tbQuantidade, novoValor)

    def Produto(self, nome, id, quantidade, precoCompras, precoVendas=0, total=False):
        novoProduto = {
            self.tbNome: nome,
            self.tbId: id,
            self.tbQuantidade: quantidade,
            self.tbPrecoCompras: precoCompras,
            self.tbPrecoVendas: precoVendas,
        }
        if total:
            novoProduto[self.tbTotal] = precoCompras * quantidade
        return novoProduto

    def reporEstoque(self, dbLogs):
        colunaId = self.getTodosID()
        listaLogs = []

        def reporAndSalvarLogs(id):
            quantidade = self.getValor(id, self.tbQuantidade)

            if quantidade <= self.minimoProduto:
                nome = self.getValor(id, self.tbNome)
                precoCompraEstoque = self.getValor(id, self.tbPrecoVendas)
                quantidadeTotal = quantidade + self.quantidadeRepor

                log = dbLogs.novoLogEstoque(
                    nome=nome,
                    precoCompraEstoque=precoCompraEstoque,
                    quantidade=self.quantidadeRepor,
                    tipo = dbLogs.logSistema
                )

                listaLogs.append(log)

                return quantidadeTotal

            return quantidade

        self.tabela[self.tbQuantidade] = list(map(reporAndSalvarLogs, colunaId))
        self.saveArquivo()

        dbLogs.criarNovoElemento(listaLogs)

    def dfInfoDono(self):
        return pd.DataFrame({
          self.tbNome: self.getColuna(self.tbNome),
          self.tbId: self.getTodosID(),
          self.tbQuantidade: self.getColuna(self.tbQuantidade),
          self.tbPrecoVendas: self.getColuna(self.tbPrecoVendas),
        })

    def dfInfoCliente(self):
        colunaQuantidade = self.getColuna(self.tbQuantidade)
        return pd.DataFrame({
            self.tbNome: self.getColuna(self.tbNome),
            self.tbId: self.getTodosID(),
            self.tbPrecoCompras: self.getColuna(self.tbPrecoCompras),
            "Disponivel (Kg)": list(map(lambda x: 0 if x <= self.minimoProduto else x - self.minimoProduto, colunaQuantidade)),
        })     

class DatabaseLogs(Database):
    def __init__(self, nomeArquivo):
        super().__init__(nomeArquivo)
        self.tbNome = 'Nome'
        self.tbPrecoCompra = 'Pr Cliente'
        self.tbPrecoCompraEstoque = 'Pr Estoque'
        self.tbMes = 'Mês'
        self.tbquantidade = 'Quantidade'
        self.prefixoId = '2' 
        self.tipo = 'Tipo'
        self.total = 'Total'
        self.logSistema = 'Sistema'
        self.logCliente = 'Cliente'
        self.logAdmin = 'Admin'

    def novoLogEstoque(self, nome, tipo, quantidade, precoCompraEstoque):
        dataAtual = date.today()
        return {
            self.tbNome: nome,
            self.tbPrecoCompraEstoque: precoCompraEstoque,
            self.tbPrecoCompra: 0,
            self.tbId: self.gerarID(),
            self.tbMes: int(dataAtual.month),
            self.tbquantidade: quantidade,
            self.tipo: tipo,
            self.total: float(precoCompraEstoque * quantidade)
        }

    def novoLogCliente(self, nome, quantidade, precoCompra):
        dataAtual = date.today()
        return {
            self.tbNome: nome,
            self.tbPrecoCompra: precoCompra,
            self.tbPrecoCompraEstoque: 0,
            self.tbId: self.gerarID(),
            self.tbMes: int(dataAtual.month),
            self.tbquantidade: quantidade,
            self.tipo: self.logCliente,
            self.total: float(precoCompra * quantidade)
        }

# Funções para manusear o estoque como adicionar deletar editar ....

def criarProduto(db, adicionais={}):
    verProdutos(db)

    nome = input("Digie o nome do produto ")

    if nome.isnumeric() or nome == '':
        raise Exception('O Nome do produto deve ser uma palavra')

    quantidade = int(input("Digite a quantidade dos produtos "))

    if quantidade < 0:
        raise Exception("A Quantidade não pode ser menor que 0")

    precoCompras = float(input('Digite o valor do preço de venda pro cliente '))

    if precoCompras < 0:
        raise Exception("O Preço não pode ser menor que 0")

    precoVendas = float(input('Digite o valor do preço de compra para o estoque '))

    if precoVendas < 0:
        raise Exception("O Preço não pode ser menor que 0")

    if precoCompras < precoVendas:
        raise Exception('O Valor de compra de estoque não pode ser menor do que o de venda pro cliente')

    novoProduto = db.Produto(
        id=db.gerarID(),
        nome=nome,
        quantidade=quantidade,
        precoCompras=precoCompras,
        precoVendas=precoVendas,
    )

    db.criarNovoElemento(novoProduto)

    cprint('O produto foi criado com sucesso.', 'green')


def removerProduto(db, adicionais={}):
    verProdutos(db)

    idProduto = int(input("Digite o ID do produto que deseja remover "))

    db.removerElemento(idProduto)

    cprint('O produto foi deletado com sucesso.', 'green')


def editarProduto(db, adicionais={}):
    verProdutos(db)

    id = int(input('Digite o ID do Produto '))

    db.checkID(id)

    for index, name in enumerate(db.head):
        print(f'{index + 1} - {name}')

    numeroProp = int(input('Digite o número referente a propiedade que deseja atualizar '))

    if 0 >= numeroProp or numeroProp > len(db.head):
        raise Exception("O número fornecido é inválido.")

    propiedade = db.head[numeroProp - 1]

    if propiedade == db.tbId:
        novoId = db.gerarID()
        db.editarElemento(id, propiedade, novoId)
        return cprint('O ID foi atualizado com sucesso', 'green')

    novoValor = input('Digite o novo valor da propiedade ')

    if novoValor == '':
        raise Exception('O Novo valor é nulo, digite um valor válido.')

    if propiedade not in [db.tbNome]:
        novoValor = float(novoValor)

    db.editarElemento(id, propiedade, novoValor)

    cprint('O Valor foi atualizado com sucesso.', 'green')


def comprarProdutos(db, adicionais={}):
    dbLogs = adicionais['dbLogs']
    infos = db.dfInfoDono() 

    marcador()
    print(infos)
    marcador()

    idProduto = int(input('Digite o ID do produto que deseja comprar '))

    db.checkID(idProduto)

    valor = float(input('Digite a quantidade que deseja comprar '))

    if valor <= 0:
        raise Exception('Você não pode comprar valores menores que 1')

    nomeProduto = db.getValor(idProduto, db.tbNome)
    preco = db.getValor(idProduto, db.tbPrecoVendas)     

    decisao = input(f'Deseja comprar {valor} Kg {nomeProduto} por {formatarNumero(preco * valor)} ? (S/N) ').strip() 

    if not checkarDecisao(decisao):
       return cprint('Compra cancelada com sucesso.', 'red')

    log = dbLogs.novoLogEstoque(
        nome=db.getValor(idProduto, db.tbNome),
        tipo = dbLogs.logAdmin,
        quantidade=valor,
        precoCompraEstoque=db.getValor(idProduto, db.tbPrecoVendas)
    )

    dbLogs.criarNovoElemento(log)
    db.adicionarQuantidade(idProduto, valor)

    return cprint('O Produto foi comprado com sucesso.', 'green')

# Funções compra do cliente e etc
# -------------------------------------------------------------------------------------------------------
def operacoesFinalCompra(db, dbLogs, cliente):
    logsCliente = []

    for produto in cliente.carrinho:
        id = produto[db.tbId]
        quantidade = produto[db.tbQuantidade]

        log = dbLogs.novoLogCliente(
            nome=produto[db.tbNome],
            quantidade=quantidade,
            precoCompra=produto[db.tbPrecoCompras]
        )
        logsCliente.append(log)
        db.subtrairQuantidade(id, quantidade)

    dbLogs.criarNovoElemento(logsCliente)
    db.reporEstoque(dbLogs)
    cliente.limparCarrinho()

def limparCarrinho(db, adicionais ={}):
    cliente = adicionais["cliente"]
    cliente.limparCarrinho()

    cprint('Carrinho limpo com sucesso', 'green')
    quebraLinha()

def verCarrinho(db, adicionais={}):
    cliente = adicionais["cliente"]
    cliente.verCarrinho()

def addInCarrinho(db, adicionais={}):
    infos = db.dfInfoCliente()
    cliente = adicionais["cliente"]

    marcador()
    print(infos)
    marcador()

    idProduto = input("Digite o ID do Produto ou S para sair ")

    if idProduto.lower() == 's':
        return cprint('Retornando ao menu de compras', 'green')

    idProduto = int(idProduto)    

    db.checkID(idProduto)

    quantidadeCliente = float(input('Digite a quantidade que deseja compras (Em Kg) '))
    quantidadeProduto = db.getValor(idProduto, db.tbQuantidade)

    if quantidadeCliente <= 0:
        raise Exception('A Quantidade não pode ser menor ou igual que zero.')

    if quantidadeCliente > quantidadeProduto - db.minimoProduto:
        raise Exception(f'Você não pode comprar uma quantidade superior ao do estoque ')

    for produto in cliente.carrinho:
        if produto[db.tbId] == int(idProduto):
          
            if produto[db.tbQuantidade] + quantidadeCliente >= quantidadeProduto - db.minimoProduto:
               raise Exception('Você não pode colocar um valor superior ao que tem no estoque ')

            produto[db.tbQuantidade] += quantidadeCliente

            return cprint(f'Foram adicionado {quantidadeCliente:.2f} Kg de {produto[db.tbNome]} ao Carrinho', 'green')

    novoProduto = db.Produto(
        id= idProduto,
        nome=db.getValor(idProduto, db.tbNome),
        quantidade=float(quantidadeCliente),
        precoCompras=float(db.getValor(idProduto, db.tbPrecoCompras)),
        total=True
    )

    cliente.addInCarrinho(novoProduto)

    cprint('O Produto foi adicionado ao carrinho com sucesso', 'green')
    quebraLinha()


def removerDoCarirnho(db, adicionais={}):
    cliente = adicionais["cliente"]
    cliente.verCarrinho()

    idProduto = int(input('Digite o ID do produto que deseja remover '))

    cliente.removerFromCarrinho(idProduto)
    cprint('O Produto foi removido do carrinho com sucesso', 'green')
    quebraLinha()


def efetuarCompra(db, adicionais={}):
    cliente = adicionais["cliente"]
    dbLogs = adicionais['dbLogs']

    preçoTotal = cliente.getPreçoTotal()

    cliente.verCarrinho()

    dinheiroCliente = float(input('Digite o dinheiro do cliente '))

    if dinheiroCliente < preçoTotal:
        raise Exception('O dinheiro do cliente não pode ser menor que o preço total da compra')

    decisao = input('Deseja confirmar a compra? (S/N) ').strip()

    if not checkarDecisao(decisao):
       return cprint('Compra Cancelada com sucesso', 'red')

    quebraLinha()
    cprint(f'Compra realizada com sucesso', 'green')
    cprint(f'Troco:\t\t  {formatarNumero(dinheiroCliente - preçoTotal)}', 'green')
    quebraLinha()

    operacoesFinalCompra(db, dbLogs, cliente)

# funções para criar menu no geral
# -------------------------------------------------------------------------------------------------------

def createMenu(db, functions, opcoes, adicionais={}, title='Menu de Opções'):
    opcoes.append("Sair do menu")

    while True:
        quebraLinha()
        cprint(title.center(25), 'blue')
        quebraLinha()

        for index, element in enumerate(opcoes):
            cprint(f'{index + 1} - {element}', 'white')

        try:
            opcao = int(input("Digite a opção que deseja "))
        except ValueError:
            cprint(f'ERROR: Você precisa informar números de 1 a {len(opcoes)}', 'red')
            continue
        except KeyboardInterrupt:
            quebraLinha()
            cprint('ERROR: O Usuário encerrou o progama.', 'red')
            break
        except:
            cprint('ERROR: Um erro inesperado aconteceu, por favor tente novamente.', 'red')
            break

        function = functions.get(opcao)

        if opcao == len(opcoes):
            quebraLinha()
            break

        if function is None:
            cprint("ERROR: Digite uma opção válida.", 'red')
        else:
            try:
                quebraLinha()
                function(db, adicionais)
                quebraLinha()
            except ValueError:
                cprint('ERROR: Você precisa informar um número válido.', 'red')
                quebraLinha()
            except Exception as error:
                cprint(f'ERROR: {error}', 'red')
                quebraLinha()
            except KeyboardInterrupt:
                quebraLinha()
                cprint('ERROR: O Usuário encerrou o progama.', 'red')
                break
            except:
                quebraLinha()
                cprint('ERROR: Um erro inesperado aconteceu, por favor tente novamente.', 'red')
                quebraLinha()
                break


def menuEstoque(db, adicionais={}):
    opcoes = [
        "Ver Estoque",
        "Criar novo produto",
        "Atualizar Produtos",
        "Remover Produtos",
        "Comprar Produtos",
    ]

    functions = {
        1: verProdutos,
        2: criarProduto,
        3: editarProduto,
        4: removerProduto,
        5: comprarProdutos
    }

    createMenu(
        opcoes=opcoes,
        functions=functions,
        adicionais=adicionais,
        db=db,
        title='Menu do Estoque'
    )


def menuCompra(db, adicionais={}):
    opcoes = [
        "Ver Produtos e adicionar ao carrinho",
        "Ver Carrinho",
        "Efetuar Compra",
        "Remover produto do carrinho",
        "Limpar carrinho"
    ]

    functions = {
        1: addInCarrinho,
        2: verCarrinho,
        3: efetuarCompra,
        4: removerDoCarirnho,
        5: limparCarrinho
    }

    createMenu(
        opcoes=opcoes,
        functions=functions,
        db=db,
        adicionais=adicionais,
        title='Menu de Compras'
    )

def menuInicial(db, dbLogs, cliente):
    opcoes = [
        "Entrar no menu de estoque",
        "Entrar no menu de compras",
        "Historico de compras"
    ]

    functions = {
        1: menuEstoque,
        2: menuCompra,
        3: verLogs
    }

    createMenu(
        adicionais={
            'dbLogs': dbLogs,
            'cliente': cliente
        },
        opcoes=opcoes,
        functions=functions,
        db=db,
        title='Menu Inicial'
    )