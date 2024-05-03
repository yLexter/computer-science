module entidades.Utils;

import entidades.Jogador;
import entidades.Carta;

import std.stdio;
import std.algorithm;
import std.array;
import std.algorithm.iteration : map;
import std.conv : to;
import std.random;
import std.format;

import core.stdc.stdlib;
import core.thread;


/**
 * Implementação de uma lista dinâmica.
 */
class ArrayList(T)
{
    private T[] array;
    private int size;

     /**
     * Construtor padrão.
     * Inicializa a lista como vazia.
     */
    this()
    {
        this.array = [];
    }

     /**
     * Adiciona um elemento ao final da lista.
     *
     * Params:
     *   element: O elemento a ser adicionado.
     */
    void add(T element)
    {
        array ~= element;
        size++;
    }

    /**
     * Adiciona um elemento em uma posição específica na lista.
     *
     * Params:
     *   index: A posição onde o elemento será inserido.
     *   element: O elemento a ser adicionado.
     * Throws:
     *   Exception: Se o índice estiver fora dos limites da lista.
     */
    void add(int index, T element)
    {
        if (index < 0 || index > size)
        {
            throw new Exception("Index out of bounds");
        }

        array = array[0 .. index] ~ [element] ~ array[index .. size];
        size++;
    }

    /**
     * Retorna o elemento na posição especificada na lista.
     *
     * Params:
     *   index: A posição do elemento a ser retornado.
     * Returns:
     *   O elemento na posição especificada.
     * Throws:
     *   Exception: Se o índice estiver fora dos limites da lista.
     */
    T get(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new Exception("Index out of bounds");
        }

        return array[index];
    }

    /**
     * Remove e retorna o elemento na posição especificada na lista.
     *
     * Params:
     *   index: A posição do elemento a ser removido.
     * Returns:
     *   O elemento removido.
     * Throws:
     *   Exception: Se o índice estiver fora dos limites da lista.
     */
    T remove(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new Exception("Index out of bounds");
        }
        T element = array[index];
        array = array[0 .. index] ~ array[index + 1 .. size];
        size--;
        return element;
    }

    /**
     * Remove a primeira ocorrência do elemento especificado da lista.
     *
     * Params:
     *   element: O elemento a ser removido.
     */
    void remove(T element)
    {
        array = array.filter!(x => x !is element).array;
        size--;
    }

    /**
     * Remove e retorna o último elemento da lista.
     *
     * Returns:
     *   O último elemento da lista.
     * Throws:
     *   AssertionError: Se a lista estiver vazia.
     */
    T pop()
    {
        assert(size > 0, "Array vazio");

        auto lastIndex = size - 1;
        auto element = array[lastIndex];

        array = array[0 .. lastIndex];

        size--;

        return element;
    }

    /**
     * Retorna o último elemento da lista sem removê-lo.
     *
     * Returns:
     *   O último elemento da lista.
     * Throws:
     *   AssertionError: Se a lista estiver vazia.
     */
    T getLast()
    {
        assert(size > 0, "Array vazio");
        auto lastIndex = size - 1;
        auto element = array[lastIndex];

        return element;
    }

    /**
     * Retorna o tamanho atual da lista.
     *
     * Returns:
     *   O tamanho da lista.
     */
    int length()
    {
        return size;
    }

    /**
     * Remove todos os elementos da lista, deixando-a vazia.
     */
    void clear()
    {
        array = [];
        size = 0;
    }

    /**
     * Verifica se a lista está vazia.
     *
     * Returns:
     *   true se a lista estiver vazia, false caso contrário.
     */
    bool isEmpty()
    {
        return size == 0;
    }

    /**
     * Imprime os elementos da lista.
     * Os elementos são impressos no formato de um array.
     */
    void print()
    {
        writeln(array);
    }

    /**
     * Embaralha os elementos da lista aleatoriamente.
     */
    void shuffle()
    {
        randomShuffle(array);
    }

    /**
     * Converte a lista em um array.
     *
     * Returns:
     *   Um array contendo os elementos da lista.
     */
    public T[] toArray()
    {
        return array;
    }

    /**
     * Permite o acesso aos elementos da lista como se fosse um array.
     */
    alias array this;
}

