module entidades.Uno;

import std.stdio;
import std.conv;
import std.range;
import std.string;
import std.random : uniform, uniform01;
import std.exception;

import entidades.Carta;
import entidades.Jogador;
import entidades.Baralho;
import entidades.RegrasUno;
import entidades.Utils;

import core.thread;

export class Uno
{
  public static int MAXIMO_JOGADORES = 4; ///< Número máximo de jogadores permitidos no jogo Uno.
  public static int NUMERO_CARTAS_JOGADOR = 7; ///< Número de cartas que cada jogador recebe no início do jogo.

  private Baralho baralho; ///< O baralho de cartas usado no jogo Uno.
  private ListaJogadores jogadores; ///< A lista de jogadores participantes do jogo Uno.
  private Stack!(Carta) cartasUsadas; ///< A pilha de descarte de cartas usadas no jogo Uno.
  private bool jogoEncerrado; ///< Indica se o jogo Uno foi encerrado.
  private bool sentidoInvertidoRotacao; ///< Indica a direção da ordem de turnos no jogo Uno.
  private RegrasUno regrasUno; ///< As regras do jogo Uno.
  private int totalJogadas; ///< O número total de jogadas feitas no jogo Uno.
  private Jogador vencedor; ///< O jogador que venceu o jogo Uno.
  private string nomeJogador; ///< O nome do jogador real que inicia o jogo Uno.

  /**
   * Construtor para o jogo Uno.
   * @param regrasUno As regras do jogo Uno.
   */
  public this(RegrasUno regrasUno)
  {
    this.baralho = new Baralho();
    this.cartasUsadas = new Stack!(Carta)();
    this.jogadores = new ListaJogadores();
    this.sentidoInvertidoRotacao = false;
    this.jogoEncerrado = false;
    this.regrasUno = regrasUno;
    this.totalJogadas = 0;
  }

