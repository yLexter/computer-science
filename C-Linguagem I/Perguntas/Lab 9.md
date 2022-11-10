# Lab 9

## Ponteiros

Ponteiros são variáveis cujos valores são **endereços de memória**. Normalmente, uma variável contém diretamente um valor específico. Um ponteiro, no entanto, contém o endereço de outra variável que contém um valor específico. O ponteiro aponta para essa variável. Nesse sentido, um nome de variável referencia diretamente um valor e um ponteiro referencia indiretamente um valor, como na imagem a seguir:

![](pointer_example.png)

Referenciar um valor por meio de um ponteiro é chamado de **indireção**.

### Declarando um Ponteiro

Ponteiros, como todas as variáveis, devem ser definidos antes de serem usados. A instrução a seguir define a variável `countPtr` como um `int *` — um ponteiro para um inteiro:

```
int *countPtr;
```

Esta definição é lida da direita para a esquerda: “countPtr é um ponteiro para int” ou “countPtr aponta para um objeto ou variável do tipo int”. O sinal `*` indica que a variável é um ponteiro.

Por convenção é recomendado terminar o nome de cada variável de ponteiro com `Ptr` para indicar que a variável é um ponteiro e deve ser tratada de acordo. Outras convenções de nomenclatura comuns incluem iniciar o nome da variável com `p` (por exemplo, `pCount`) ou `p_` (por exemplo, `p_count`).

O sinal `*` não é reaproveitado com definições em linha, como acontece com os tipos normais, por exemplo:

```
// as duas variáveis são do tipo int
int count1, count2; 

// as duas variáveis são do tipo int, mas só a primeira é um ponteiro
int *countPtr, count; 

// a definição acima também pode ser escrita dessa forma: 
int *countPtr;
int count;
```

### Operadores para Ponteiros

O primeiro operador disponível nesse tutorial é o operador de endereço `&`. Ele retorna o endereço de uma variável e não pode ser aplicado a valores literais ou expressões.. Por exemplo:

```
int y = 5; // y contém o valor 5

int *yPtr = &y;  // yPtr contém o valor do endereço de y
```

![](pointer_example_2.png)

O segundo operador disponível é o de indireção `*`, também chamado de operador de desreferenciamento. Ele é usado para obter o valor do objeto apontado pelo ponteiro. Por exemplo:

``` 
printf("%d", *yPtr); // obtém o valor da variável apontada por yPtr, que é y = 5
```

No entanto, é preciso observar, ao utilizar do operador `*`, se o ponteiro foi inicializado ou atribuído ao endereço de outra variável na memória. Isso pode gerar erros durante a execução do programa, trazer resultados incorretos ou violar segurança do sistema. 

Vejamos, então, os dois operadores utilizados na prática:

```
#include <stdio.h>

int main() {
  int a = 7;
  int *aPtr = &a;

  printf("O endereço de a é %p \nO valor de aPtr é %p\n", &a, aPtr);
  printf("O valor de a é %d \nO valor de aPtr é %d\n", a, *aPtr);
  printf("&*aPtr = %p\n*&aPtr = %p\n", &*aPtr, *&aPtr);
  
  return 0;
}
```

Durante a execução do código anterior, você encontrará um resultado semelhante ao exibido a seguir (não será exatamente o mesmo, pois os endereços de memória são atribuídos dinamicamente):

```
O endereço de a é 0x7ffda3501604 
O valor de aPtr é 0x7ffda3501604
O valor de a é 7 
O valor de aPtr é 7
&*aPtr = 0x7ffda3501604
*&aPtr = 0x7ffda3501604
```

**Exercício 1**: Crie um programa que recebe dois valores inteiros `n1` e `n2` e, utilizando ponteiros `p1` e `p2`, calcula as seguintes expressões: 

- *p1 - *p2
- **&p1
- 3* - *p1/(*p2)+7

### Utilizando ponteiros em funções

Até aqui criamos funções que contém parâmetros ligados diretamente aos tipos das variáveis. Isso é o que chamamos de **passagem por valor**. Veja o exemplo a seguir:

```
#include <stdio.h>

int valorAoCubo(int n);

int main() {
  int a = 5;

  printf("O valor de a ao cubo é %d \n", valorAoCubo(a));
  
  return 0;
}

int valorAoCubo(int n) {
  return n * n * n;
}

```
A função `valorAoCubo` recebe um valor no parâmetro `n`, que, com escopo local, é utilizado para calcular o cubo deste parâmetro, sem alterar o valor original de `a`.

Com ponteiros, iremos ampliar as opções de uso de parâmetros nas funções que estamos criando. Usaremos uma técnica que chamamos de **passagem por referência**. Nesse caso, usaremos os operadores vistos anteriormente para concretizar o uso dos ponteiros, ou usar os valores de forma indireta.

```
#include <stdio.h>

void valorAoCuboComPonteiro(int *nPtr);

int main() {
  int a = 5;

  valorAoCuboComPonteiro(&a);
  printf("O valor de a ao cubo usando ponteiros é %d \n", a);
  
  return 0;
}

void valorAoCuboComPonteiro(int *nPtr) {
  *nPtr = *nPtr * *nPtr * *nPtr;
}

```

