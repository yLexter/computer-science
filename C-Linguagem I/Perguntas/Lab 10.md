# Lab 10

## Recapitulando: uso de ponteiros

Ponteiros são usados para acessar o endereço de memória das variáveis, fazendo um controle de mais baixo nível. Por exemplo:

```
#include <stdio.h>

int main(void) {

  int x = 10;
  double y = 20.50;
  char z = 'a';

  int *xPtr = &x;
  double *yPtr = &y;
  char *zPtr = &z;

  printf("Endereço de x = %p - Valor de x = %d\n", xPtr, *xPtr);
  printf("Endereço de y = %p - Valor de y = %.2lf\n", yPtr, *yPtr);
  printf("Endereço de z = %p - Valor de z = %c\n", zPtr, *zPtr);

  return 0;
}
```

Um exemplo de saída para o código acima é este:

```
Endereço de x = 0x7ffdd971f84c - Valor de x = 10
Endereço de y = 0x7ffdd971f840 - Valor de y = 20.50
Endereço de z = 0x7ffdd971f83f - Valor de z = 97
```

Ao contrário da declaração de variáveis, com ponteiros é preciso ter atenção aos sinais utilizados para indicar o apontamento e o desreferenciamento:

```
#include <stdio.h>

int main(void) {
  // declaração de variável 1
  int x = 10;
  
  // declaração de variável 2
  int x;
  x = 10;

  // declaração de ponteiro 1
  int *xPtr;
  xPtr = &x;

  // declaração de ponteiro 2
  int *xPtr = &x;
  
  return 0;
}
```

Operações podem ser feitas entre valores por meio de ponteiros, por exemplo:

```
#include <stdio.h>

int main(void) {

  int x = 10;
  double y = 20.50;
  
  int *xPtr = &x;
  double *yPtr = &y;

  double soma = *xPtr + *yPtr;
  
  printf("Soma = %.2lf\n", soma);
 
  return 0;
}
```

O resultado deste código é:
```
Soma = 30.50
```
O que aconteceu para esse resultado é que temos o acesso aos valores nos endereços de memória indicados pelos ponteiros `xPtr` e `yPtr`. O sinal `*` acessa o valor a partir do ponteiro.

## Incrementando os ponteiros com o qualificador const

O qualificador const permite que você informe ao compilador que o valor de uma variável específica não deve ser modificado, reforçando assim o princípio do menor privilégio. Isso pode reduzir o tempo de depuração e evitar efeitos colaterais não intencionais, tornando um programa mais robusto e mais fácil de modificar e manter. Se for feita uma tentativa de modificar um valor declarado const, o compilador o detecta e emite um erro.

### Usando um ponteiro não constante em dados não constantes

O exemplo de função no desafio do último tutorial exemplifica bem o risco de efeito colateral ao trabalhar com uma informação sem esse qualificador. Na situação a seguir, vemos que o nível mais alto de acesso a dados é concedido por um ponteiro não constante para dados não constantes.

```
#include <stdio.h>
#include <ctype.h> 

void converteParaMaiusculas(char *sPtr);

int main(void) {
  char palavra[] = "aula dE laboraToRio De proGramaCaO";

  printf("Antes da conversão: %s\n", palavra);
  converteParaMaiusculas(palavra);
  printf("Depois da conversão: %s\n", palavra);

  return 0;
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

Os dados podem ser modificados por meio do ponteiro desreferenciado e o ponteiro pode ser modificado para apontar para outros itens de dados. Uma função pode usar esse ponteiro para receber um argumento de string e, em seguida, processar (e possivelmente modificar) cada caractere da string.

A função converteParaMaiusculas acima declara seu parâmetro, um ponteiro não constante para dados não constantes chamado `sPtr`. A função processa a string da matriz (apontada por `sPtr`) um caractere por vez. A função da biblioteca padrão C `toupper` do cabeçalho `<ctype.h>` converte cada caractere em sua letra maiúscula correspondente. 

Se o caractere original não for uma letra ou já estiver em maiúscula, toupper retornará o caractere original. No final do laço o ponteiro é incrementado para apontar para o próximo caractere na string. O Capítulo 8 apresenta muitas funções de processamento de strings e caracteres da biblioteca padrão C.

### Usando um ponteiro não constante em dados constantes

Um ponteiro não constante para dados constantes pode ser modificado para apontar para qualquer item de dados do tipo apropriado, mas os dados para os quais ele aponta não podem ser modificados. Uma função pode receber tal ponteiro para processar os elementos de um argumento de matriz sem modificá-los. Por exemplo, observe o uso da função a seguir:

```
#include <stdio.h>

void imprimeCaracteres(const char *sPtr);

