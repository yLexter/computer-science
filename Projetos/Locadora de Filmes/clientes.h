#include "filmes.h"

//  ----------------------------------
//  Funções de ordenar os clientes;
//  ---------------------------------

//  typedef de uma função q retorna um inteiro e q contenha 2 parametros Cliente *
typedef int (*funcaoOrdenarCliente)(Client *, Client *);

// função para ordem crescente
int ordenarClientesPorOrdemCrescente(Client *c1, Client *c2)
{
  return strcmp(c1->nome, c2->nome);
}

// função para ordem decrescente
int ordenarClientesPorOrdemDecrescente(Client *c1, Client *c2)
{
  return (-1) * strcmp(c1->nome, c2->nome);
}

// Ordenar pela idade do cliente (Decrescente)
int ordenarClientesPorIdadeDecrescente(Client *c1, Client *c2)
{
  return c2->idade - c1->idade;
}

// Opções para ordenar os clientes
char opcoesOrdenar[4][1000] = {
    "Crescente",
    "Decrescente",
    "Idade",
    "Nenhuma"
};

//  array com as funções de ordenação
funcaoOrdenarCliente funcoesCliente[3] = {
    ordenarClientesPorOrdemCrescente,
    ordenarClientesPorOrdemDecrescente,
    ordenarClientesPorIdadeDecrescente
};

// Ordena os clientes de acordo coma opção escolhida do cliente
void ordenarClientes(StructClientes *clientes, int opcao)
{
  qsort(clientes->clientes, clientes->total, sizeof(Client), funcoesCliente[opcao]);
}

//  -----------------------------------------------------------------

// Gera um id e atribui ao cliente
void gerarIdCliente(char *varId)
{
  gerarId(varId, prefixoIdCliente, tamanhoIdCliente);

  StructClientes Clientes = getClientes();

  //  compara o id gerado com os existentes caso encontre um igual gera outro
  //  chamando a função recurssivamente
  for (int x = 0; x < Clientes.total; x++)
  {
    if (!strcmp(Clientes.clientes[x].id, varId))
      return gerarIdCliente(varId);
  }
}

// Verifica se o nome é válido
bool nomeClienteValido(char *nome)
{
  //  se o nome for maior q o definido na variavel tamanhoNome retorna false
  if (strlen(nome) >= tamanhoNomeCliente)
    return false;

  //  caso o nome contenha um caractere q n seja uma letra do alfabeto ou espaço
  //  retorna false
  while (*nome != '\0')
  {
    if (!isalpha(*nome) && *nome != ' ')
      return false;
    nome++;
  }

  return true;
}

// Cadastra o cliente, verifica se já tem cliente cadastrado com esse nome
bool cadastrarCliente(Client client)
{
  char diretorioCliente[tamanhoLinha];
  char nomeMinusculo[tamanhoNomeCliente];
  StructClientes Clientes = getClientes();

  //  copia para uma outra variavel e deixa todos em minusculo
  strcpy(nomeMinusculo, client.nome);
  toLowerCase(nomeMinusculo);

  //  verifica se existe um nome igual ao que o usuario mandou
  //  se estiver retorna false e um erro
  for (int x = 0; x < Clientes.total; x++)
  {
    toLowerCase(Clientes.clientes[x].nome);

    if (!strcmp(nomeMinusculo, Clientes.clientes[x].nome))
    {
      cabecalho();
      printf("Já existe um cliente cadastrado com esse nome.\n");
      return false;
    }
  }

  //  verifica se o nome é valido
  if (!nomeClienteValido(nomeMinusculo))
  {
    cabecalho();
    printf("Informe um nome com apenas letras e espaço com\nno máximo até %d caracteres\n",
           tamanhoNomeCliente - 1);
    return false;
  }

  //  pega o diretorio do cliente
  getDiretorioCliente(diretorioCliente, client.id, true);

  // cria o arquivo txt do cliente e salva o id na lista de clientes
  FILE *arquivoCliente = abrirArquivo(diretorioCliente, "a+", true);
  FILE *todosClientes = abrirArquivo(diretorioClientes, "a+", true);

  fprintf(todosClientes, "%s%s%s%s%d\n", client.nome, charDivisor, client.id, charDivisor, client.idade);
  fclose(arquivoCliente);
  fclose(todosClientes);

  return true;
}