No exemplo anterior, perceba que, para receber o valor indireto da variável `a`, através do ponteiro que aponta para este valor, na definição da função, temos o uso do operador de desreferenciamento `*`. Na passagem do valor, ou seja, na chamada da função, temos o uso do operador de endereço `&`.

Ou seja: 

```
#include <stdio.h>

int valorAoCubo(int n);
void valorAoCuboComPonteiro(int *nPtr);

int main() {
  int a = 5;     

  // Uso do valor diretamente armazenado na variável a 
  a = valorAoCubo(a);
  printf("O valor de a ao cubo é %d \n", a);

  a = 5;  // redefinição do exemplo

  // Uso do ponteiro que aponta para o valor armazenado na variável a
  valorAoCuboComPonteiro(&a);
  printf("O valor de a ao cubo usando ponteiros é %d \n", a);
    
  return 0;
}

int valorAoCubo(int n) {
  // Uso do valor diretamente passado como parâmetro
  return n * n * n;
}

void valorAoCuboComPonteiro(int *nPtr) {
  // Uso do valor indiretamente passado como parâmetro 
  *nPtr = *nPtr * *nPtr * *nPtr;
}

```

Lembrou de algo parecido que já utilizamos? Isso mesmo, a função `scanf` do pacote padrão. Sempre que vamos receber um valor do teclado, esse valor é passado através da referência ao endereço da variável que armazenará o valor.

**_Observação_**: Com strings, não é necessário utilizar o operador de endereços `&` 

**Exercício 2**: Utilizando a mesma lógica do exemplo anterior, utilizando passagem por referência, crie uma função que, recebendo do usuário o valor do raio, calcula o perímetro (2\*pi*raio) e a área (pi\*raio²) de um círculo. Considere pi = 3.14.

### Uso de ponteiros com strings

Considere abaixo duas declarações em C. Qual é a diferença entre as duas?

```
char palavra[] = "programacao";
char *palavra  = "programacao";
```

A variável `char palavra[] = "programacao"` criam uma matriz (array) de caracteres que é como qualquer outra matriz, e sobre essa variável podemos fazer todas as operações de matriz. A única coisa especial sobre este array é que, embora tenhamos inicializado com 11 elementos, seu tamanho é 12 (o compilador adiciona automaticamente '\0').

```
#include <stdio.h>
int main()
{
    char palavra[] = "programacao";
    printf("%lu\n", sizeof(palavra));
    palavra[0] = 'P';
    printf("%s", palavra);
    return 0;
}
```

A saída do código anterior é: 

```
12
Programacao
```

A instrução `char *palavra  = "programacao"` cria uma string literal. Uma string literal é armazenada na parte somente leitura da memória pela maioria dos compiladores. Os padrões C e C++ dizem que as strings literais têm duração de armazenamento estática, qualquer tentativa de modificá-los gera um comportamento indefinido.

Logo, `palavra` é apenas um ponteiro e, como qualquer outro ponteiro, armazena o endereço da string literal.

```
#include <stdio.h>
int main()
{
    char *palavra = "programacao";
    printf("%lu\n", sizeof(palavra));

    // As linhas abaixo podem gerar erro ou nenhum efeito
    // palavra[0] = 'P';
    // printf("%s", palavra);
    
    return 0;
}
```

A saída do código anterior é: 

```
8
```

Resumindo: 

- O primeiro é um array, o segundo é um ponteiro;
- O tamanho do primeiro é dado pelo número de caracteres; o do segundo, pela quantidade de bytes na memória;
- `palavra` e `&palavra` são a mesma coisa, mas `palavra` e `*palavra` não são;
- O valor `programacao` no primeiro caso é armazenado uma seção contígua de memória; no segundo caso, numa seção de código de memória;
- Não é possível alterar o array `palavra` para um valor de tamanho maior do que o definido, enquanto que, no ponteiro, é permitido;
- Não é válido usar operador de incremento ou decremento no array `palavra`, enquanto que no ponteiro é válido;
- É permitido redefinir o tamanho na declaração do array, enquanto que o tamanho do ponteiro é somente leitura.

**Desafio 1**: Baseado no exemplo a seguir, elabore uma função que recebe uma string e retorna quantas vogais e quantas consoantes ela possui. Lembre-se de utilizar passagem de parâmetro como referência. 

```
#include <stdio.h>
#include <ctype.h> 

void converteParaMaiusculas(char *sPtr);

int main(void) {
  char palavra[] = "aula dE laboraToRio De proGramaCaO";

  printf("Antes da conversão: %s\n", palavra);
  converteParaMaiusculas(palavra);
  printf("Depois da conversão: %s\n", palavra);
}

void converteParaMaiusculas(char *sPtr) {
  while (*sPtr != '\0') {
    *sPtr = toupper(*sPtr);
    ++sPtr;
  }
}

```

Saída do exemplo anterior: 

```
Antes da conversão: aula dE laboraToRio De proGramaCaO
Depois da conversão: AULA DE LABORATORIO DE PROGRAMACAO
```
