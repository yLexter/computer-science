#include "utils.h"

// ----------------------------------
// Funções de ordenar os filmes;
// ---------------------------------

//  typedef de uma função q retorna um inteiro e q contenha 2 parametros Filme *
typedef int (*funcaoOrdenarFilme)(Filme *, Filme *);

// Função para ordenar os filmes por ordem crescente
int ordenarFilmesPorTituloCrescente(Filme *f1, Filme *f2)
{
  return strcmp(f1->titulo, f2->titulo);
}

// Função para ordenar os filmes por ordem decerscente
int ordenarFilmesPorTituloDecrescente(Filme *f1, Filme *f2)
{
  return (-1) * strcmp(f1->titulo, f2->titulo);
}

// Função para ordenar os filmes por nota
int ordenarFilmesPorNota(Filme *f1, Filme *f2)
{
  return (f1->nota * 100) - (f2->nota * 100);
}

// Função para ordenar os filmes por duração
int ordenarFilmesPorDuracao(Filme *f1, Filme *f2)
{
  return f1->duracao - f2->duracao;
}

// Função para ordenar os filmes por gênero
int ordenarFilmesPorGenero(Filme *f1, Filme *f2)
{
  return strcmp(f1->genero, f2->genero);
}

// Função para ordenar os filmes pela classificação etária
int ordenarFilmesPorClassificacao(Filme *f1, Filme *f2)
{
  return f1->classificacao - f2->classificacao;
}

// Opções de ordenação
char opcoesOrdenacao[7][50] = {
    "Título (Crescente)",
    "Título (Decrescente)",
    "Duração",
    "Gênero",
    "Nota",
    "Classificação",
    "Nenhuma"};

// Array que armazena as funções para ordenação de filmes
// *A Posição de cada função deve estar de acordo com as opçoes de ordenação
funcaoOrdenarFilme funcoesFilme[6] = {
    ordenarFilmesPorTituloCrescente,
    ordenarFilmesPorTituloDecrescente,
    ordenarFilmesPorDuracao,
    ordenarFilmesPorGenero,
    ordenarFilmesPorNota,
    ordenarFilmesPorClassificacao
};

// Função que ordena a partir de uma opção selecionada usuário
void ordenarFilmes(StructFilmes *filmes, int opcao)
{
  qsort(filmes->filmes, filmes->total, sizeof(Filme), funcoesFilme[opcao]);
}

// ----------------------------------------------------------------------

// Salva uma linha com os dados referente ao filme alugado no diretório
void salvarFilmeAlugado(FILE *arquivo, Client *client, Filme *filme)
{
  fprintf(arquivo, "%s%s%s%s%s%s%s\n", filme->id, charDivisor, filme->titulo,
          charDivisor, client->nome, charDivisor, client->id);
}

// Salva uma linha com os dados referente ao cliente que alugou em algum diretório
// escolhido
void salvarFilmeCliente(Client *client, Filme *filme)
{
  char diretorio[tamanhoArray];
  getDiretorioCliente(diretorio, client->id, true);

  FILE *arquivo = abrirArquivo(diretorio, "a+", true);

  salvarFilmeAlugado(arquivo, client, filme);
  fclose(arquivo);
}

// Formata a duração do filme, ex: 3600s = 1h0m
char *getDuracaoFormatada(int duracaoEmSegundos)
{
  int totalCaracteres = 8;
  char *info = (char *)malloc(sizeof(char) * totalCaracteres);
  int horas = duracaoEmSegundos / 3600;
  int minutos = (duracaoEmSegundos % 3600) / 60;

  snprintf(info, totalCaracteres, "%dh%dm", horas, minutos);

  return info;
}

// Formata a classificação do filme, Ex 18 = 18+ // 0 = L
char *getClassificacaoFormatada(int classificacao)
{
  int totalCaracteres = 4;
  char *info = (char *)malloc(sizeof(char) * totalCaracteres);

  classificacao ? snprintf(info, totalCaracteres, "%d+", classificacao) : snprintf(info, totalCaracteres, "L");

  return info;
}

