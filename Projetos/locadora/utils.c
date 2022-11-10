#include <stdio.h>
#include <stdbool.h>
#include <malloc.h>
#include <time.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <dirent.h>
#include <unistd.h>

// -----------------------------------------------------
// Constantes
//------------------------------------------------------

const int tamanhoArray = 1000;
const int tamanhoIdFilme = 10;
const int tamanhoLinha = 500;
const int tamanhoNomeCliente = 21;
const int tamanhoIdCliente = 10;
const int prefixoIdCliente = 2;
const char diretorioClientes[50] = "geral/clientes.txt";
const char diretorioFilmes[50] = "geral/filmes.txt";
const char charDivisor[2] = "-";
const char espacoBranco = '_';
const char opcoes[8][1000] = {
    "Ver Filmes",
    "Alugar Filme",
    "Cadastrar Cliente",
    "Mostrar Clientes",
    "Deletar Cliente",
    "Mostrar Filmes Alugados",
    "Deletar Filme",
    "Sair",
};

typedef struct
{
  char id[tamanhoIdFilme];
  char titulo[tamanhoLinha];
  char duracao[tamanhoLinha];
  char nota[tamanhoLinha];
  char genero[tamanhoLinha];
  char classificacao[tamanhoLinha];
} Filme;

typedef struct
{
  char nome[tamanhoNomeCliente];
  char id[tamanhoIdCliente];
} Client;

typedef struct
{
  char idFilme[tamanhoIdFilme];
  char titulo[tamanhoLinha];
  char autor[tamanhoNomeCliente];
  char idAutor[tamanhoIdCliente];
} FilmeAlugado;

// -----------------------------------------------------
// Funções utieis no geral
//------------------------------------------------------

char **strsplit(const char *src, const char *delim)
{
  char *pbuf = NULL;
  char *ptok = NULL;
  int count = 0;
  int srclen = 0;
  char **pparr = NULL;

  srclen = strlen(src);

  pbuf = (char *)malloc(srclen + 1);

  if (!pbuf)
    return NULL;

  strcpy(pbuf, src);

  ptok = strtok(pbuf, delim);

  while (ptok)
  {
    pparr = (char **)realloc(pparr, (count + 1) * sizeof(char *));
    *(pparr + count) = strdup(ptok);

    count++;
    ptok = strtok(NULL, delim);
  }

  pparr = (char **)realloc(pparr, (count + 1) * sizeof(char *));
  *(pparr + count) = NULL;

  free(pbuf);

  return pparr;
}

void cabecalho()
{
  printf("-------------------------------------------------------\n");
}

void limparBuffer()
{
  char c;
  while ((c = getchar()) != '\n' && c != EOF)
  {
  }
}

void limparTela()
{
  system("clear");
}

void textoCentralizado(char *nome)
{
  printf("                %s\n\n", nome);
}

void quebraLinha(int quantidade)
{
  for (int x = 0; x < quantidade; x++)
    printf("\n");
}

void toLowerCase(char *palavra)
{
  while (*palavra != '\0')
  {
    *palavra = tolower(*palavra);
    palavra++;
  }
}

void getDiretorioCliente(char *var, char *id, bool extensao)
{
  snprintf(var, tamanhoLinha, "clientes/%s%s", id, extensao ? ".txt" : "");
}

void nomeComEspaco(char *nome)
{
  while (*nome != '\0')
  {
    if (*nome == espacoBranco)
      *nome = ' ';
    nome++;
  }
}

void msgEntreCabecalho(char *msg, bool superior, bool inferior)
{
  if (superior)
    cabecalho();

  printf("%s\n", msg);

  if (inferior)
    cabecalho();
}

void encerrarProgama(char *msg)
{
  limparTela();
  msgEntreCabecalho(msg, true, true);
  exit(0);
}

FILE *abrirArquivo(char *nome, char *tipo, bool encerrar)
{
  char msgError[tamanhoArray];

  FILE *arquivo = fopen(nome, tipo);

  if (arquivo == NULL && encerrar)
  {
    snprintf(msgError, tamanhoArray, "Não foi possível abrir o arquivo %s", nome);
    encerrarProgama(msgError);
  }

  return arquivo;
}

void removerLinhaEspecifica(char *diretorio, char *idLinha)
{
  char nomeTemporario[10] = "temp.txt";
  char **infoDiretorio = strsplit(diretorio, "/");
  char linha[tamanhoLinha];
  char *linhasNovoArquivo[tamanhoArray];
  char diretorioNovoArquivo[tamanhoLinha];
  int totalLinhas = 0;

  FILE *arq = abrirArquivo(diretorio, "a+", true);

  while (fscanf(arq, "%s\n", linha) > 0)
  {
    char *subString = strstr(linha, idLinha);

    if (subString == 0)
    {
      linhasNovoArquivo[totalLinhas] = malloc(sizeof(char) * tamanhoLinha);
      snprintf(linhasNovoArquivo[totalLinhas++], tamanhoLinha, "%s", linha);
    }
  }

  snprintf(diretorioNovoArquivo, tamanhoLinha, "%s/%s", infoDiretorio[0], nomeTemporario);

  FILE *novoArquivo = abrirArquivo(diretorioNovoArquivo, "a+", true);

  for (int x = 0; x < totalLinhas; x++)
    fprintf(novoArquivo, "%s\n", linhasNovoArquivo[x]);

  fclose(novoArquivo);
  remove(diretorio);
  rename(diretorioNovoArquivo, diretorio);
}