  /**
   * Método para exibir a tela inicial do jogo.
   */
  public void telaInicial()
  {

    int tempo = 4;
    write("\x1b[31m");

    writeln("\n--------------------------------------------------------------------------------");
    writeln("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
    writeln("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyso+++osyhdhhhhhhhhh");
    writeln("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyo:.......```-/shhhhhhhh");
    writeln("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhy+..````.....````.:shhhhhh");
    writeln("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhy:````-/osysso+:.```.+hhhhh");
    writeln("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyso/-.shhhhh:````/yddmddhhys+-..../hhhh");
    writeln("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhys:....-yhhds-```-ydhhhhddmhyho-....shhh");
    writeln("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyyhhhhhhhdhs+-````/hddo.  `-hhhhhhhhhmmdh/----:hhh");
    writeln("hhhhhhhhhhhhhhhhhhhhhhhhhhys+:-.-+shhhhdmys+.````+dds- ``.shhhhhhhhhmmd/::::-hhh");
    writeln("hhhhhhhhhhhhhhhhhhhyso/:shs/-``````-+shhdmhy/.  `.hhy/```.-yhhhhhhhhdmh-::::/hhh");
    writeln("hhhhhhhhhhhhhhhhhhy/-...-hso:`````````-+sdmhh/````-hs+:.----oyhhhhhhhy::::::yhhh");
    writeln("hhhhhhhhyyhhhhhhhdso:````:hs+:````-.`   `-oyhy:..../so+/::::-:/oooo/::::::/shhhh");
    writeln("hhhhys+:--yhhhhhhmhs+-````oys+-`  `:/:.````.-+/:----os+++/::::::::::::::/oyhhhhh");
    writeln("hhhhs:```./hhhhhhdmys/.```.yys/.```.+so+/-------::::-yhssso++///:::::/+shhhhhhhh");
    writeln("hhdhy+-```.+hhhhhhdmys:````-hyy/....-yyysso/::::::::::dddhyyyyyssssyhdddhhhhhhhh");
    writeln("hhdmhy/-```-shhhhhhddys:``..:dys:----:dddhhyyo+/::::::/hddddddmdmmmmddhhhhhhhhhh");
    writeln("hhhdmys:.```-yhhhhhhmdhs:..-.odho::::-+hdmmdddhyo+/////shhhhdddddhhhhhhhhhhhhhhh");
    writeln("hhhhddyo:.```:hhhhhhdmdd+:-:-.mhh+::::-ohhddmmdddhysyyhhhhhhhhhhhhhhhhhhhhhhhhhh");
    writeln("hhhhhmhy+:....+hhhhhhdmd+::::.hmdh/::::-yhhhhddmmdddddhhhhhhhhhhhhhhhhhhhhhhhhhh");
    writeln("hhhhhdmhy+:---.+yhhhhhds-:::::hdmdh/:/++ohhhhhhhddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
    writeln("hhhhhhdms++:-----/+o+/:-:::::shhmddyyhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
    writeln("hhhhhhhddoo+/::::----:::::/+yhhhdmdmmddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
    writeln("hhhhhhhhddysso//:::::://+shhhhhhhddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
    writeln("hhhhhhhhhdmdddhhyyyyyhhdmdhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
    writeln("hhhhhhhhhhhdhhhhhhhhhhddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
    writeln("hhhhhhhhhhhhhhhdddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
    writeln("--------------------------------------------------------------------------------");

    write("\x1b[0m");

    writeln();
    writeln();

    writeln("O jogo ira comecar em ", tempo, " segundos...");

    Decoracao.aMimir(tempo);

    Decoracao.limparTela();
  }

  /**
   * Método para gerar jogadores para o jogo Uno.
   */
  public void gerarJogadores()
  {

    Decoracao.mensagemRetangulo("Criacao de Jogadores");

    writef("Digite o seu nome: \n");
    readf("%s\n", &nomeJogador);

     int numeroJogadores = 0;
     string input;

     while (numeroJogadores > MAXIMO_JOGADORES - 1 || numeroJogadores <= 0) {
        write("Digite o numero de bots: ");
        input = readln();
        input = input.replace("\n", "");
        numeroJogadores = to!int(input);
     }


    jogadores.add(new JogadorReal(nomeJogador));

    for (int i = 0; i < numeroJogadores; i++)
    {
      jogadores.add(new Bot("Burrinho Artificial " ~ to!string(i + 1)));
    }

    Decoracao.limparTela();
  }

  /**
   * Método para distribuir as cartas na primeira rodada para os jogadores.
   */
  public void distribuirCartaJogadores()
  {

    foreach (ref jogador; jogadores.toArray())
    {
      this.baralho.distribuirCartaJogador(jogador, NUMERO_CARTAS_JOGADOR);
    }

  }

  /**
   * Método para mostrar a lista de jogadores da partida.
   */
  public void mostrarJogadores()
  {

    Decoracao.mensagemRetangulo("Lista de Jogadores");

    auto jogadoresLista = jogadores.toArray();

    foreach (i, jogador; jogadoresLista)
    {
      writeln(++i, ". ", jogador.toString(), " -- ", NUMERO_CARTAS_JOGADOR, " Cartas");
    }
    

    Decoracao.aMimir(4);
    Decoracao.limparTela();
  }

   /**
   * Método para adicionar a primeira carta na pilha de descarte e, assim, começar o jogo.
   */
  public void adicionarPrimeiraCartaPilhaDescarte()
  {

    Carta[] cartas = this.baralho.getCartasBaralho().toArray();

    for (int x = 0; x < cartas.length; x++)
    {
      Carta carta = cartas[x];

      if (carta.getNome() != "Joker" && carta.getNome() != "JokerMais4")
      {
        Carta removida = this.baralho.getCartasBaralho().remove(x);
        cartasUsadas.push(removida);
        break;
      }
    }
  }

  /**
   * Método para iniciar o jogo, para gerar um fluxo de jogadas e mostrar ao usuário a dinâmica do jogo.
   */
  public void comecarJogo()
  {
    Carta carta;
    Jogador jogadorVez;
    bool possuiCartaValida;
    int totalCartasJogador;
    Carta cartaTopoPilha;

    Decoracao.mensagemRetangulo("Comeco do Jogo");

    while (!jogoEncerrado)
    {
      bool sentidoRotacao = jogadores.getSentidoRotacao();
      jogadorVez = jogadores.getJogadorVez();
      totalCartasJogador = jogadorVez.getMaoCartas().length;
      cartaTopoPilha = cartasUsadas.getLast();
      possuiCartaValida = false;

      if (jogadorVez.isUno())
      {
        writeln();
        write("\x1b[32m");
        writeln("UNO!");
        write("\x1b[0m");
        writeln();
      }

      for (int x = 0; x < totalCartasJogador; x++)
      {
        Carta cartaAtual = jogadorVez.getMaoCartas().get(x);

        try
        {
          this.testarCartaValida(cartaAtual);
          possuiCartaValida = true;
          break;
        }
        catch (JogadaInvalidaException error)
        {
        }
      }

      if (!possuiCartaValida)
      {
        Carta cartaComprada = baralho.getCartasBaralho().pop();

        writeln();
        writeln(jogadorVez.toString(), "Nao possui cartas valida, indo comprar...");

        try
        {
          regrasUno.jogarCarta(cartaComprada);
          writeln();
          writeln(jogadorVez.getNome() ~ " comprou e jogou ", cartaComprada.toString());
          cartasUsadas.push(cartaComprada);
        }
        catch (JogadaInvalidaException)
        {
          writeln();
          writeln(jogadorVez.toString(), " Nao possui carta valida, mesmo comprando, indo pro proximo....");
          jogadorVez.adicionarCarta(cartaComprada);
          jogadores.mudarVezJogador();
          writeln();
        }

        Decoracao.aMimir(2);

        continue;
      }

      writeln();
      writeln("-".replicate(50));
      Decoracao.printSimetrico("Ultima carta jogada:", cartaTopoPilha.toString());
      Decoracao.printSimetrico("Vez do Jogador:", jogadorVez.toString());
      Decoracao.printSimetrico("Sentido Rotacao:", sentidoRotacao ? "Invertido" : "Normal");
      writeln("-".replicate(50));
      writeln();

      Decoracao.aMimir(1);

      carta = jogadorVez.jogar(this);

      regrasUno.jogarCarta(carta);
      jogadorVez.removerCarta(carta);
      cartasUsadas.push(carta);

      bool jogoFinalizado = regrasUno.verficiarSeJogoEstaFinalizado(jogadorVez);

      if (jogoFinalizado)
      {
        vencedor = jogadorVez;
        jogoEncerrado = true;
        continue;
      }

      if (baralho.getCartasBaralho().length == 0)
      {
        reporCartas();
      }

    }
  }

  /**
   * Método para repor as cartas do baralho após esgotarem.
   */
  public void reporCartas()
  {
    auto cartas = baralho.getCartasBaralho();

    while (!cartasUsadas.isEmpty())
    {
      cartas.add(
        cartasUsadas.pop()
      );
    }

    cartas.shuffle();
  }

  /**
   * Verifica se a carta jogada é válida de acordo com as regras do jogo Uno.
   * 
   * @param carta A carta jogada
   * @throws JogadaInvalidaException Se a jogada não for válida
   */
  public void testarCartaValida(Carta carta)
  {
    Carta cartaTopoPilha = this.getCartasUsadas().getLast();

    string nomeCartaTopoPilha = cartaTopoPilha.getNome();
    string corCartaTopoPilha = cartaTopoPilha.getCor();

    string nomeCarta = carta.getNome();
    string corCarta = carta.getCor();

    if (
      (nomeCartaTopoPilha == "Joker" || nomeCartaTopoPilha == "JokerMais4") &&
      (nomeCarta == "Joker" || nomeCarta == "JokerMais4")
      )
    {
      throw new JogadaInvalidaException("Não é possiel jogar 2 jokes seguidos");
    }

    if ((nomeCartaTopoPilha == "Joker" || corCartaTopoPilha == "JokerMais4") && corCarta != corCartaTopoPilha)
    {
      throw new JogadaInvalidaException("A cor tem que ser igual a selecionada pelo o player");
    }

    if (
      nomeCarta != "Joker" &&
      nomeCarta != "JokerMais4" &&
      nomeCarta != nomeCartaTopoPilha &&
      corCarta != corCartaTopoPilha
      )
    {
      throw new JogadaInvalidaException("Jogada Invalida!!!");
    }
  }

  /**
   * Exibe o vencedor do jogo com algumas mensagens personalizadas.
   */
  public void mostrarVencedor()
  {
    writeln();
    write("\x1b[34m");
    writeln(vencedor, " Venceu o jogo!!!");
    write("\x1b[0m");

    Decoracao.aMimir(2);

    writeln();
    writeln();

    writeln(".........        .. .......         ");
    writeln(
      "                                                                 ....:-----:..........------..         ");
    writeln(
      "                                                                .:----::::----:..:-----::::--..        ");
    writeln(
      "                                                              ..:--::::::::::------::::::::--:.        ");
    writeln(
      "                                                              ..:-:::::::::::::--:::::::::::-:.        ");
    writeln(
      "                                             .......          ..:-:::::::::::::::::::::::::--..        ");
    writeln(
      "                                        ......:---:.....      ..:--::::::--:::::::::::---::--..        ");
    writeln(
      "                                       ......:-:::-:....      ...---::::--::::::-::-::--::--...        ");
    writeln(
      "                                      ..:--::::::::----:..      ..---::::::--::------::::--...         ");
    writeln(
      " ...                                  ..:-::::::::::::-:..       ...---:::::--------:::---..  .        ");
    writeln(
      " ..                                   ...:-:::---:::::-:..         ..:--::::::::::::::--:...           ");
    writeln(
      "                 ..                     .:-:--=====-::-:.            ..:---:::::::::---:..             ");
    writeln(
      "                                        ..:-==----==-:::.            ....:--:::::::--:....             ");
    writeln(
      "                                        ..:-::::::-=-....              ....:--------:...               ");
    writeln(
      "                             .=##=..   ..-=-:::::-::=-..                    ....:---:..                ");
    writeln(
      "                        ...=%@@@@@@@=...-=-:-:::::::-=-....-+**+-..            ...:-:..              ");
    writeln(
      "                        .-@@@@#:=@@@@@===-::::::::-::--=@@@@@@@@@@@#..               .:-:.             ");
    writeln(
      "                      .-@@@@=... ..#@%==----------::::-=#@@=:.  :%@@@..               .:-:.            ");
    writeln(
      "                    ..*@@@+..     ..:==--::::::::::::--==.       :#@@@..               .::..           ");
    writeln(
      "                    :%@@@-.         ..-==-::::::::----==-.        -@@@=.               ..-:.           ");
    writeln(
      "                   .=@@%:.           .---::-=========::...        .+@@%:                .-:.           ");
    writeln(
      "             :#@%-.......           .:--..:--:..---..              :%@@= .......        .-:.           ");
    writeln(
      "             :#@@@@-                .--:..--:..:--:.               ..==.:+#@@@%:.      .::.            ");
    writeln(
      "            .==-==-.                .:-:..---...-:..                   .%@@@@@%-.     .:-:.            ");
    writeln(
      "            +@@@@%-                      ... .                         .........     .:-:.             ");
    writeln(
      "            .=%@@@=                                                    .-@@@@@@=.   ..-:.              ");
    writeln(
      "             .....                                                     .:%@@#+=..  ..--..              ");
    writeln(
      "               ..............                                                    ..:-:..               ");
    writeln(
      "               ..:::::..*@@@+    ..--:..:==-..       ...  .-=-..      ...      ..:--:.                 ");
    writeln(
      "               ..:::::..+@@@=  ...%@@@+%@@@@@+:..=%%=.....#@@@%.     .......  ..:-:..                  ");
    writeln(
      "               ...%@@#.       .....%@@@@@%#@@@@@@@@@.::...#@@.     ::::::...:-:...                   ");
    writeln(
      "                 .=@@@@-..   ..::....:@@@-:--@@@@#*:.:::::.....     .:---:..:-:..                      ");
    writeln(
      "                  .:#@@@%-... ....   :@@@*:::#@@@..::::::::.      ..-%@@%:--:..                        ");
    writeln(
      "                   ..:@@@@@@%=--:.   .=@@*:::%@@+..::::.....    .-+@@@@%--:...                         ");
    writeln(
      "            ..      .#@@@@@@@@@@@*.   =@@@=::%@@=.........:+%@@@@@@@@@=--:.                            ");
    writeln(
      "                    .%@@%. .......    .@@@#:=@@@=...:::..:@@@@@@%*#@@%-:. .                            ");
    writeln(
      "                    .:@@@%-.  ....    .-@@%-#@@%...:::............:@@+-..                              ");
    writeln(
      "                     ..%@@@+...::::..  .@@@@@@@-   ....      ..:+%@@@-:.                               ");
    writeln(
      "                     ..#@@@#.::::::..  .:#@@@@=.             .#@@@@@=-:.                               ");
    writeln(
      "                     .*@@@=..:::::...   ...::...            .:@@@=...-:.                               ");
    writeln(
      "                  .:#@@@@+..::::..    .:....                .+@@@:  .:-..   ..:---:.                   ");
    writeln(
      "                 .=@@@@@#.........    .---:.                :@@@+    .:-:.. .-:..:-.                   ");

    writeln();
  }

  
  /**
   * Método principal para iniciar o jogo Uno.
   */
  public void main()
  {
    this.baralho.gerarCartas();
    this.baralho.embaralharCartas();

    this.gerarJogadores();
    this.mostrarJogadores();
    this.distribuirCartaJogadores();

    this.jogadores.sortearJogadorVez();

    this.adicionarPrimeiraCartaPilhaDescarte();

    this.comecarJogo();
    this.mostrarVencedor();
  }

  // -----------------------------------------------------------------------------------------------------------
  // Get e Setters

  /**
   * Obtém o jogador da vez.
   * 
   * @returns O jogador da vez
   */
  public Jogador getJogadorVez()
  {
    return this.jogadores.getJogadorVez();
  }

  /**
   * Obtém o baralho do jogo.
   * 
   * @returns O baralho do jogo
   */
  public Baralho getBaralho()
  {
    return baralho;
  }

  /**
   * Define um novo baralho para o jogo.
   * 
   * @param novoBaralho O novo baralho a ser definido
   */
  public void setBaralho(Baralho novoBaralho)
  {
    baralho = novoBaralho;
  }

  /**
   * Obtém a lista de jogadores participantes do jogo.
   * 
   * @returns A lista de jogadores participantes
   */
  public ListaJogadores getJogadores()
  {
    return this.jogadores;
  }

  /**
   * Obtém as cartas já jogadas.
   * 
   * @returns As cartas já jogadas
   */
  public Stack!Carta getCartasUsadas()
  {
    return cartasUsadas;
  }

  /**
   * Define as cartas já jogadas.
   * 
   * @param novasCartasUsadas As novas cartas já jogadas
   */
  public void setCartasUsadas(Stack!Carta novasCartasUsadas)
  {
    cartasUsadas = novasCartasUsadas;
  }

  /**
   * Verifica se o jogo está encerrado.
   * 
   * @returns true se o jogo estiver encerrado, caso contrário false
   */
  public bool getJogoEncerrado()
  {
    return jogoEncerrado;
  }

  /**
   * Define se o jogo está encerrado.
   * 
   * @param novoJogoEncerrado O novo estado de encerramento do jogo
   */
  public void setJogoEncerrado(bool novoJogoEncerrado)
  {
    jogoEncerrado = novoJogoEncerrado;
  }

  /**
   * Verifica se o sentido de rotação do jogo está invertido.
   * 
   * @returns true se o sentido de rotação estiver invertido, caso contrário false
   */
  public bool getsentidoInvertidoRotacao()
  {
    return sentidoInvertidoRotacao;
  }

  /**
   * Define se o sentido de rotação do jogo está invertido.
   * 
   * @param novosentidoInvertidoRotacao O novo estado de inversão do sentido de rotação
   */
  public void setsentidoInvertidoRotacao(bool novosentidoInvertidoRotacao)
  {
    sentidoInvertidoRotacao = novosentidoInvertidoRotacao;
  }

}
