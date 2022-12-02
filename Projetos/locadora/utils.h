#include <ctype.h>
#include <dirent.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>

// -----------------------------------------------------
// Constantes
//------------------------------------------------------

#define menorNotaFilme 0
#define maiorNotaFilme 5.00
#define inicioOpcoes 1
#define tamanhoArray 1000
#define tamanhoIdFilme 10
#define tamanhoIdCliente 10
#define tamanhoLinha 200
#define tamanhoNomeCliente 25
#define tamanhoTituloFilme 80
#define prefixoIdCliente 2
#define prefixoIdFilme 4
#define charDivisor "-"
#define diretorioClientes "geral/clientes.txt"
#define diretorioFilmes "geral/filmes.txt"
#define diretorioPopulares "geral/populares.txt"
#define pastaClientes "clientes"

// Estes enums são referentes as posições dos dados salvos,
// Ex: o FL_id esta na posição 0, então o primeiro dado salvo é o ID
//Fl = Filme, FA = Filme Alugado, CL = cliente
enum {
  FL_id,
  FL_titulo,
  FL_duracao,
  FL_nota,
  FL_genero,
  FL_classificacao,
  FL_total
};
enum { CL_id, CL_nome, CL_idade, CL_total };
enum { FA_id, FA_titulo, FA_autor, FA_idAutor, FA_total };

// Structs de Filme, cliente e Filme Alugado
typedef struct {
  char id[tamanhoIdFilme];
  char titulo[tamanhoLinha];
  int duracao;
  char genero[tamanhoLinha];
  float nota;
  int classificacao;
} Filme;

typedef struct {
  char nome[tamanhoNomeCliente];
  char id[tamanhoIdCliente];
  int idade;
} Client;

typedef struct {
  char idFilme[tamanhoIdFilme];
  char titulo[tamanhoTituloFilme];
  char autor[tamanhoNomeCliente];
  char idAutor[tamanhoIdCliente];
} FilmeAlugado;

// Struct de lista de populares
typedef struct lista {
  char titulo[tamanhoTituloFilme];
  char id[tamanhoIdFilme];
  int total;
  struct lista *prox;
} ListaPopulares;


// Structs q armazena um array de filmes, clientes ou filmes alugados e o total de elementos
typedef struct {
  Filme *filmes;
  int total;
} StructFilmes;

typedef struct {
  Client *clientes;
  int total;
} StructClientes;

typedef struct {
  FilmeAlugado *filmes;
  int total;
} StructFA;


// Opções do menu inicial
const char opcoesMenu[11][100] = {
    "Ver Filmes",
    "Alugar Filme",
    "Cadastrar Cliente",
    "Mostrar Clientes",
    "Deletar Cliente",
    "Mostrar Filmes Alugados",
    "Deletar Filme",
    "Cadastrar Filme",
    "Devolver Filme",
    "Mostrar Filmes Populares",
    "Sair",
};

// -----------------------------------------------------
// Funções utieis no geral
//------------------------------------------------------

// Função split padrão
char ** strsplit(const char *src, const char *delim) {
  char **pparr = NULL;
  char *pbuf = NULL;
  char *ptok = NULL;
  int count = 0;
  int srclen = 0;

  srclen = strlen(src);

  // aloca memoria para o len da string + 1 para armazenar a string junto
  // com o caractere nulo
  pbuf = (char *)malloc(srclen + 1);

  // caso nao consiga alocar memoria retorna nulo
  if (!pbuf)
    return NULL;

  // copia a string para variavel pbfuf
  strcpy(pbuf, src);

  // ptok retorna tudo oq estiver antes do primeiro delim encontrado
  ptok = strtok(pbuf, delim);

  // caso esteja delim presente na string, o while ira rodando
  while (ptok) {
    // pparr sera uma matriz de string e realocar memoria para o total de delim encontrado
    // mais um
    pparr = (char **)realloc(pparr, (count + 1) * sizeof(char *));
    // o ponteiro pparr + count sera a string ptok ja q a funcao strdup retorna um ponteiro
    // para a string passada
    *(pparr + count++) = strdup(ptok);

    // redefinição para pegar a proxima palavra e não ficar em looping infinito
    ptok = strtok(NULL, delim);
  }

  // alloca mais um espaço de memoria para para por o ponteiro nulo q sera a condição de parada
  pparr = (char **)realloc(pparr, (count + 1) * sizeof(char *));
  *(pparr + count) = NULL;

  // libera a memoria pbuf
  free(pbuf);

  return pparr;
}

// Printa um cabecalho
void cabecalho() {
  printf("-------------------------------------------------------\n");
}

// Limpa o buffer do teclado
void limparBuffer() {
  char c;
  while ((c = getchar()) != '\n' && c != EOF) {}
}

// Limpa a tela
void limparTela() {
  system("clear");
}

