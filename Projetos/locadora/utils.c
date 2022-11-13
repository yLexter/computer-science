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

#define tamanhoArray 1000
#define tamanhoIdFilme 10
#define tamanhoIdCliente 10
#define tamanhoLinha 500
#define tamanhoNomeCliente 50
#define prefixoIdCliente 2
#define charDivisor "-"
#define espacoBranco '_'
#define diretorioClientes "geral/clientes.txt"
#define diretorioFilmes "geral/filmes.txt"

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

typedef struct
{
  Filme filmes[tamanhoArray];
  int total;
} StructFilmes;

typedef struct
{
  Client clientes[tamanhoArray];
  int total;
} StructClientes;

typedef struct
{
  FilmeAlugado filmes[tamanhoArray];
  int total;
} StructFA;

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

  snprintf(diretorioNovoArquivo, tamanhoLinha, "%s/%s", infoDiretorio[0], "temp.txt");

  FILE *novoArquivo = abrirArquivo(diretorioNovoArquivo, "a+", true);

  for (int x = 0; x < totalLinhas; x++)
    fprintf(novoArquivo, "%s\n", linhasNovoArquivo[x]);

  fclose(novoArquivo);
  remove(diretorio);
  rename(diretorioNovoArquivo, diretorio);
}

StructClientes getClientes()
{
  Client client;
  char linha[tamanhoLinha];
  StructClientes clientes;
  FILE *arquivoClientes = abrirArquivo(diretorioClientes, "rt", true);

  clientes.total = 0;

  while (fscanf(arquivoClientes, "%s\n", linha) > 0)
  {
    char **dadosCliente = strsplit(linha, charDivisor);

    snprintf(client.nome, tamanhoNomeCliente, "%s", dadosCliente[0]);
    snprintf(client.id, tamanhoIdCliente, "%s", dadosCliente[1]);

    clientes.clientes[clientes.total++] = client;
  }

  fclose(arquivoClientes);

  return clientes;
}

StructFilmes getFilmes()
{
  char linha[tamanhoLinha];
  StructFilmes Filmes;
  Filme novoFilme;

  FILE *arquivoFilmes = abrirArquivo(diretorioFilmes, "rt", true);

  Filmes.total = 0;

  while (fscanf(arquivoFilmes, "%s\n", linha) > 0)
  {
    char **dadosFilme = strsplit(linha, charDivisor);

    snprintf(novoFilme.id, tamanhoIdFilme, dadosFilme[0]);
    snprintf(novoFilme.titulo, tamanhoLinha, dadosFilme[1]);
    snprintf(novoFilme.duracao, tamanhoLinha, dadosFilme[2]);
    snprintf(novoFilme.nota, tamanhoLinha, dadosFilme[3]);
    snprintf(novoFilme.genero, tamanhoLinha, dadosFilme[4]);
    snprintf(novoFilme.classificacao, tamanhoLinha, dadosFilme[5]);

    Filmes.filmes[Filmes.total++] = novoFilme;
  }

  fclose(arquivoFilmes);

  return Filmes;
}

void gerarId(char *varId, int prefixo, int quantidade)
{
  srand(time(NULL));

  varId[0] = prefixo + '0';

  for (int x = 1; x < quantidade - 1; x++)
    varId[x] = (rand() % 10) + '0';

  varId[quantidade - 1] = '\0';
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

StructFilmes mostrarFilmes()
{
  StructFilmes filmes = getFilmes();

  if (!filmes.total)
  {
    msgEntreCabecalho("Não existe filmes cadastrados no momento.", true, false);
    return filmes;
  }

  cabecalho();
  textoCentralizado("Todos os Filmes");

  for (int x = 0; x < filmes.total; x++)
  {
    printf("%d.\n", x + 1);
    mostrarInfoFilme(filmes.filmes[x]);
    quebraLinha(1);
  }

  return filmes;
}

Filme *getFilmeByUser()
{
  int indiceFilme;
  StructFilmes Filmes = mostrarFilmes();

  if (!Filmes.total)
    return NULL;

  msgEntreCabecalho("Digite o indíce do filme", true, false);
  scanf("%d", &indiceFilme);
  limparBuffer();
  limparTela();

  if (indiceFilme < 1 || indiceFilme > Filmes.total)
  {
    msgEntreCabecalho("A opção escolhida é invalida", true, false);
    return NULL;
  }

  return &Filmes.filmes[indiceFilme - 1];
}

StructFA getFilmesAlugados()
{
  char diretorio[tamanhoArray];
  char linha[tamanhoLinha];
  FilmeAlugado filmeAlugado;
  StructFA Filmes;
  struct dirent *arquivo;

  Filmes.total = 0;

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

      Filmes.filmes[Filmes.total++] = filmeAlugado;
    }

    fclose(bufferArquivo);
  }

  closedir(dir);

  return Filmes;
}