// Função para mostrar formatado as informaçõs do filme
void imprimirFilme(Filme filme)
{
  char *duracaoFormatada = getDuracaoFormatada(filme.duracao);
  char *classificacaoFormatada = getClassificacaoFormatada(filme.classificacao);

  printf("Titúlo: %s\n", filme.titulo);
  printf("Duração: %s\n", duracaoFormatada);
  printf("Nota: %.2f/%.2f\n", filme.nota, maiorNotaFilme);
  printf("Genêro: %s\n", filme.genero);
  printf("Classificação: %s\n", classificacaoFormatada);

  // Libera a memoria das variavel
  free(classificacaoFormatada);
  free(duracaoFormatada);
}

// Valida a opção que o usuário digitou para que as ordenações aconteçam e mostra os filmes
StructFilmes mostrarFilmes(bool ordenar)
{
  // total de ordenações presente, a ultima sempre sera nenhuma
  int totalOrdenacoes = sizeof(opcoesOrdenacao) / sizeof(opcoesOrdenacao[0]);
  int opcaoSelecionada;

  StructFilmes filmes = getFilmes();

  // se nao tiver filmes cadastrados retorna um erro
  if (!filmes.total)
  {
    msgEntreCabecalho("Não existe filmes cadastrados no momento.", true, false);
    return filmes;
  }

  // se quiser mostrar as opções de ordenação
  if (ordenar)
  {
    cabecalho();
    textoCentralizado("Opções de Ordenação");

    // printa as opções de ordenação dos filmes
    for (int x = 0; x < totalOrdenacoes; x++)
      printf("[%d] %s\n", x + 1, opcoesOrdenacao[x]);

    msgEntreCabecalho("Ordenar por: ", true, false);
    scanf("%d", &opcaoSelecionada);
    limparTela();

    // casoa opção seja invalida, seta a opção para ultima (nenhuma)
    if (opcaoSelecionada < inicioOpcoes || opcaoSelecionada > totalOrdenacoes)
      opcaoSelecionada = totalOrdenacoes;

    // caso opção não seja nenhuma, ordena pelo oque foi selecionado
    if (opcaoSelecionada != totalOrdenacoes)
      ordenarFilmes(&filmes, opcaoSelecionada - 1);
  }

  cabecalho();
  textoCentralizado("Todos os Filmes");

  // printa todos os filmes cadastrados
  for (int x = 0; x < filmes.total; x++)
  {
    printf("%d.\n", x + 1);
    imprimirFilme(filmes.filmes[x]);
    quebraLinha(1);
  }

  return filmes;
}

// Mostra os filmes e retorna o filme escolhido pelo usuário
Filme *getFilmePorUsuario()
{
  int indiceFilme;
  StructFilmes Filmes = mostrarFilmes(true);

  // se não tiver filmes retorna nulo
  if (!Filmes.total)
    return NULL;

  msgEntreCabecalho("Digite o indíce do filme", true, false);
  scanf("%d", &indiceFilme);
  limparBuffer();
  limparTela();

  // caso seja uma oção invalida retorna um erro e nulo
  if (indiceFilme < inicioOpcoes || indiceFilme > Filmes.total)
  {
    msgEntreCabecalho("A opção escolhida é invalida", true, false);
    return NULL;
  }

  return &Filmes.filmes[indiceFilme - 1];
}

// Verifica se um filme está alugado
bool verificarFilmeAlugado(Filme *filme)
{
  StructFA Filmes = getFilmesAlugados();

  // for q pecorre todos os filmes alugados existente e verifica
  // se o que o usuário escolheu está alugado
  for (int x = 0; x < Filmes.total; x++)
  {
    if (!strcmp(Filmes.filmes[x].idFilme, filme->id))
      return true;
  }

  return false;
}