//Centraliza o texto na tela
void textoCentralizado(char *nome) {
  printf("                %s\n\n", nome);
}
//Pula uma quantidade de linha 
void quebraLinha(int quantidade) {
  for (int x = 0; x < quantidade; x++)
    printf("\n");
}
//Transforma tudo em minúsculo
void toLowerCase(char *palavra) {
  while (*palavra != '\0') {
    *palavra = tolower(*palavra);
    palavra++;
  }
}
//Pega o diretório do cliente
void getDiretorioCliente(char *var, char *id, bool extensao) {
  snprintf(var, tamanhoLinha, "%s/%s%s", pastaClientes, id, extensao ? ".txt" : "");
}

//Mensagem que vai no meio do cabeçalho
void msgEntreCabecalho(char *msg, bool superior, bool inferior) {
  if (superior)
    cabecalho();

  printf("%s\n", msg);

  if (inferior)
    cabecalho();
}

//Finaliza o programa
void encerrarProgama(char *msg) {
  limparTela();
  msgEntreCabecalho(msg, true, true);
  exit(0);
}

//Tenta abrir um arquivo
FILE * abrirArquivo(char *nome, char *tipo, bool encerrar) {
  char msgError[tamanhoArray];

  FILE *arquivo = fopen(nome, tipo);

  // caso nao consiga abrir o arquivo e deseje-se encerrar o progama
  if (arquivo == NULL && encerrar) {
    snprintf(msgError, tamanhoArray, "Não foi possível abrir o arquivo %s", nome);
    encerrarProgama(msgError);
  }

  return arquivo;
}

//Apaga uma linha inteira do diretório
void deletarLinha(char *diretorio, char *idLinha) {
  char *nomeArquivoTemporario = "temp.txt";
  char diretorioNovoArquivo[tamanhoLinha];
  char linha[tamanhoLinha];
  char **linhasNovoArquivo = NULL;
  int totalLinhas = 0;

  FILE *arq = abrirArquivo(diretorio, "rt", true);

   // pecorre todas as linha do arquivo caso o id linha esteja presente em umas das linha não
   // adiciona fazendo assim com o que a  linha seja excluida
  while (fgets(linha, tamanhoLinha - 1, arq) != NULL) {
    if (!strstr(linha, idLinha)) {
      linhasNovoArquivo = (char **)realloc(linhasNovoArquivo, (totalLinhas + 1) * sizeof(char *));
      *(linhasNovoArquivo + totalLinhas++) = strdup(linha);
    }
  }

   // verifica se o diretorio e uma pasta ou esta na raiz do projeto
  if (strstr(diretorio, "/")) {
    char **infoDiretorio = strsplit(diretorio, "/");
    snprintf(diretorioNovoArquivo, tamanhoLinha, "%s/%s", infoDiretorio[0], nomeArquivoTemporario);
  } else {
    snprintf(diretorioNovoArquivo, tamanhoLinha, "%s", nomeArquivoTemporario);
  }

   // abre um novo arquivo com o mesmo q o antigo 
  FILE *novoArquivo = abrirArquivo(diretorioNovoArquivo, "a+", true);

   // escreve todas as linhas menos as que contenham a id linha no arquivo novo
  for (int x = 0; x < totalLinhas; x++)
    fprintf(novoArquivo, "%s", linhasNovoArquivo[x]);

  fclose(novoArquivo);

   //remove o antigo arquivo e renomeia o novo para o nome do antigo
  remove(diretorio);
  rename(diretorioNovoArquivo, diretorio);
}

//Gera um id aleatório
void gerarId(char *varId, int prefixo, int quantidade) {
 // gera um semente usando o tempo unix para que os numeros sejam aleatorios
  srand(time(NULL));

 // o "+ '0'" transforma um char numerico em um int numero
  varId[0] = prefixo + '0';

  for (int x = 1; x < quantidade - 1; x++)
    varId[x] = (rand() % 10) + '0';

  varId[quantidade - 1] = '\0';
}

//Formata para que cada vez que aparecer um espaço em branco(modificaado depois para "_") a primeira letra fique maíúscula
//Exemplo: Até o último homem = Até_O_Último_Homem
void formatarNome(char *nome) {

  bool letraMaiuscula = true;

  while (*nome != '\0') {
     // caso a letra seja um espaço a proxima sera transformada em maiuscula e remove e coloca um 
     // estiver na variavel espÇo em branco
    if (*nome == ' ') {
      letraMaiuscula = true;
      nome++;
      continue;
    }

    if (letraMaiuscula) {
      *nome = toupper(*nome);
    } else {
      *nome = tolower(*nome);
    }

    letraMaiuscula = false;
    nome++;
  }
}

