# Lab 0

Olá. Seja bem-vindo a disciplina de Laboratório de Programação 1. Estaremos praticando a linguagem C, uma linguagem de propósito geral e de alto nível (e que também provê recursos de baixo nível). 

Desenvolvida no ano de 1972 pelo cientista americano Dennis Ritchie dentro da Bell Laboratories (a empresa do inventor do telefone, Graham Bell, hoje pertencente a Nokia), pensada para a criação de compiladores e sistemas operacionais, sendo a principal do Unix (suas características estão presentes nos sistemas MacOS, Linux e Android). 

Ela é considerada a "mãe" de muitas outras linguagens de alto nível conhecidas hoje, como C++, C#, Dart, Go, Java, PHP, Rust, Swift, entre outras, porém não deixou de ser atual, sendo ainda bastante utilizada nas áreas de seu propósito original e também em máquinas virtuais.

É uma linguagem de processamento muito rápido, em comparação com outras linguagens, como Java e Python, muito versátil, pois irá te ajudar, inclusive, a conhecer outras linguagens de programação. Por isso é tão popular.

## Nosso primeiro programa em C: Hello World

Para criarmos nosso primeiro programa, veja quais são os passos e a sintaxe do código:

```
#include <stdio.h>

int main()
{
  printf("Hello, World!");
  return 0;
}
```

Na primeira linha, temos a inclusão da biblioteca padrão de entrada e saída de dados (muito importante na resolução de problemas), o Standard I/O.

Como a maioria das linguagens de programação, C é composta por muitas funções, denotadas sempre que há uma palavra seguida de parênteses `()`. Nesse caso, estamos invocando a função principal, `main`, que é responsável por executar o código. Nela você poderá armazenar e manipular dados de entrada e saída.

Note que a função é um bloco de código, e que deve estar dentro de chaves `{}`. E mais uma observação: a função `main` sempre retorna alguma coisa, por isso temos a palavra `return` na última linha. Ela sinaliza que será devolvido um valor, que nesse caso é um número inteiro. Por isso que antes do nome da função aparece uma palavra reservada `int`, que corresponde ao tipo de informação que será devolvido ao final da função.

Ah, não esqueça que todo final de linha deve conter o ponto-e-vírgula `;`!

Por fim, veja o núcleo da nossa função: "imprimir" na tela a frase "Hello, World!". Para tanto, utilizaremos a função `printf` da biblioteca `stdio.h` para realizar essa tarefa para nós. 

Esse código pode ser escrito na sua máquina com a instalação de um compilador, como o CGC, por exemplo, e o uso de um editor de texto, como o Sublime. O arquivo deverá ter a extensão `.c`. 

Dica: Utilize uma IDE, como o CodeBlocks ou Visual Studio Code, para tornar sua tarefa de codar mais produtiva, ou utilize ambientes online, sempre priorizando aqueles integrados ao `GitHub`, como o `repl.it`.

**Exercício 1:** Como seria um programa para imprimir duas frases, tipo, uma saudação e seu nome? 

## Um segundo programa: somando dois números

Já que sabemos a forma de saída de dados, vejamos como realizar a inserção de informações no programa, ou seja, trabalhar com a entrada de dados.

Consideremos o cenário de somar dois números. Para tanto, é necessário criar o espaço onde armazenaremos os dois números que queremos somar, da seguinte forma:

```
#include <stdio.h>

int main()
{
  int integer1 = 0;
  int integer2 = 0;
}
```

Nós acabamos de criar duas _variáveis_, espaços na memória do computador para guardar informações necessárias para a resolução do problema. Como nosso problema é resolver uma soma de dois números, preciso memorizar quais são os dois números que irei somar (óbvio, não é mesmo)?

Dica: cuidado com o nome das variáveis: integer1 e INTEGER1 não são a mesma coisa. C é _case sensitive_.

Após criar as variáveis, vamos inserir a maneira de coletar as informações, que, neste caso, são os dois números. Utilizaremos a função `scanf`:

```
#include <stdio.h>

int main()
{
  int integer1 = 0;
  int integer2 = 0;

  scanf("%d", &integer1);
  scanf("%d", &integer2);
}
```

Beleza, agora já posso guardar o que eu digitar no teclado! Mas espere, tem algumas coisas que é preciso saber: a função `scanf` recebe dois _parâmetros_: o primeiro irá formatar o que você inserir do teclado - texto, mas que é chamado de `string`, para um `decimal integer` (por isso a letra d). O segundo parâmetro já é mais simples de entender: é o destino da informação (o nome da variável). Nesse caso, precisamos informar ao programa que será alocado espaço na memória para armazenar a informação, por isso o uso do símbolo `&`. Sem ele, nosso programa resultaria em um erro do tipo "segmentation fault" ou "access violation".

Por fim, precisamos armazenar a soma. Para tanto, iremos utilizar uma outra variável que irá guardar o resultado da operação aritimética:

```
#include <stdio.h>

int main()
{
  int integer1 = 0;
  int integer2 = 0;

  scanf("%d", &integer1);
  scanf("%d", &integer2);

  int sum = 0; 
  sum = integer1 + integer2;
}
```

Pronto, podemos rodar nosso programa. Mas ainda falta alguma coisa... imprimir o resultado na tela! Vamos utilizar novamente a função `printf`:

```
#include <stdio.h>

int main()
{
  int integer1 = 0;
  int integer2 = 0;

  scanf("%d", &integer1);
  scanf("%d", &integer2);

  int sum = 0; 
  sum = integer1 + integer2;

  printf("%d", sum);
}
```

Certo, nosso programa funciona. Imprime a soma corretamente. Mas, se olharmos de fora do código, como apenas usuários e não como programadores, o que exatamente esse programa está fazendo? Poderia ser uma subtração, uma multiplicação, uma divisão, ou qualquer outra operação. 

Para entender o que um programa faz, é necessário guiar o usuário, e isso também precisa ser programado. 

Primeiramente, é preciso informar qual o dado que o usuário irá digitar, antes de pedir isso no teclado:

```
#include <stdio.h>

int main()
{
  int integer1 = 0;
  int integer2 = 0;

  printf("Soma de dois números\n");
  
  printf("Digite o primeiro número: ");
  scanf("%d", &integer1);
  printf("Digite o segundo número: ");
  scanf("%d", &integer2);

  int sum = 0; 
  sum = integer1 + integer2;

  printf("%d", sum);
}
```

Além disso, na última linha, ao informarmos o resultado, podemos imprimir uma mensagem indicando que aquela informação é a soma dos dois números:

```
#include <stdio.h>

int main()
{
  int integer1 = 0;
  int integer2 = 0;

  printf("Soma de dois números\n");
  
  printf("Digite o primeiro número: ");
  scanf("%d", &integer1);
  printf("Digite o segundo número: ");
  scanf("%d", &integer2);

  int sum = 0; 
  sum = integer1 + integer2;

  printf("A soma é %d\n", sum);
}
```

Note que utilizamos o mesmo símbolo de formatação para `decimal integer` que fizemos no `scanf`. Isso se dá por conta desse **f** nas funções de scan e print. É possível formatar informações, inserindo quantas forem necessárias.

E o símbolo `\n` foi colocado para pular uma linha. Veja sem ele qual seria o resultado do seu programa.

**Exercício 2**: Crie um programa para subtrair dois números e multiplicar por um terceiro.

**Desafio**: Crie um programa para calcular potência de base 2, sendo a informação da base (2) sempre uma _constante_, e o expoente informado no telcado pelo usuário.