// Printa informações formatadas dos filmes alugados
void imprimirFilmeAlugado(FilmeAlugado filme)
{
  printf("Autor: %s (%s)\n", filme.autor, filme.idAutor);
  printf("Titulo: %s\n", filme.titulo);
  printf("ID: %s\n", filme.idFilme);
}

// Mostra os filmes alugados
StructFA mostrarFilmesAlugados()
{
  StructFA Filmes = getFilmesAlugados();

  // se n tiver filmes alugado printa um erro
  if (!Filmes.total)
  {
    msgEntreCabecalho("Não existe filmes alugado no momento", true, false);
    return Filmes;
  }

  cabecalho();
  textoCentralizado("Filmes Alugados");

  // Printa todos os filmes alugados no momento.
  for (int x = 0; x < Filmes.total; x++)
  {
    printf("%d.\n", x + 1);
    imprimirFilmeAlugado(Filmes.filmes[x]);
    quebraLinha(1);
  }

  return Filmes;
}

// Gera um id para o filme.
void gerarIdFilme(char *varId)
{
  gerarId(varId, prefixoIdFilme, tamanhoIdFilme);

  StructFilmes Filmes = getFilmes();

  // Verifica se o id gerado já esta em uso caso esteja gera outro recurssivamente
  for (int x = 0; x < Filmes.total; x++)
  {
    if (!strcmp(Filmes.filmes[x].id, varId))
      return gerarIdFilme(varId);
  }
}

// Registra o log do filme alugado nos arquivos de populares
void salvarLog(Client *cliente, Filme *filme)
{
  FILE *arquivo = abrirArquivo(diretorioPopulares, "a+", true);
  salvarFilmeAlugado(arquivo, cliente, filme);
  fclose(arquivo);
}

// Funçaõ para colocar numa lista encadeada toda vez que um filme for ser alugado
void inserirListaPopulares(ListaPopulares *raiz, char **dados)
{

  // Copía para não alterar a raiz
  ListaPopulares *node = raiz;

  while (true)
  {

    // caso o filme ja esteja na lista soma o total +1
    if (!strcmp(dados[FA_id], node->id))
    {
      node->total = node->total + 1;
      break;
    }

    // caso entre no if significa q é o ultimo filme da lista e adiciona na lista encadeada
    if (node->prox == NULL)
    {
      node->prox = (ListaPopulares *)malloc(sizeof(ListaPopulares));
      node = node->prox;
      snprintf(node->titulo, tamanhoTituloFilme, "%s", dados[FA_titulo]);
      snprintf(node->id, tamanhoIdFilme, "%s", dados[FA_id]);
      node->total = 1;
      node->prox = NULL;
      break;
    }

    node = node->prox;
  }
}

// Retorna a lista encadeada
ListaPopulares *getListaPopulares()
{
  char linha[tamanhoLinha];
  ListaPopulares *raiz = NULL;

  FILE *arquivo = abrirArquivo(diretorioPopulares, "rt", true);

  // Laço para pegar todos os filmes populares
  while (fgets(linha, tamanhoLinha - 1, arquivo) > 0)
  {
    char **dados = strsplit(linha, charDivisor);

    // se a raiz for nula, ou seja, não tenha nada na lista cadastra o primeiro elemento
    // caso não adiciona na lista ou soma ao total caso esteja
    if (raiz == NULL)
    {
      raiz = (ListaPopulares *)malloc(sizeof(ListaPopulares));
      snprintf(raiz->titulo, tamanhoTituloFilme, "%s", dados[FA_titulo]);
      snprintf(raiz->id, tamanhoIdFilme, "%s", dados[FA_id]);
      raiz->total = 1;
      raiz->prox = NULL;
    }
    else
    {
      inserirListaPopulares(raiz, dados);
    }
  }

  fclose(arquivo);

  return raiz;
}