/**
 * Implementação de uma pilha em D lang.
 *
 * Esta classe fornece métodos para manipulação de uma pilha de elementos de tipo genérico.
 */
class Stack(T)
{
    private T[] data; ///< O array interno que armazena os elementos da pilha.
    private int size; ///< O tamanho atual da pilha.

    /**
     * Construtor padrão.
     * Inicializa a pilha como vazia.
     */
    this()
    {
        data = [];
    }

    /**
     * Adiciona um elemento no topo da pilha.
     *
     * Params:
     *   value: O elemento a ser adicionado.
     */
    void push(T value)
    {
        data ~= value;
        size++;
    }

    /**
     * Remove e retorna o elemento no topo da pilha.
     *
     * Returns:
     *   O elemento removido do topo da pilha.
     * Throws:
     *   AssertionError: Se a pilha estiver vazia.
     */
    T pop()
    {
        assert(!isEmpty(), "Stack is empty");
        T value = data[size - 1];
        data = data[0 .. size - 1];
        size--;
        return value;
    }

    /**
     * Retorna o elemento no topo da pilha sem removê-lo.
     *
     * Returns:
     *   O elemento no topo da pilha.
     * Throws:
     *   AssertionError: Se a pilha estiver vazia.
     */
    T getLast()
    {
        return data[size - 1];
    }

    /**
     * Retorna o tamanho atual da pilha.
     *
     * Returns:
     *   O tamanho da pilha.
     */
    int getSize()
    {
        return size;
    }

    /**
     * Verifica se a pilha está vazia.
     *
     * Returns:
     *   true se a pilha estiver vazia, false caso contrário.
     */
    bool isEmpty()
    {
        return size == 0;
    }

    /**
     * Embaralha os elementos da pilha aleatoriamente.
     */
    void shuffle()
    {
        randomShuffle(data);
    }
}

/**
 * Classe para entrada de dados pelo usuário em D lang.
 *
 * Esta classe fornece métodos para interação com o usuário durante a entrada de dados.
 */
class DataInput
{
    /**
     * Permite que o usuário selecione um elemento de uma lista e retorna o elemento selecionado.
     *
     * Params:
     *   elements: Um array contendo os elementos entre os quais o usuário deve selecionar.
     *   sizeArray: O tamanho do array de elementos.
     *   label: Uma string que será exibida como rótulo antes da lista de elementos.
     * Returns:
     *   O elemento selecionado pelo usuário.
     * Throws:
     *   Exception: Se o usuário fornecer uma entrada inválida ou se o índice estiver fora dos limites da lista.
     */
    public static T selecionarElementoPeloUsuario(T)(T[] elements, int sizeArray, string label)
    {

        writeln();
        writeln(label);
        writeln();

        int i = 0;

        foreach (element; elements.map!(to!string))
        {
            writeln(++i, ": ", element);
        }

        int selection;
        string input;

        while (true)
        {

            try
            {
                write("Digite o numero: ");
                input = readln();
                input = input.replace("\n", "");

                selection = to!int(input);

                if (selection >= 1 && selection <= sizeArray)
                {   
                    writeln("\n".replicate(5));
                    return elements[selection - 1];
                }
                else
                {
                    writeln("Indice invalido. Tente novamente.");
                }
            }
            catch (Exception e)
            {
                writeln("Entrada invalida. Tente novamente.");
            }

        }
    }

}

/**
 * Exceção para representar jogadas inválidas em D lang.
 *
 * Esta exceção é lançada quando uma jogada inválida é detectada durante a execução do programa.
 */
class JogadaInvalidaException : Exception
{