int main(void) {
  char palavra[] = "aula dE laboraToRio De proGramaCaO";

  puts("A String é: ");
  imprimeCaracteres(palavra);

  return 0;
}

void imprimeCaracteres(const char *sPtr) {
  while (*sPtr != '\0') {
    printf("%c", *sPtr);
    ++sPtr;
  }
}
```

A função `imprimeCaracteres` declara o parâmetro `sPtr` como sendo do tipo `const char *`. A declaração é lida da direita para a esquerda como “sPtr é um ponteiro para uma constante de caractere”. A estrutura de repetição contida na função exibe cada caractere até encontrar um caractere nulo. Depois de exibir cada caractere, o loop incrementa o ponteiro sPtr para apontar para o próximo caractere da string.

**Exercício 1**: Mude o núcleo da função `imprimeCaracteres` para alguma instrução que modifique cada caractere, usando o operador de desreferenciamento, e anote no código, usando comentários, qual o erro que apareceu.

### Tentando modificar um ponteiro constante para dados não constantes

Um ponteiro constante para dados não constantes sempre aponta para o mesmo local de memória, mas os dados nesse local podem ser modificados por meio do ponteiro.

```
#include <stdio.h>

int main(void) {
  int x = 0;
  int y = 0;

  int *const ptr = &x;

  *ptr = 7;  // é permitido pois constante não é o valor, e sim o ponteiro
  printf("%d", x);
  // ptr = &y; // erro: o ponteiro é constante, não pode ser apontado para outro endereço de memória
  
  return 0;
}
```

O ponteiro `ptr` é definido no exemplo anterior como sendo do tipo `int *const`, que é lido da direita para a esquerda como “ptr é um ponteiro constante para um inteiro”. Depois de  é inicializado (linha 11) com o endereço da variável inteira x. O programa tenta atribuir o endereço de y a ptr (linha 14), mas o compilador gera um erro.

**Exercício 2**: Crie uma função análoga ao primeiro exemplo, que imprime todas as letras minúsculas, usando um ponteiro do tipo `char *const` e anote com comentários o que acontece.

### Tentando modificar um ponteiro constante para dados constantes

O menor privilégio de acesso é concedido por um ponteiro constante para dados constantes. Esse ponteiro sempre aponta para o mesmo local de memória e os dados nesse local de memória não podem ser modificados.

É assim, por exemplo, que que um array deve ser passado para uma função que apenas olha para os elementos do array usando a notação de índice de array e não modifica os elementos.

Para entender melhor, veja o exemplo a seguir:

```
#include <stdio.h>

int main(void) {
  int x = 0;
  int y = 0;

  const int *const ptr = &x;

  //*ptr = 7;  // erro: não é permitido pois o valor não é constante
  printf("%d", x);
  // ptr = &y; // erro: não é permitido pois o ponteiro também não é constante  

  return 0;
}
```

**Exercício 3**: Modifique a função do exercício anterior, que imprime todas as letras minúsculas, para, desta vez, usar um ponteiro do tipo `const char *const` e anote com comentários o que acontece.

## Arrays e Ponteiros

O uso de ponteiros para arrays é um pouco diferente, pois não é preciso utilizar o operador `&` nem copiar o tamanho do array para o ponteiro. Observe o exemplo a seguir:

```
#include <stdio.h>

int main(void) {

  int vetorExemplo[3] = {1,2,3};

  int *vetorPtr = vetorExemplo; 
  // a linha de código anterior é a mesma coisa 
  // que colocar int *vetorPtr = &vetorExemplo[0]
  // mas não é necessário especificar dessa forma

  printf("%d\n", *vetorPtr);
  printf("%p", vetorPtr);

  return 0;
}
```

Se você observou, não é necessário especificar para qual valor do array estaremos apontando, pois, automaticamente, é feito o apontamento para o primeiro índice. 

Caso seja necessário apontar especificamente para um outro item do array, pode-se fazer o seguinte:

```
#include <stdio.h>

int main(void) {

  int vetorExemplo[3] = {1,2,3};
  int *vetorPtr = &vetorExemplo[1]; 

  printf("%d\n", *vetorPtr);
  printf("%p", vetorPtr); // Um outro endereço de memória, diferentemente do exemplo anterior

  return 0;
}
```

É possível percorrer um array da mesma forma que fizemos com as strings, e acessar os valores a partir do incremento do ponteiro.

**Exercício 4**: Baseado no exemplo anterior e no que discutimos sobre strings, elabore um programa que percorra arrays dos seguintes tipos: float, double e char. Usando comentários, responda: o que pode-se notar quando acessamos o endereço de memória a cada iteração de cada um desses arrays? 