// Printa informações dos filmes populares
void imprimirFilmePopular(ListaPopulares *filme)
{
  printf("Titulo: %s\n", filme->titulo);
  printf("ID: %s\n", filme->id);
  printf("Total de Alugações: %d\n", filme->total);
}

// Libera a memória da lista encadeada de filmes populares
void limparLista(ListaPopulares *raiz)
{
  while (raiz->prox != NULL)
  {
    ListaPopulares *temp = raiz;
    raiz = raiz->prox;
    free(temp);
  }
}

// Formata e mostra os filmes populares
void mostrarPopulares()
{
  int count = 0;

  // pega a raiz da lista encadeada
  ListaPopulares *raiz, *node = getListaPopulares();

  // caso esteja vazia siginifica que nao existe nenhum filme alugado na lista de populares
  if (node == NULL)
    return msgEntreCabecalho("Sem filmes registrados no momento", true, false);

  // faz uma copia da raiz para variavel raiz
  raiz = node;

  cabecalho();
  textoCentralizado("Filmes Mais Populares");

  // printa a lista encadeada dos filmes populares
  while (node != NULL)
  {
    printf("%d.\n", ++count);
    imprimirFilmePopular(node);
    quebraLinha(1);

    node = node->prox;
  }

  // libera a memoria da lista encadeada
  limparLista(raiz);
}

// Valida a classificação do filme
bool classificacaoValida(int classificacao)
{

  int classificacoes[6] = {0, 10, 12, 14, 16, 18};
  int totalClassificacoes = sizeof(classificacoes) / sizeof(classificacoes[0]);

  // verifica se a classificacao escolhida
  // esta no array de classificacao, se estiver retorna true
  for (int x = 0; x < totalClassificacoes; x++)
  {
    if (classificacoes[x] == classificacao)
      return true;
  }

  return false;
}

// Cadastra o filme no arquivo de filmes
void cadastrarFilmeArquivo(Filme filme)
{
  FILE *arquivoFilmes = abrirArquivo(diretorioFilmes, "a+", true);

  // formata o nome  deixando cada letra inicial maiuscula
  formatarNome(filme.titulo);
  formatarNome(filme.genero);

  // cadastra o filme
  fprintf(arquivoFilmes, "%s%s%s%s%d%s%.2f%s%s%s%d\n", filme.id, charDivisor,
          filme.titulo, charDivisor, filme.duracao, charDivisor, filme.nota,
          charDivisor, filme.genero, charDivisor, filme.classificacao);

  fclose(arquivoFilmes);
}

// valida a duração fornecida pelo o usuario
int duracaoValidaFilme(char *duracao)
{

  // Deixa tudo em minusculo
  toLowerCase(duracao);

  // Para contar quantos "h" há na string
  int count = 0;
  int len = strlen(duracao);

  // Se não tiver o "h" ou so tiver uma letra retorna false
  if (!strstr(duracao, "h") || len == 1)
    return false;

  // se o "h" estiver na primeira posição ou na ultima retorna falso
  if (duracao[0] == 'h' || duracao[len - 1] == 'h')
    return false;

  while (*duracao != '\0')
  {
    // se tiver mais de um h retorna falso
    if (count > 1)
      return false;

    if (*duracao == 'h')
      count++;

    // se for um digito diferente de h retorna falso
    if (!isdigit(*duracao) && *duracao != 'h')
      return false;

    duracao++;
  }

  return true;
}

// Exclui o filme do arquivo do cliente que alugou
void devolverFilmeAlugado(FilmeAlugado filme)
{
  char diretorioCliente[tamanhoArray];

  // pega o diretorio do cliente e remove a linha que contem o id ao filme
  getDiretorioCliente(diretorioCliente, filme.idAutor, true);
  deletarLinhaArquivo(diretorioCliente, filme.idFilme);
}

// Deleta um filme dos arquivos de filmes
void deletarFilmeArquivo(Filme *filme)
{
  deletarLinhaArquivo(diretorioFilmes, filme->id);
}