bool verificarFilmeAlugado(Filme *filme)
{
  StructFA Filmes = getFilmesAlugados();

  for (int x = 0; x < Filmes.total; x++)
  {
    if (!strcmp(Filmes.filmes[x].idFilme, filme->id))
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

StructFA mostrarFilmesAlugados()
{
  StructFA Filmes = getFilmesAlugados();

  if (!Filmes.total)
  {
    msgEntreCabecalho("Não existe filmes alugado no momento", true, false);
    return Filmes;
  }

  cabecalho();
  textoCentralizado("Filmes Alugados");

  for (int x = 0; x < Filmes.total; x++)
  {
    printf("%d.\n", x + 1);
    infoFilmeAlugado(Filmes.filmes[x]);
    quebraLinha(1);
  }

  return Filmes;
}

// ----------------------------------------------------
// Funções referente ao cliente
//-----------------------------------------------------

void gerarIdCliente(char *varId)
{
  gerarId(varId, prefixoIdCliente, tamanhoIdCliente);

  StructClientes Clientes = getClientes();

  for (int x = 0; x < Clientes.total; x++)
  {
    if (!strcmp(Clientes.clientes[x].id, varId))
      return gerarIdCliente(varId);
  }
}
bool cadastrarCliente(Client client)
{
  char diretorioCliente[tamanhoLinha];
  char nomeMinusculo[tamanhoNomeCliente];
  StructClientes Clientes = getClientes();

  strcpy(nomeMinusculo, client.nome);
  toLowerCase(nomeMinusculo);

  for (int x = 0; x < Clientes.total; x++)
  {
    toLowerCase(Clientes.clientes[x].nome);

    if (!strcmp(nomeMinusculo, Clientes.clientes[x].nome))
      return false;
  }

  getDiretorioCliente(diretorioCliente, client.id, true);

  FILE *arquivoCliente = abrirArquivo(diretorioCliente, "a+", true);
  FILE *todosClientes = abrirArquivo(diretorioClientes, "a+", true);

  fprintf(todosClientes, "%s%s%s\n", client.nome, charDivisor, client.id);
  fclose(arquivoCliente);
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

StructClientes mostrarClientes()
{
  StructClientes clientes = getClientes();

  if (!clientes.total)
  {
    msgEntreCabecalho("Não existe clientes cadastrados no momento", true, false);
    return clientes;
  }

  cabecalho();
  textoCentralizado("Todos os Clientes \n");

  for (int x = 0; x < clientes.total; x++)
  {
    printf("%d.\n", x + 1);
    mostrarInfoCliente(clientes.clientes[x]);
    quebraLinha(1);
  }

  return clientes;
}

Client *getClienteByUser()
{
  int indiceCliente;
  StructClientes Clientes = mostrarClientes();

  if (!Clientes.total)
    return NULL;

  msgEntreCabecalho("Digite o número referente ao cliente", false, true);
  scanf("%d", &indiceCliente);
  limparBuffer();
  limparTela();

  if (indiceCliente < 1 || indiceCliente > Clientes.total)
  {
    msgEntreCabecalho("A opção escolhida é invalida", true, false);
    return NULL;
  }

  return &Clientes.clientes[indiceCliente - 1];
}