void getClientes(Client *Clientes, int *total)
{
  Client client;

  int totalClientes = 0;
  char linha[tamanhoLinha];

  FILE *clientes = abrirArquivo(diretorioClientes, "rt", true);

  while (fscanf(clientes, "%s\n", linha) > 0)
  {
    char **dadosCliente = strsplit(linha, charDivisor);

    snprintf(client.nome, tamanhoNomeCliente, "%s", dadosCliente[0]);
    snprintf(client.id, tamanhoIdCliente, "%s", dadosCliente[1]);

    Clientes[totalClientes++] = client;
  }

  fclose(clientes);
  *total = totalClientes;
}

void getFilmes(Filme *arrayFilmes, int *total)
{
  FILE *filmes = abrirArquivo(diretorioFilmes, "rt", true);
  char linha[tamanhoLinha];
  int i = 0;

  while (fscanf(filmes, "%s\n", linha) > 0)
  {
    Filme newFilme;

    char **dadosFilme = strsplit(linha, charDivisor);

    snprintf(newFilme.id, tamanhoIdFilme, dadosFilme[0]);
    snprintf(newFilme.titulo, tamanhoLinha, dadosFilme[1]);
    snprintf(newFilme.duracao, tamanhoLinha, dadosFilme[2]);
    snprintf(newFilme.nota, tamanhoLinha, dadosFilme[3]);
    snprintf(newFilme.genero, tamanhoLinha, dadosFilme[4]);
    snprintf(newFilme.classificacao, tamanhoLinha, dadosFilme[5]);

    arrayFilmes[i++] = newFilme;
  }

  fclose(filmes);
  *total = i;
}

void gerarId(char *varId, int prefixo, int quantidade)
{
  srand(time(NULL));

  varId[0] = prefixo + '0';

  for (int x = 1; x < quantidade - 1; x++)
    varId[x] = (rand() % 10) + '0';

  varId[quantidade] = '\0';
}

void formatarNome(char *nome)
{
  while (*nome != '\0')
  {
    if (*nome == ' ')
      *nome = espacoBranco;
    nome++;
  }
}

// ---------------------------------------------------------
// ----- Funções sobre os filmes
// ---------------------------------------------------------

void mostrarInfoFilme(Filme filme)
{
  nomeComEspaco(filme.titulo);
  printf("Titúlo: %s\n", filme.titulo);
  printf("Duração: %s\n", filme.duracao);
  printf("Nota: %s\n", filme.nota);
  printf("Genêro: %s\n", filme.genero);
  printf("Classificação: %s\n", filme.classificacao);
}

void verFilmes()
{
  Filme filmes[tamanhoArray];
  int totalFilmes;

  getFilmes(filmes, &totalFilmes);

  if (!totalFilmes)
    return msgEntreCabecalho("Não existe filmes cadastrados no momento.", true, false);

  cabecalho();
  textoCentralizado("Todos os Filmes");

  for (int x = 0; x < totalFilmes; x++)
  {
    Filme filme = filmes[x];
    printf("%d.\n", x + 1);
    mostrarInfoFilme(filme);
    quebraLinha(1);
  }
}

Filme *getFilmeByUser()
{
  int totalFilmes;
  int indiceFilme;
  Filme filmes[tamanhoArray];

  getFilmes(filmes, &totalFilmes);

  if (!totalFilmes)
    return NULL;

  verFilmes();

  msgEntreCabecalho("Digite o indíce do filme", true, false);
  scanf("%d", &indiceFilme);
  limparBuffer();
  limparTela();

  if (indiceFilme < 1 || indiceFilme > totalFilmes)
    return NULL;

  return &filmes[indiceFilme - 1];
}

void getFilmesAlugados(FilmeAlugado *FilmesAlugados, int *var)
{
  FilmeAlugado filmeAlugado;
  char diretorio[tamanhoArray];
  char linha[tamanhoLinha];
  struct dirent *arquivo;
  int totalFilmes = 0;

  DIR *dir = opendir("clientes");

  if (dir == NULL)
    encerrarProgama("Não foi possivel abrir a pasta clientes");

  while ((arquivo = readdir(dir)) != NULL)
  {

    if (!strcmp(arquivo->d_name, ".") || !strcmp(arquivo->d_name, ".."))
      continue;

    getDiretorioCliente(diretorio, arquivo->d_name, false);

    FILE *bufferArquivo = abrirArquivo(diretorio, "rt", true);

    while (fscanf(bufferArquivo, "%s\n", linha) > 0)
    {
      char **split = strsplit(linha, charDivisor);

      snprintf(filmeAlugado.idFilme, tamanhoIdFilme, "%s", split[0]);
      snprintf(filmeAlugado.titulo, tamanhoLinha, "%s", split[1]);
      snprintf(filmeAlugado.autor, tamanhoNomeCliente, "%s", split[2]);
      snprintf(filmeAlugado.idAutor, tamanhoIdCliente, "%s", split[3]);

      FilmesAlugados[totalFilmes++] = filmeAlugado;
    }

    fclose(bufferArquivo);
  }

  closedir(dir);
  *var = totalFilmes;
}

