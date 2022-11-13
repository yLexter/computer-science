#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include "utils.c::"

void alugarFilme()
{
  Client *clientEscolhido = getClienteByUser();

  if (clientEscolhido == NULL)
    return;

  Filme *filmeEscolhido = getFilmeByUser();

  if (filmeEscolhido == NULL)
    return;

  if (verificarFilmeAlugado(filmeEscolhido))
    return msgEnstreCabecalho("Este filme já esta alugado.", true, false);

  salvarFilmeCliente(clientEscolhido, filmeEscolhido);
  msgEntreCabecalho("Filme alugado com sucesso.", true, false);
}

void cadastroCliente()
{
  Client client;

  cabecalho();
  textoCentralizado("Tela de Cadastro");
  printf("Digite seu nome: ");
  scanf("%[^\n]", client.nome);
  limparTela();

  if (!nomeValido(client.nome))
    return msgEntreCabecalho("Informe um nome apenas com letras e espaços", true, false);

  gerarIdCliente(client.id);
  formatarNome(client.nome);

  bool cadastradoComSucesso = cadastrarCliente(client);

  if (cadastradoComSucesso)
  {
    msgEntreCabecalho("Cadastro realizado com sucesso", true, false);
  }
  else
  {
    msgEntreCabecalho("Já existe um cliente com este nome!", true, false);
  }
}

void removerCliente()
{
  char diretorioClienteRemovido[tamanhoArray];

  Client *clientRemovido = getClienteByUser();

  if (clientRemovido == NULL)
    return;

  getDiretorioCliente(diretorioClienteRemovido, clientRemovido->id, true);
  remove(diretorioClienteRemovido);
  removerLinhaEspecifica(diretorioClientes, clientRemovido->id);

  cabecalho();
  printf("O cliente %s(%s) foi removido com sucesso.\n", clientRemovido->nome, clientRemovido->id);
}

void deletarFilme()
{
  Filme *filmeDeletado = getFilmeByUser();

  if (filmeDeletado == NULL)
    return;

  removerLinhaEspecifica(diretorioFilmes, filmeDeletado->id);
  msgEntreCabecalho("Filme deletado com sucesso", true, false);
}

void menuInicial()
{
  const int ultimaOpcao = sizeof(opcoes) / sizeof(opcoes[0]);
  int opcaoSelecionada;

  cabecalho();
  textoCentralizado("Menu Inicial");

  for (int x = 0; x < ultimaOpcao; x++)
    printf("%d. %s\n", x + 1, opcoes[x]);

  msgEntreCabecalho("Digite a opção que deseja ", true, false);
  scanf("%d", &opcaoSelecionada);
  limparBuffer();
  limparTela();

  switch (opcaoSelecionada)
  {
  case 1:
    mostrarFilmes();
    break;
  case 2:
    alugarFilme();
    break;
  case 3:
    cadastroCliente();
    break;
  case 4:
    mostrarClientes();
    break;
  case 5:
    removerCliente();
    break;
  case 6:
    mostrarFilmesAlugados();
    break;
  case 7:
    deletarFilme();
    break;
  case ultimaOpcao:
    encerrarProgama("Você finalizou o progama.");
    break;
  default:
    msgEntreCabecalho("A opção escolhida é invalida.", true, false);
    break;
  }
}

int main()
{

  while (true)
    menuInicial();

  return 0;
}