//Estrutura para clientes
StructClientes getClientes() {
  Client client;
  char linha[tamanhoLinha];
  StructClientes clientes;

  // abre o arquivo da lista de clientes presente
  FILE *arquivoClientes = abrirArquivo(diretorioClientes, "rt", true);

  clientes.total = 0;
  // aloca memoria pra uma struct cliente
  clientes.clientes = (Client *)malloc(sizeof(Client));

  // um while em cada linha do arquivo
  while (fgets(linha, tamanhoLinha - 1, arquivoClientes) != NULL) {
    char **dadosCliente = strsplit(linha, charDivisor);

    snprintf(client.nome, tamanhoNomeCliente, "%s", dadosCliente[CL_id]);
    snprintf(client.id, tamanhoIdCliente, "%s" , dadosCliente[CL_nome]);
    client.idade = atoi(dadosCliente[CL_idade]);

     // guarda o cliente no array e soma mais um no total
    clientes.clientes[clientes.total++] = client;
     // realloca memoria do array para sempre o total de clientes presente + 1
    clientes.clientes = (Client *)realloc(clientes.clientes, sizeof(Client) * (clientes.total + 1));
  }

  fclose(arquivoClientes);

  return clientes;
}

//Estrutura para filmes
StructFilmes getFilmes() {
  char linha[tamanhoLinha];
  StructFilmes Filmes;
  Filme novoFilme;

  // abre o arquivos de filmes de apenas leitura
  FILE *arquivoFilmes = abrirArquivo(diretorioFilmes, "rt", true);

  Filmes.total = 0;
   //alloca memoria para uma struct filme
  Filmes.filmes = (Filme *)malloc(sizeof(Filme));

   // um while para cada linha do arquivo
  while (fgets(linha, tamanhoLinha - 1, arquivoFilmes) != NULL) {
    char **dadosFilme = strsplit(linha, charDivisor);

    snprintf(novoFilme.id, tamanhoIdFilme, dadosFilme[FL_id]);
    snprintf(novoFilme.titulo, tamanhoTituloFilme, dadosFilme[FL_titulo]);
    snprintf(novoFilme.genero, tamanhoLinha, dadosFilme[FL_genero]);
    novoFilme.duracao = atoi(dadosFilme[FL_duracao]);
    novoFilme.nota = atof(dadosFilme[FL_nota]);
    novoFilme.classificacao = atoi(dadosFilme[FL_classificacao]);

     // guarda o filme no array e soma o total + 1
    Filmes.filmes[Filmes.total++] = novoFilme;
     // reealoca memoria para para o total presente + 1
    Filmes.filmes = (Filme *)realloc(Filmes.filmes, sizeof(Filme) * (Filmes.total + 1));
  }

  fclose(arquivoFilmes);

  return Filmes;
}

//Estrutur para armazenar os filmes alugados no momento
StructFA getFilmesAlugados() {
  char diretorio[tamanhoArray];
  char linha[tamanhoLinha];
  FilmeAlugado filmeAlugado;
  StructFA Filmes;
  struct dirent *arquivo;

  Filmes.total = 0;
   // alloca memoria para um struct filme alugado
  Filmes.filmes = (FilmeAlugado *)malloc(sizeof(FilmeAlugado));

   // abre a pasta de clientes
  DIR *dir = opendir(pastaClientes);

   // se nao conseguir retorna nulo e encerra o progama
  if (dir == NULL)
    encerrarProgama("Não foi possivel abrir a pasta clientes");

   // um while q retorna uma struct dirent q contem o nome do arquivo
  while ((arquivo = readdir(dir)) != NULL) {
   // os 2 primero são sempre "."e ".." então ignora
    if (!strcmp(arquivo->d_name, ".") || !strcmp(arquivo->d_name, ".."))
      continue;

   // pega o diretorio do cliente de acordo com o nome do arquivo 
    getDiretorioCliente(diretorio, arquivo->d_name, false);

    // abre o arquivo do cliente
    FILE *arquivoCliente = abrirArquivo(diretorio, "rt", true);

    //while para cada linha , que são os filmes alugados no momento
    while (fgets(linha, tamanhoLinha - 1, arquivoCliente) != NULL) {
      char **dadosFilmes = strsplit(linha, charDivisor);

      snprintf(filmeAlugado.idFilme, tamanhoIdFilme, "%s", dadosFilmes[FA_id]);
      snprintf(filmeAlugado.titulo, tamanhoTituloFilme, "%s", dadosFilmes[FA_titulo]);
      snprintf(filmeAlugado.autor, tamanhoNomeCliente, "%s",dadosFilmes[FA_autor]);
      snprintf(filmeAlugado.idAutor, tamanhoIdCliente, "%s", dadosFilmes[FA_idAutor]);

      // salva o filme alugado no array
      Filmes.filmes[Filmes.total++] = filmeAlugado;
      //realoca memoria para o total de filmes alugados + 1
      Filmes.filmes = (FilmeAlugado *)realloc(Filmes.filmes, sizeof(FilmeAlugado) * (Filmes.total + 1));
    }

    fclose(arquivoCliente);
  }

  closedir(dir);

  return Filmes;
}