bool verificarFilmeAlugado(Filme *filme)
{
  FilmeAlugado filmes[tamanhoArray];
  int totalFilmes;

  getFilmesAlugados(filmes, &totalFilmes);

  for (int x = 0; x < totalFilmes; x++)
  {
    if (!strcmp(filmes[x].idFilme, filme->id))
      return true;
  }

  return false;
}

void infoFilmeAlugado(FilmeAlugado filme)
{
  nomeComEspaco(filme.titulo);
  nomeComEspaco(filme.autor);
  printf("Autor: %s(%s)\n", filme.autor, filme.idAutor);
  printf("Titulo: %s\n", filme.titulo);
  printf("ID: %s\n", filme.idFilme);
}

// ----------------------------------------------------
// Funções referente ao cliente
//-----------------------------------------------------

void gerarIdCliente(char *varId)
{
  gerarId(varId, prefixoIdCliente, tamanhoIdCliente);

  Client clientes[tamanhoArray];
  int totalClientes;

  getClientes(clientes, &totalClientes);

  for (int x = 0; x < totalClientes; x++)
  {
    if (!strcmp(clientes[x].id, varId))
      return gerarIdCliente(varId);
  }
}

bool cadastrarCliente(Client client)
{
  Client clientes[tamanhoArray];
  char diretorioCliente[tamanhoLinha];
  char nomeMinusculo[tamanhoNomeCliente];
  int totalClientes;

  getClientes(clientes, &totalClientes);

  strcpy(nomeMinusculo, client.nome);
  toLowerCase(nomeMinusculo);

  for (int x = 0; x < totalClientes; x++)
  {
    toLowerCase(clientes[x].nome);

    if (!strcmp(nomeMinusculo, clientes[x].nome))
      return false;
  }

  getDiretorioCliente(diretorioCliente, client.id, true);

  FILE *arquivoCliente = abrirArquivo(diretorioCliente, "a+", true);
  fclose(arquivoCliente);

  FILE *todosClientes = abrirArquivo(diretorioClientes, "a+", true);
  fprintf(todosClientes, "%s%s%s\n", client.nome, charDivisor, client.id);
  fclose(todosClientes);

  return true;
}

bool nomeValido(char *nome)
{
  while (*nome != '\0')
  {
    if (!isalpha(*nome) && *nome != ' ')
      return false;
    nome++;
  }
  return true;
}

void salvarFilmeCliente(Client *client, Filme *filme)
{
  char diretorio[tamanhoArray];
  getDiretorioCliente(diretorio, client->id, true);

  FILE *arquivo = abrirArquivo(diretorio, "a+", true);

  fprintf(arquivo, "%s%s%s%s%s%s%s\n", filme->id, charDivisor, filme->titulo, charDivisor, client->nome, charDivisor, client->id);
  fclose(arquivo);
}

void mostrarInfoCliente(Client client)
{
  nomeComEspaco(client.nome);
  printf("Nome: %s\nID: %s\n", client.nome, client.id);
}

void mostrarClientes()
{
  Client clientes[tamanhoArray];
  int totalClientes;

  getClientes(clientes, &totalClientes);

  if (!totalClientes)
    return msgEntreCabecalho("Não existe clientes cadastrados no momento.", true, false);

  cabecalho();
  textoCentralizado("Todos os Clientes \n");

  for (int x = 0; x < totalClientes; x++)
  {
    printf("%d.\n", x + 1);
    mostrarInfoCliente(clientes[x]);
    quebraLinha(1);
  }
}

Client *getClienteByUser()
{
  Client clientes[tamanhoArray];
  int totalClientes;
  int indiceCliente;

  getClientes(clientes, &totalClientes);

  if (!totalClientes)
  {
    msgEntreCabecalho("Não existe clientes cadastrados no momento", true, false);
    return NULL;
  }

  mostrarClientes();

  msgEntreCabecalho("Digite o número referente ao cliente", false, true);
  scanf("%d", &indiceCliente);
  limparBuffer();
  limparTela();

  if (indiceCliente < 1 || indiceCliente > totalClientes)
  {
    msgEntreCabecalho("A opção escolhida é invalida", true, false);
    return NULL;
  }

  return &clientes[indiceCliente - 1];
}