//  mostra as informações de um cliente
void imprimirCliente(Client client)
{
  printf("Nome: %s\n", client.nome);
  printf("ID: %s\n", client.id);
  printf("Idade: %d\n", client.idade);
}

// Mostra todos os clientes e as funções de ordenação (opcional)
StructClientes mostrarClientes(bool ordenar)
{
  int totalOrdenacoes = sizeof(opcoesOrdenar) / sizeof(opcoesOrdenar[0]);
  int opcaoSelecionada;

  StructClientes clientes = getClientes();

  //  se n tiver nenhum cliente cadastrado retorna a struct clientes e um erro
  if (!clientes.total)
  {
    msgEntreCabecalho("Não existe clientes cadastrados no momento", true, false);
    return clientes;
  }

  //  caso a queria mostrar as opções de ordenação
  if (ordenar)
  {
    cabecalho();
    textoCentralizado("Opções de Ordenação");

    //  printa as opções de ordenação
    for (int x = 0; x < totalOrdenacoes; x++)
      printf("[%d] %s\n", x + 1, opcoesOrdenar[x]);

    msgEntreCabecalho("Ordenar por: ", true, false);
    scanf("%d", &opcaoSelecionada);
    limparTela();

    //  caso a opção seja invalida, seta a opção para ultima , que no caso
    //  é nenhum tipo de ordenação
    if (opcaoSelecionada < inicioOpcoes || opcaoSelecionada > totalOrdenacoes)
      opcaoSelecionada = totalOrdenacoes;

    // caso a opção nao seja a de nenhuma ordenação, ordena os clientes de acordo
    //  oque foi selecionado
    if (opcaoSelecionada != totalOrdenacoes)
      ordenarClientes(&clientes, opcaoSelecionada - 1);
  }

  cabecalho();
  textoCentralizado("Todos os Clientes");

  //  printa as informações de cada cliente
  for (int x = 0; x < clientes.total; x++)
  {
    printf("%d.\n", x + 1);
    imprimirCliente(clientes.clientes[x]);
    quebraLinha(1);
  }

  return clientes;
}

// Retorna um cliente escolhido pelo usuário
Client *getClientePorUsuario()
{
  int indiceCliente;
  StructClientes Clientes = mostrarClientes(false);

  //  se n tiver clientes cadastrado retorna nulo
  if (!Clientes.total)
  {
    msgEntreCabecalho("Não existe clientes cadastrado no momento", true, false);
    return NULL;
  }

  msgEntreCabecalho("Digite o número referente ao cliente", true, false);
  scanf("%d", &indiceCliente);
  limparBuffer();
  limparTela();

  //  Caso a opção escolhida seja inválida, menor q o minimo e maior q o total
  if (indiceCliente < inicioOpcoes || indiceCliente > Clientes.total)
  {
    msgEntreCabecalho("A opção escolhida é invalida", true, false);
    return NULL;
  }

  return &Clientes.clientes[indiceCliente - 1];
}

// Deleta um cliente do banco de dados
void deletarClienteArquivo(Client *cliente)
{
  char diretorioClienteRemovido[tamanhoArray];

  // pega o diretorio do cliente e remove o arquivo alem de remover da lista
  // de clientes
  getDiretorioCliente(diretorioClienteRemovido, cliente->id, true);

  // Exclui o arquivo do cliente
  remove(diretorioClienteRemovido);

  // Remove o cliente da lista de clientes
  deletarLinhaArquivo(diretorioClientes, cliente->id);
}
