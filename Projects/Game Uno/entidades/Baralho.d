module entidades.Baralho;

import entidades.Jogador;
import entidades.Carta;
import entidades.Utils;

import std.container;
import std.range;
import std.random;


export class Baralho
{
    /**
     * Constante que define as cartas de numeração no baralho.
     */
    public static string[] CARTAS_NUMERACAO = [
        "Zero", "Um", "Dois", "Tres", "Quatro", "Cinco", "Seis", "Sete",
        "Oito",
        "Nove"
    ];

    /**
     * Constante que define as cartas especiais no baralho.
     */
    public static string[] CARTAS_ESPECIAIS = [
        "Bloqueio", "Inverter", "Mais2"
    ];
    /**
     * Constante que define as cores das cartas no baralho e cartas especiais.
     */
    public static string[] CARTAS_CORES = ["Vermelho", "Azul", "Verde", "Amarelo"];
    public static string[] CARTAS_JOKERS = ["Joker", "JokerMais4"];
    public static int NUMEROS_CARTAS_JOKER = 4;

    private ArrayList!(Carta) cartasBaralho;

    /**
     * Construtor da classe Baralho.
     * Inicializa a lista de cartas do baralho.
     */
    this()
    {
        this.cartasBaralho = new ArrayList!(Carta)();
    }

      /**
     * Método para gerar as cartas do baralho.
     * Gera as cartas numeradas, especiais e os jokers.
     */
    public void gerarCartas()
    {
        // Cartas numeradas de 0 a 9 para cada cor
        foreach (cor; CARTAS_CORES)
        {
            foreach (numero; CARTAS_NUMERACAO)
            {
                if (numero == "Zero")
                {
                    cartasBaralho.add(new Carta(numero, cor));
                }
                else
                {
                    cartasBaralho.add(new Carta(numero, cor));
                    cartasBaralho.add(new Carta(numero, cor));
                }
            }
        }

        // Cartas especiais
        foreach (cor; CARTAS_CORES)
        {
            foreach (especial; CARTAS_ESPECIAIS)
            {
                cartasBaralho.add(new Carta(especial, cor));
                cartasBaralho.add(new Carta(especial, cor));
            }
        }

        // Cartas coringas
        foreach (joker; this.CARTAS_JOKERS)
        {
            foreach (_; 0 .. NUMEROS_CARTAS_JOKER)
            {
                cartasBaralho.add(new Carta(joker, null));
            }
        }
    }

    /**
     * Método para embaralhar as cartas do baralho.
     */
    public void embaralharCartas()
    {
        cartasBaralho.shuffle();
    }

    /**
     * Método para obter a lista de cartas do baralho.
     * @returns Lista de cartas do baralho.
     */
    public ArrayList!(Carta) getCartasBaralho()
    {
        return cartasBaralho;
    }

     /**
     * Método para distribuir cartas a um jogador.
     * Remove as cartas do topo do baralho e as adiciona ao jogador especificado.
     * @param jogador Jogador ao qual as cartas serão distribuídas.
     * @param numeroCartas Número de cartas a serem distribuídas.
     */
    public void distribuirCartaJogador(Jogador jogador, int numeroCartas)
    {

        for (int x = 0; x < numeroCartas; x++)
        {
            jogador.adicionarCarta(
                cartasBaralho.pop()
            );
        }

    }

}
