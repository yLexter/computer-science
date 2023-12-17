# Lab 8

Aprenderemos neste roteiro a lidar com Arrays para manipular conjuntos de números e caracteres.

## Conjuntos de Valores: Arrays

Um array é um grupo de elementos do mesmo tipo armazenados de forma contígua na memória. A imagem a seguir mostra um array de inteiros chamado `c`, contendo cinco elementos:

![](array_example.png)

Cada elemento é acessível pela sua posição, como uma espécie de esquema chave-fechadura. Ao acessar a chave 2, por exemplo, você tem acesso ao elemento 0; ao acessar a chave 3, temos o elemento 72, e assim por diante.

Essas chaves sempre estão dentro de colchetes `[]`, e se tornam variáveis que podem ser manipuladas como qualquer outra. Por exemplo:

```
#include <stdio.h>

int main() 
{
  int s[5];

  // Acessando o primeiro elemento e guardando valor
  s[0] = 1;
  // Acessando o segundo elemento e guardando valor
  s[1] = 2;
  // Acessando o terceiro elemento e guardando valor a partir do primeiro e do segundo
  s[2] = s[0] + s[1];
  printf("%d\n", s[2]);
}
```

Note que, inicialmente, um array precisa ter um tamanho definido. 

### Inicializando um array

É possível inicializar os valores de um array pelo menos de três formas diferentes. A primeira, através de um laço, permite colocar valores de forma automática em até todos os itens do array:

```
#include <stdio.h>

int main() 
{
  int s[5];

  for (size_t j = 0; j < 5; j++) {
    s[j] = 0;
  }

  printf("%s%8s\n", "Elemento", "Valor");
  
  for (size_t j = 0; j < 5; j++) {
    printf("%7zu%8d\n", j, s[j]);
  }
}
```

Executando o código acima, temos:

```
Elemento   Valor
      0       0
      1       0
      2       0
      3       0
      4       0
```

Uma segunda forma é dizer, diretamente na atribuição, quais são os elementos do array:

```
#include <stdio.h>

int main() 
{
  int s[5] = {1, 2, 3, 4, 5};
  
  for (size_t j = 0; j < 5; j++) {
    printf("%7zu%8d\n", j, s[j]);
  }
}
```
Executando o código acima, temos:

```
Elemento   Valor
      0       1
      1       2
      2       3
      3       4
      4       5
```

Algo que podemos notar nesses dois primeiros exemplos de código é a definição informal do tamanho, o que pode ser resolvido com a criação de uma constante com auxílio de uma diretiva `#define`:

```
#include <stdio.h>

#define SIZE 5

int main() 
{
  int s[SIZE] = {0}; // inicializa o array com elementos "zero"
  
  printf("%s%8s\n", "Elemento", "Valor");
  
  for (size_t j = 0; j < SIZE; j++) {
    printf("%7zu%8d\n", j, s[j]);
  }
}
```

Aproveitando, o código acima exemplifica a inicialização mais simples, replicando um elemento apenas, inferindo sua quantidade a partir do tamanho da definição do array (não está vinculado ao SIZE, poderia ser qualquer outro número informal também).

**Exercício 1**: Faça um Programa que leia 20 números, que devem ser inseridos em dois arrays com 10 elementos cada. Gere um terceiro vetor de 20 elementos, cujos valores deverão ser compostos pelos elementos intercalados dos dois outros arrays.

**Exercício 2**: Faça um programa que simule um lançamento de dados. Lance o dado 100 vezes e armazene os resultados em um array. Depois, mostre quantas vezes cada valor foi conseguido.

**Desafio 1**: Utilizando listas, faça um programa que faça 5 perguntas para uma pessoa sobre um crime (1 para SIM e 0 para NÃO). As perguntas são:
- "Telefonou para a vítima?"
- "Esteve no local do crime?"
- "Mora perto da vítima?"
- "Devia para a vítima?"
- "Já trabalhou com a vítima?"

O programa deve no final emitir uma classificação sobre a participação da pessoa no crime. Se a pessoa responder positivamente a 2 questões ela deve ser classificada como "Suspeita", entre 3 e 4 como "Cúmplice" e 5 como "Assassino". Caso contrário, ele será classificado como "Inocente".

**Desafio 2**: Faça um programa que leia um número indeterminado de valores, correspondentes a notas, encerrando a entrada de dados quando for informado um valor igual a -1 (que não deve ser armazenado). Após esta entrada de dados: 
- Mostre a quantidade de valores que foram lidos;
- Exiba todos os valores na ordem em que foram informados, um ao lado do outro;
- Exiba todos os valores na ordem inversa à que foram informados, um abaixo do outro;
- Calcule e mostre a soma dos valores;
- Calcule e mostre a média dos valores;
- Calcule e mostre a quantidade de valores acima da média calculada;
- Calcule e mostre a quantidade de valores abaixo de sete.

## Manipulando Strings com Arrays

Arrays podem conter dados de qualquer tipo, embora todos os elementos de um determinado array devam ter o mesmo tipo. Agora discutiremos o armazenamento de strings em arrays de caracteres. Até agora, a única capacidade de processamento de strings que temos é a saída de uma string com printf. Uma string como "hello" é realmente um array de caracteres individuais, além de mais um item.

```
#include <stdio.h>
#define SIZE 20

int main() 
{
  char string0[] = "hello";          // reserva implicitamente 6 caracteres
  char string1[] = "string literal"; // reserva implicitamente 15 caracteres
  char string2[SIZE];                // reserva 20 caracteres

  return 0;
}
```

Olhando para os comentários acima, pode parecer estranho que os arrays `string0` e `string1` tenham um caractere a mais no seu tamanho. Isso se dá pela ocorrência do caracter `\0`, presente, de forma implícita, pois o compilador determinou o tamanho do array `string0` e do `string1` com base no comprimento do string. A string "hello" contém cinco caracteres mais um caractere nulo de terminação de string. É como se ela fosse inicializada assim: 

```
char string0[] = {'h', 'e', 'l', 'l', 'o', '\0'};
```

Para manipular esses arrays, podemos utilizar as funções de entrada e saída que já conhecemos. Atenção a um detalhe: ao tratar com o array de caracteres, o operador de formatação é o `%s` e as letras estarão entre aspas duplas, enquanto que, quando tratamos com uma letra de cada vez, o operador de formatação é o `%c`. 

```
#include <stdio.h>
#define SIZE 20

int main() 
{
  char string0[] = "hello";          // reserva implicitamente 6 caracteres
  char string1[] = "string literal"; // reserva implicitamente 15 caracteres
  char string2[SIZE];                // reserva 20 caracteres

  printf("%s", "Digite uma palavra a string (no máximo 19 caracteres): ");
  scanf("%19s", string2);

  printf("string1 é: %s\nstring2 é: %s\n", string1, string2);
  puts("string1 com espaços entre os caracteres:");

  for (size_t i = 0; i < SIZE && string2[i] != '\0'; ++i) {
    printf("%c ", string2[i]); 
  }

  return 0;
}
```

Note também que, podemos formatar a quantidade de letras que serão lidas, assim como fizemos com as casas decimais de um `float`, por exemplo. Observe também a falta do operador `&` na função de entrada para um array de caracteres.

**Exercício 3**: Nome na vertical. Faça um programa que solicite o nome do usuário e imprima-o na vertical.

```
F
U
L
A
N
O
```

**Desafio 3**: Modifique o programa anterior de forma a mostrar o nome em formato de escadas (normal e invertida).

```
F
FU
FUL
FULA
FULAN
FULANO
FULAN
FULA
FUL
FU
F
```