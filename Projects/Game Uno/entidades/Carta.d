module entidades.Carta;

import std.stdio;
import std.format;

import entidades.Jogador;

/**
 * Classe que representa uma carta do jogo Uno.
 */
export class Carta
{
    /**
     * Nome e cor da carta.
     */
    private string nome, cor;

    /**
     * Construtor padrão da classe Carta.
     * Inicializa uma carta sem nome e sem cor.
     */
    public this()
    {
    }

     /**
     * Construtor da classe Carta.
     * Inicializa uma carta com o nome e a cor especificados.
     * @param nome Nome da carta.
     * @param cor Cor da carta.
     */
    public this(string nome, string cor)
    {
        this.nome = nome;
        this.cor = cor;
    }

     /**
     * Método para obter o nome da carta.
     * @returns Nome da carta.
     */
    public string getNome()
    {
        return nome;
    }

    /**
     * Método para obter a cor da carta.
     * @returns Cor da carta.
     */
    public string getCor() const
    {
        return cor;
    }

    /**
     * Método para definir a cor da carta.
     * @param novaCor Nova cor da carta.
     */
    public void setCor(string novaCor)
    {
        cor = novaCor;
    }

    /**
     * Método para representar a carta como uma string.
     * Se a carta tiver uma cor, inclui a cor na representação.
     * @returns Representação da carta como uma string.
     */
    override string toString() const
    {
        if (this.cor != null)
        {
            string print = format("%-13s["~cor~"]", "[" ~ nome ~ "]");
            return print;
        }

        return "[" ~ nome ~ "]";
    }
}