    /**
     * Construtor da classe JogadaInvalidaException.
     *
     * Params:
     *   mensagem: Uma string contendo uma mensagem de erro que descreve a natureza da jogada inválida.
     */
    this(string mensagem)
    {
        super(mensagem);
    }

}

/**
 * Implementação de um nó de lista encadeada em D lang.
 *
 * Esta classe representa um nó que contém dados genéricos e referências para o próximo e o nó anterior na lista.
 */
class Node(T)
{
    T data; ///< Os dados armazenados neste nó.
    Node!T proximo; ///< O próximo nó na lista.
    Node!T anterior; ///< O nó anterior na lista.

    /**
     * Construtor da classe Node.
     *
     * Params:
     *   data: Os dados a serem armazenados no nó.
     */
    this(T data)
    {
        this.data = data;
        this.proximo = null;
        this.anterior = null;
    }
}

/**
 * Implementação de uma lista encadeada de jogadores em D lang.
 *
 * Esta classe representa uma lista encadeada na qual cada elemento é um jogador e fornece métodos para manipulação desses jogadores.
 */
class ListaJogadores
{
public:
    Node!Jogador inicio; ///< O primeiro nó da lista.
    Node!Jogador fim; ///< O último nó da lista.
    Node!Jogador vez; ///< O nó que representa o jogador atual.
    int tamanho; ///< O tamanho atual da lista.
    bool sentidoInvertido; ///< Indica se a lista está percorrendo no sentido invertido.

public:

    /**
     * Construtor padrão da classe ListaJogadores.
     * Inicializa os ponteiros e variáveis de controle.
     */
    this()
    {
        this.inicio = null;
        this.fim = null;
        this.vez = null;
        this.sentidoInvertido = false;
        this.tamanho = 0;
    }

    /**
     * Adiciona um jogador à lista.
     * 
     * Params:
     *   jogador: O jogador a ser adicionado.
     */
    void add(Jogador jogador)
    {
        Node!Jogador novoNo = new Node!Jogador(jogador);

        if (this.inicio is null)
        {
            this.inicio = novoNo;
            this.fim = novoNo;
            this.vez = novoNo;
        }
        else
        {
            this.fim.proximo = novoNo;
            novoNo.anterior = this.fim;
            this.fim = novoNo;
        }

        this.fim.proximo = this.inicio;
        this.inicio.anterior = this.fim;

        this.tamanho++;
    }

public:
    /**
     * Retorna o tamanho atual da lista.
     * 
     * Returns:
     *   O tamanho da lista.
     */
    int length()
    {
        return this.tamanho;
    }

    /**
     * Retorna um array contendo todos os jogadores na ordem em que estão na lista.
     * 
     * Returns:
     *   Um array contendo os jogadores.
     */
    Jogador[] toArray()
    {
        Jogador[] array = new Jogador[](this.tamanho);

        Node!Jogador atual = this.inicio;

        for (int i = 0; i < this.tamanho; i++)
        {
            array[i] = atual.data;
            atual = atual.proximo;
        }

        return array;
    }

public:

    /**
     * Define o próximo jogador como o jogador atual e o retorna.
     *
     * Returns:
     *   O jogador atualizado.
     */
    Jogador setJogadorVezProximo()
    {
        this.vez = vez.proximo;

        return vez.data;
    }

    /**
     * Define o jogador anterior como o jogador atual e o retorna.
     *
     * Returns:
     *   O jogador atualizado.
     */
    Jogador setJogadorVezAnterior()
    {
        this.vez = vez.anterior;

        return vez.data;
    }

    /**
     * Retorna o próximo jogador na lista, mantendo a direção de iteração atual.
     *
     * Returns:
     *   O próximo jogador.
     */
    Jogador getJogadorVezProximo()
    {
        if (sentidoInvertido)
        {
            return vez.anterior.data;
        }

        return vez.proximo.data;
    }

