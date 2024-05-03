module entidades.RegrasUno;


import entidades.Uno;
import entidades.Carta;
import entidades.Utils;
import entidades.Baralho;
import entidades.Jogador;

import std.algorithm;
import std.array;
import std.range;
import std.typecons;
import std.conv;
import std.stdio;
import std.string;

/**
 * Classe abstrata para executar a lógica de jogar uma carta em um jogo de Uno em D lang.
 *
 * Esta classe fornece métodos para executar a lógica associada a diferentes tipos de cartas do jogo Uno.
 * Ela é projetada para ser estendida por classes concretas que implementam a lógica específica para cada tipo de carta.
 */
abstract class ExecutorCarta
{

    private Uno uno; ///< O jogo Uno ao qual esta instância está associada.
    private ListaJogadores jogadores; ///< A lista de jogadores no jogo.

    /**
     * Construtor da classe ExecutorCarta.
     *
     * Params:
     *   uno: O jogo Uno ao qual esta instância está associada.
     */
    this(Uno uno)
    {
        this.uno = uno;
        this.jogadores = uno.getJogadores();
    }

    /**
     * Executa a jogada associada à carta fornecida.
     * Este método é responsável por executar a ação correspondente à carta jogada.
     *
     * Params:
     *   carta: A carta a ser jogada.
     */
    public void jogarCarta(Carta carta)
    {
        string nomeCarta = carta.getNome();

        uno.testarCartaValida(carta);

        switch (nomeCarta)
        {
        case "Bloqueio":
            carta_Bloqueio();
            break;
        case "Inverter":
            carta_Inverter();
            break;
        case "Mais2":
            carta_Mais2();
            break;
        case "Joker":
            carta_Joker(carta);
            jogadores.mudarVezJogador();
            break;
        case "JokerMais4":
            carta_JokerMais4(carta);
            break;
        default:
            jogadores.mudarVezJogador();
            break;
        }
    }

    /**
    * Executa a ação associada à carta Inverter em um jogo de Uno.
    *
    * Este método é chamado quando a carta Inverter é jogada e executa a ação correspondente, que é inverter a direção de rotação dos jogadores e passar a vez para o próximo jogador.
    */
    protected void carta_Inverter()
    {
        jogadores.trocarSentidoRotacao();
        jogadores.mudarVezJogador();
    }

    /**
 * Executa a ação associada à carta Bloqueio em um jogo de Uno.
 *
 * Este método é chamado quando a carta Bloqueio é jogada e executa a ação correspondente, que é bloquear o próximo jogador e fazê-lo perder a vez.
 */
    protected void carta_Bloqueio()
    {
        writeln();
        writeln("Jogou a carta Bloqueio.");
        writeln();
        writefln(jogadores.getJogadorVezProximo().getNome() ~ " foi bloqueado. Perdeu a vez!");
        Decoracao.aMimir(1);
        jogadores.pularVezJogador();
    }

    /**
 * Executa a ação associada à carta Mais 2 em um jogo de Uno.
 *
 * Este método é chamado quando a carta Mais 2 é jogada e executa a ação correspondente, que é fazer o próximo jogador comprar duas cartas e perder a vez.
 */
    protected void carta_Mais2()
    {
        writeln();
        writefln(jogadores.getJogadorVezProximo().getNome() ~ " comprou duas cartas. Perdeu a vez!");
        writeln();
        uno.getBaralho().distribuirCartaJogador(jogadores.getJogadorVezProximo(), 2);
        Decoracao.aMimir(1);
        jogadores.pularVezJogador();
    }

    protected abstract void carta_JokerMais4(Carta carta);
    protected abstract void carta_Joker(Carta carta);
}

/**
 * Classe para executar a lógica de jogar uma carta por um jogador controlado pelo computador em um jogo de Uno em D lang.
 *
 * Esta classe estende a classe abstrata ExecutorCarta e implementa a lógica específica para um jogador controlado pelo computador.
 */
class ExecutorCartaBot : ExecutorCarta
{

    /**
     * Construtor da classe ExecutorCartaBot.
     *
     * Params:
     *   uno: O jogo Uno ao qual esta instância está associada.
     */
    this(Uno uno)
    {
        super(uno);
    }
    
    /**
     * Executa a ação associada à carta Joker +4 em um jogo de Uno, para um jogador controlado pelo computador.
     *
     * Este método é chamado quando a carta Joker +4 é jogada por um jogador controlado pelo computador e executa a ação correspondente, que é escolher a cor mais comum das cartas na mão do jogador e fazer o próximo jogador comprar quatro cartas e perder a vez.
     *
     * Params:
     *   carta: A carta Joker +4 jogada.
     */
    public override void carta_JokerMais4(Carta carta)
    {
        Jogador jogador = uno.getJogadorVez();
        Carta[] cartas = jogador.getMaoCartas().toArray();

        auto contagemCores = ["Azul": 0, "Verde": 0, "Vermelho": 0, "Amarelo": 0];

        foreach (cartaBaralho; cartas)
        {
            contagemCores[cartaBaralho.getCor()]++;
        }

        string corMaiorQuantidade = "Azul";
        int quantidadeMaior = 0;

        foreach (cor, quantidade; contagemCores)
        {
            if (quantidade > quantidadeMaior)
            {
                corMaiorQuantidade = cor;
                quantidadeMaior = quantidade;
            }
        }

        carta.setCor(corMaiorQuantidade);
        uno.getBaralho().distribuirCartaJogador(jogadores.getJogadorVezProximo(), 4);
        writeln();
        writefln(jogadores.getJogadorVezProximo().getNome() ~ " comprou quatro cartas. Perdeu a vez!");
        writeln();
        Decoracao.aMimir(1);
        jogadores.pularVezJogador();
    }

