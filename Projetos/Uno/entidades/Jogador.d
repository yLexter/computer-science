module entidades.Jogador;

import std.stdio;
import std.string;
import std.algorithm.iteration : map;

import entidades.Utils;
import entidades.Baralho;
import entidades.Uno;
import entidades.Carta;

/**
 * Classe abstrata representando um jogador em um jogo de Uno.
 */
export abstract class Jogador
{
    protected ArrayList!Carta maoCartas; ///< A mão de cartas do jogador.
    protected string nome; ///< O nome do jogador.

    /**
     * Construtor para criar um jogador com um nome dado.
     * 
     * Parâmetros:
     *     nome: O nome do jogador
     */
    this(string nome)
    {
        this.nome = nome;
        this.maoCartas = new ArrayList!(Carta)();
    }

    /**
     * Adiciona uma carta à mão do jogador.
     * 
     * Parâmetros:
     *     carta: A carta a ser adicionada
     */
    public void adicionarCarta(Carta carta)
    {
        maoCartas.add(carta);
    }

    /**
     * Verifica se o jogador possui Uno (apenas uma carta restante).
     * 
     * Retorna:
     *     bool: true se o jogador tiver Uno, caso contrário false
     */
    public bool isUno()
    {
        return maoCartas.length() == 1;
    }

    // Getters & Setters

    /**
     * Obtém o nome do jogador.
     * 
     * Retorna:
     *     string: O nome do jogador
     */
    public string getNome()
    {
        return nome;
    }

    /**
     * Define o nome do jogador.
     * 
     * Parâmetros:
     *     novoNome: O novo nome do jogador
     */
    public void setNome(string novoNome)
    {
        nome = novoNome;
    }

    /**
     * Obtém a mão de cartas do jogador.
     * 
     * Retorna:
     *     ArrayList!Carta: A mão de cartas do jogador
     */
    public ArrayList!Carta getMaoCartas()
    {
        return maoCartas;
    }

    /**
     * Remove uma carta da mão do jogador.
     * 
     * Parâmetros:
     *     carta: A carta a ser removida
     */
    public void removerCarta(Carta carta)
    {
        return maoCartas.remove(carta);
    }

    /**
     * Fornece uma representação em string do jogador.
     * 
     * Retorna:
     *     string: Uma string representando o jogador com o seu nome
     */
    override string toString() const
    {
        return "Jogador [" ~ nome ~ "]";
    }

    /**
     * Exibe a mão de cartas do jogador.
     */
    public void mostrarMaoCartas()
    {
        const(Carta)[] toArray = maoCartas.toArray();

        foreach (i, carta; toArray)
        {
            writeln(++i, ". ", carta.toString());
        }
    }

    /**
     * Método abstrato representando a jogada de um jogador no jogo.
     * 
     * Parâmetros:
     *     uno: A instância do jogo de Uno
     * 
     * Retorna:
     *     Carta: A carta a ser jogada pelo jogador
     */
    public abstract Carta jogar(Uno uno);
}

/**
 * Classe representando um jogador real em um jogo de Uno.
 */
export class JogadorReal : Jogador
{

    /**
     * Construtor para criar um jogador real com um nome dado.
     * 
     * Parâmetros:
     *     nome: O nome do jogador
     */
    public this(string nome)
    {
        super(nome);
    }

    /**
     * Implementa a lógica para a jogada de um jogador real no jogo.
     * 
     * Parâmetros:
     *     uno: A instância do jogo de Uno
     * 
     * Retorna:
     *     Carta: A carta a ser jogada pelo jogador
     */
    override Carta jogar(Uno uno)
    {
        Carta[] cartaArray = this.maoCartas.toArray();
        int size = this.maoCartas.length();

        Carta cartaJogada = null;

        while (true)
        {

            cartaJogada = DataInput.selecionarElementoPeloUsuario(
                cartaArray,
                size,
                "Escolha uma Carta"
            );

            try
            {
                uno.testarCartaValida(cartaJogada);
                return cartaJogada;
            }
            catch (JogadaInvalidaException error)
            {
                writeln();
                writefln("Error: " ~ error.msg);
                writeln();
                continue;
            }
        }
    }

}

/**
 * Classe representando um jogador bot em um jogo de Uno.
 */
export class Bot : Jogador
{
    /**
     * Construtor para criar um jogador bot com um nome dado.
     * 
     * Parâmetros:
     *     nome: O nome do jogador bot
     */
    public this(string nome)
    {
        super(nome);
    }

    /**
     * Implementa a lógica para a jogada de um jogador bot no jogo.
     * 
     * Parâmetros:
     *     uno: A instância do jogo de Uno
     * 
     * Retorna:
     *     Carta: A carta a ser jogada pelo jogador bot
     */
    override Carta jogar(Uno uno)
    {

        Carta[] cartaArray = this.maoCartas.toArray();

        writefln("Escolhendo uma carta (%s) ...", nome);
        Decoracao.aMimir(2);

        foreach (cartaMao; cartaArray)
        {

            if (cartaMao.getNome() == "Joker" || cartaMao.getNome() == "JokerMais4")
            {
                return cartaMao;
            }

            try
            {
                uno.testarCartaValida(cartaMao);
                return cartaMao;
            }
            catch (JogadaInvalidaException err)
            {
            }

        }

        throw new Exception("Nao eh pra chegar aqui");
    }

    /**
     * Fornece uma representação em string do jogador bot.
     * 
     * Retorna:
     *     string: Uma string representando o jogador bot com o seu nome
     */
    public override string toString() const {
        return super.toString() ~ " (Bot)";
    }

}
