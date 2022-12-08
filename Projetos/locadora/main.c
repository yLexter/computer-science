#include "clientes.h"

// Função para alugar um filme
void alugarFilme()
{

  // Pega um cliente escolhido pelo usuário
  Client *clientEscolhido = getClientePorUsuario();

  // caso a opção seja invalida retorna nulo e uma mensagem de erro
  if (clientEscolhido == NULL)
    return;

  Filme *filmeEscolhido = getFilmePorUsuario();

  // caso a opção seja invalida retorna nulo e uma mensagem de erro
  if (filmeEscolhido == NULL)
    return;

  // caso o filme esteja alugado retorna true
  if (verificarFilmeAlugado(filmeEscolhido))
    return msgEntreCabecalho("Este filme já esta alugado.", true, false);

  // Verifica se a idade do cliente é apropiada pro filme
  if (clientEscolhido->idade < filmeEscolhido->classificacao)
    return msgEntreCabecalho("Este Filme não é apropiado para este cliente", true, false);

  // salva o filme na lista de filmes alugados do cliente
  salvarFilmeCliente(clientEscolhido, filmeEscolhido);
  // salva o registro de alugação para a lista de mais populares
  salvarLog(clientEscolhido, filmeEscolhido);

  msgEntreCabecalho("Filme alugado com sucesso.", true, false);
}

// Cadastra um cliente
void cadastroCliente()
{
  Client client;

  cabecalho();
  textoCentralizado("Tela de Cadastro");
  printf("Digite seu nome: \n");
  scanf("%[^\n]", client.nome);

  quebraLinha(1);

  printf("Digite a sua idade \n");
  scanf("%d", &client.idade);
  limparTela();

  // gera umd id aleatorio
  gerarIdCliente(client.id);
  // formata o nome para deixar cada letra inicial maiuscula
  formatarNome(client.nome);

  bool cadastradoComSucesso = cadastrarCliente(client);

  // caso o cadastro seja realizado com sucesso retorna true
  if (cadastradoComSucesso)
    msgEntreCabecalho("Cadastro realizado com sucesso", true, false);
}

// Remove um cliente
void removerCliente()
{

  // Pega um cliente escolhido pelo usuário
  Client *clientRemovido = getClientePorUsuario();

  // caso o cliente escolhido seja invalido retorna nulo e uma mensagem de erro
  if (clientRemovido == NULL)
    return;

  deletarClienteArquivo(clientRemovido);

  cabecalho();
  printf("O cliente %s (%s) foi removido.\n", clientRemovido->nome, clientRemovido->id);
}

// Delete um filme e todos os seus dados escolhido pelo usuário
void deletarFilme()
{
  // Pega um filme escolhido pelo usuário
  Filme *filmeDeletado = getFilmePorUsuario();

  // caso a opção ecolhida seja invalida retorna null com uma mensagem de
  // erro
  if (filmeDeletado == NULL)
    return;

  // deleta o filme do banco de dados
  deletarFilmeArquivo(filmeDeletado);

  msgEntreCabecalho("Filme deletado com sucesso", true, false);
}

// Cadastra filme
void cadastrarFilme()
{
  Filme filme;
  char stringDuracao[tamanhoArray];
  char **arrayDuracao;

  cabecalho();
  quebraLinha(1);
  textoCentralizado("Cadastrando Filme");
  cabecalho();

  printf("Informe o nome do filme. (Max: %d caracteres) \n", tamanhoTituloFilme);
  scanf("%[^\n]", filme.titulo);
  limparBuffer();
  quebraLinha(1);

  printf("Informe a duração do filme: (Ex: 2h0 = 2 Horas)\n");
  scanf("%s", stringDuracao);
  limparBuffer();
  quebraLinha(1);

  // verifica se a duração informada é valida
  if (!duracaoValidaFilme(stringDuracao))
  {
    limparTela();
    return msgEntreCabecalho("A duração informa é invalida", true, false);
  }

  printf("Informe a classificação do filme: 0(L),10,12,14,16 e 18\n");
  scanf("%d", &filme.classificacao);
  limparBuffer();
  quebraLinha(1);

  // caso a classificacao seja invalida retorna um erro
  if (!classificacaoValida(filme.classificacao))
  {
    limparTela();
    return msgEntreCabecalho("Classificacão inválida", true, false);
  }

  printf("Informe a nota do filme: (Menor Nota: %d, Maior Nota: %.2f)\n", menorNotaFilme, maiorNotaFilme);
  scanf("%f", &filme.nota);
  limparBuffer();
  quebraLinha(1);

  // caso a nota nao seja entre as 2 variavel definiida (maiorNota filme
  // e menorNotaFilme) retorna um erro
  if (filme.nota > maiorNotaFilme || filme.nota < menorNotaFilme)
  {
    limparTela();
    cabecalho();
    printf("A Nota só pode ser entre %d e %.2f\n", menorNotaFilme, maiorNotaFilme);
    return;
  }

  printf("Informe o gênero do filme: \n");
  scanf("%[^\n]", filme.genero);
  limparBuffer();
  limparTela();

  // separa a horas dos minutos e converte tudo para segundos
  arrayDuracao = strsplit(stringDuracao, "h");
  filme.duracao = (3600 * atoi(arrayDuracao[0])) + (60 * atoi(arrayDuracao[1]));

  gerarIdFilme(filme.id);
  cadastrarFilmeArquivo(filme);

  msgEntreCabecalho("Filme cadastrado com sucesso", true, false);
}