    /**
     * Executa a ação associada à carta Joker em um jogo de Uno, para um jogador controlado pelo computador.
     *
     * Este método é chamado quando a carta Joker é jogada por um jogador controlado pelo computador e executa a ação correspondente, que é escolher a cor mais comum das cartas na mão do jogador.
     *
     * Params:
     *   carta: A carta Joker jogada.
     */
    public override void carta_Joker(Carta carta)
    {
        Jogador jogador = uno.getJogadorVez();
        Carta[] cartas = jogador.getMaoCartas().toArray();

        auto contagemCores = ["Azul": 0, "Verde": 0, "Vermelho": 0, "Amarelo": 0];

        foreach (cartaBaralho; cartas)
        {
            contagemCores[cartaBaralho.getCor()]++;
        }

        string corMaiorQuantidade = "Verde";
        int quantidadeMaior = 0;

        foreach (cor, quantidade; contagemCores)
        {
            if (quantidade > quantidadeMaior)
            {
                corMaiorQuantidade = cor;
                quantidadeMaior = quantidade;
            }
        }

        carta.setCor(corMaiorQuantidade);
    }
}

/**
 * Classe para executar a lógica de jogar uma carta por um jogador humano em um jogo de Uno em D lang.
 *
 * Esta classe estende a classe abstrata ExecutorCarta e implementa a lógica específica para um jogador humano.
 */
class ExecutorCartaJogador : ExecutorCarta
{

    /**
     * Construtor da classe ExecutorCartaJogador.
     *
     * Params:
     *   uno: O jogo Uno ao qual esta instância está associada.
     */
    this(Uno uno)
    {
        super(uno);
    }

    /**
     * Executa a ação associada à carta Joker +4 em um jogo de Uno, para um jogador humano.
     *
     * Este método é chamado quando a carta Joker +4 é jogada por um jogador humano e executa a ação correspondente, que é permitir que o jogador escolha a cor da próxima jogada e fazer o próximo jogador comprar quatro cartas e perder a vez.
     *
     * Params:
     *   carta: A carta Joker +4 jogada.
     */
    public override void carta_JokerMais4(Carta carta)
    {
        writeln("Jogou a carta Joker +4.");

        string corDaCarta = DataInput.selecionarElementoPeloUsuario(
            Baralho.CARTAS_CORES,
            4,
            "Digite um numero: "
        );

        carta.setCor(corDaCarta);

        uno.getBaralho().distribuirCartaJogador(jogadores.getJogadorVezProximo(), 4);

        writeln();
        writefln(jogadores.getJogadorVezProximo().getNome() ~ " comprou quatro cartas. Perdeu a vez!");
        writeln();
        jogadores.pularVezJogador();
    }

    /**
     * Executa a ação associada à carta Joker em um jogo de Uno, para um jogador humano.
     *
     * Este método é chamado quando a carta Joker é jogada por um jogador humano e executa a ação correspondente, que é permitir que o jogador escolha a cor da próxima jogada.
     *
     * Params:
     *   carta: A carta Joker jogada.
     */
    public override void carta_Joker(Carta carta)
    {
        writeln("Jogou a carta Joker.");

        string corDaCarta = DataInput.selecionarElementoPeloUsuario(
            Baralho.CARTAS_CORES,
            4,
            "Escolha uma Cor"
        );

        carta.setCor(corDaCarta);
    }

}

/**
 * Classe responsável por definir e controlar as regras do jogo Uno.
 *
 * Esta classe gerencia as interações entre os jogadores, as cartas e as execuções das jogadas, garantindo que as regras do Uno sejam seguidas durante o jogo.
 */
export class RegrasUno
{
    private Uno uno; ///< O jogo Uno ao qual estas regras estão associadas
    private ListaJogadores jogadores; ///< Lista de jogadores participantes do jogo
    private ExecutorCartaJogador executorCartaJogador; ///< Executor de jogadas para um jogador humano
    private ExecutorCartaBot executorCartaBot; ///< Executor de jogadas para um jogador bot

    /**
     * Construtor padrão da classe RegrasUno.
     *
     * Inicializa os membros privados com valores nulos.
     */
    public this()
    {
        this.uno = null;
        this.jogadores = null;
        this.executorCartaBot = null;
        this.executorCartaJogador = null;
    }

    /**
     * Define o jogo Uno associado a estas regras.
     *
     * Params:
     *   uno: O jogo Uno a ser associado a estas regras.
     */
    public void setUno(Uno uno)
    {
        this.uno = uno;
        this.jogadores = uno.getJogadores();
        this.executorCartaBot = new ExecutorCartaBot(uno);
        this.executorCartaJogador = new ExecutorCartaJogador(uno);
    }

    /**
     * Permite que um jogador jogue uma carta no jogo Uno.
     *
     * Params:
     *   carta: A carta a ser jogada pelo jogador.
     */
    public void jogarCarta(Carta carta)
    {
        Jogador jogadorVez = jogadores.getJogadorVez();

        if (cast(Bot) jogadorVez)
        {
            executorCartaBot.jogarCarta(carta);
        }
        else
        {
            executorCartaJogador.jogarCarta(carta);
        }

    }

    /**
     * Verifica se o jogo Uno está finalizado.
     *
     * Este método verifica se o jogo Uno chegou ao fim, ou seja, se algum jogador ficou sem cartas na mão.
     *
     * Params:
     *   jogadorVezAnterior: O jogador que jogou a última carta na rodada anterior.
     *
     * Returns:
     *   true se o jogo estiver finalizado (um jogador venceu), false caso contrário.
     */
    public bool verficiarSeJogoEstaFinalizado(Jogador jogadorVezAnterior)
    {
        return jogadorVezAnterior.getMaoCartas().length() == 0;
    }

}