    /**
     * Altera o jogador atual de acordo com a direção de iteração atual.
     */
    void mudarVezJogador()
    {
        if (sentidoInvertido)
        {
            this.vez = vez.anterior;
        }
        else
        {
            this.vez = vez.proximo;
        }
    }

    /**
     * Pula uma vez na iteração dos jogadores na lista.
     * Isso equivale a chamar mudarVezJogador() duas vezes.
     */
    void pularVezJogador()
    {
        this.mudarVezJogador();
        this.mudarVezJogador();
    }

    /**
     * Inverte a direção de iteração dos jogadores na lista.
     * Isso altera o comportamento dos métodos getJogadorVezProximo() e mudarVezJogador().
     */
    void trocarSentidoRotacao()
    {
        this.sentidoInvertido = !this.sentidoInvertido;
    }

    /**
     * Obtém o nó de jogador na posição especificada na lista.
     *
     * Params:
     *   posicao: A posição do jogador na lista.
     * Returns:
     *   O nó de jogador na posição especificada.
     * Throws:
     *   Exception: Se a posição estiver fora dos limites da lista.
     */
    public Node!Jogador getJogadorNode(int posicao)
    {

        if (posicao < 0 || posicao >= this.tamanho)
        {
            throw new Exception("Posicao invalida");
        }

        Node!Jogador atual = this.inicio;

        for (int i = 0; i < posicao; i++)
        {
            atual = atual.proximo;
        }

        return atual;
    }

    /**
     * Retorna o sentido atual na lista.
     *
     * Returns:
     *   O sentido atual.
     */
    bool getSentidoRotacao() {
        return sentidoInvertido;
    }

    /**
     * Retorna o jogador atual na lista.
     *
     * Returns:
     *   O jogador atual.
     */
    Jogador getJogadorVez()
    {
        return vez.data;
    }

    /**
     * Sorteia aleatoriamente o jogador atual na lista.
     */
    void sortearJogadorVez()
    {
        int index = uniform(0, tamanho);

        this.vez = getJogadorNode(index);
    }

}

/**
 * Classe responsável por fornecer utilidades para a interface do usuário em D lang.
 *
 * Esta classe oferece métodos para realizar operações relacionadas à interface do usuário, como limpar a tela e pausar a execução.
 */
class Decoracao
{

public static:

    /**
     * Limpa a tela do console.
     * Este método é útil para fornecer uma experiência de usuário mais limpa e organizada.
     */
    void limparTela()
    {
        system("cls");
    }

    /**
 * Imprime duas mensagens em uma linha de forma simétrica.
 *
 * Esta função recebe duas mensagens e as imprime em uma única linha de forma simétrica, alinhando a segunda mensagem à direita.
 *
 * Params:
 *   msg1: A primeira mensagem a ser impressa.
 *   msg2: A segunda mensagem a ser impressa e alinhada à direita.
 */
    void printSimetrico(string msg1, string msg2) {
        auto str = format("%-13s   ("~msg2~")", msg1);

        writeln(str);
    }

    /**
     * Pausa a execução do programa por um determinado número de segundos.
     *
     * Params:
     *   segundos: O número de segundos a serem aguardados.
     */
    void aMimir(int segundos)
    {
        Thread.sleep(dur!"seconds"(segundos));
    }

    /**
 * Imprime uma mensagem dentro de um retângulo delimitado por linhas.
 *
 * Esta função recebe uma mensagem e a imprime dentro de um retângulo delimitado por linhas verticais e horizontais.
 *
 * Params:
 *   message: A mensagem a ser impressa dentro do retângulo.
 */
    void mensagemRetangulo(string message)
    {
        size_t width = message.length + 4; 

        writeln("|" ~ "-".replicate(width - 2) ~ "|");
        writeln("| " ~ message ~ " |");
        writeln("|" ~ "-".replicate(width - 2) ~ "|");
        writeln();
    }

}