// Função para devolver um filme
void devolverFilme()
{
  int indiceFilme;

  StructFA filmesAlugados = mostrarFilmesAlugados();

  // se o array estiver vazio apenas retorna printando a mensagem de erro
  if (!filmesAlugados.total)
    return;

  msgEntreCabecalho("Digite o indice do filme", true, false);
  scanf("%d", &indiceFilme);
  limparTela();

  // caso a opcao seja menor q 1 ou maior q o total a opção é invalida
  if (inicioOpcoes > indiceFilme || indiceFilme > filmesAlugados.total)
    return msgEntreCabecalho("Opcao invalida", true, false);

  FilmeAlugado filmeDevolvido = filmesAlugados.filmes[indiceFilme - 1];

  // Exclui do banco de dados do cliente o filme devolvido
  devolverFilmeAlugado(filmeDevolvido);

  msgEntreCabecalho("Filme devolvido com sucesso", true, false);
}

// Menu inicial, chama a função referida pela opção que o usuário digirar
void menuInicial()
{

  // a ultima opção é a de saida sempre, aqui ele pega o tamanho
  // de opções existente sempre a de saida sendo o ultimo
  const int ultimaOpcao = sizeof(opcoesMenu) / sizeof(opcoesMenu[0]);
  int opcaoSelecionada;

  cabecalho();
  textoCentralizado("Menu Inicial");

  // Printa o menu inicial
  for (int x = 0; x < ultimaOpcao; x++)
    printf("[%d] %s\n", x + 1, opcoesMenu[x]);

  msgEntreCabecalho("Digite a opção que deseja: ", true, false);
  scanf("%d", &opcaoSelecionada);
  limparBuffer();
  limparTela();

  // switch para saber qual opção o usuario escolheu
  switch (opcaoSelecionada)
  {
  case 1:
    mostrarFilmes(true);
    break;
  case 2:
    alugarFilme();
    break;
  case 3:
    cadastroCliente();
    break;
  case 4:
    mostrarClientes(true);
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
  case 8:
    cadastrarFilme();
    break;
  case 9:
    devolverFilme();
    break;
  case 10:
    mostrarPopulares();
    break;
  case ultimaOpcao:
    encerrarProgama("Progama encerrado com sucesso! Volte Sempre.");
    break;
  default:
    msgEntreCabecalho("A opção escolhida é invalida.", true, false);
    break;
  }
}

// Mensagem de boas vindas
void telaInicial()
{

  srand(time(NULL));

  int tempo = 6;

  // um for que incrementa numeros aleatorio para simular
  // uma tela de loading
  for (int x = 0; x < 100; x += (rand() % 25) + 10)
  {
    cabecalho();
    textoCentralizado("Locadora Arcade 8\n");
    printf("- Seja Bem Vindo ao Nosso Sistema.\n\n");
    cabecalho();
    printf("Carregando Sistema: %d%\n\n", x);
    sleep(tempo / 4);
    limparTela();
  }
}

// função main q fica chamando o menu inicial "infinitamente"
int main()
{

  telaInicial();

  while (true)
    menuInicial();

  return 0;
}
