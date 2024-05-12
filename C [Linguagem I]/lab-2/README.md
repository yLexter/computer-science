# Lab 2

Aprofundando nossos conhecimentos na linguagem C, veremos o poder dos operadores e o incremento de funcionalidades dos nossos programas com a adição de fluxos condicionais.

## Antes de tudo, os tipos

Sem esquecer que estamos trabalhando com uma lingugagem fortemente tipada, C possui os seguintes tipos:

Tipo | Tamanho (aprox. em bits) | Faixa de valores (mínima)
-- | -- | --
char | 8 | -127 a 127
unsigned char | 8 | 0 a 255
signed char | 8 | -127 a 127
int | 16 | -32.767 a 32.767
unsigned int | 16 | 0 a 65.535
signed int | 16 | O mesmo que `int`
short int | 16 | O mesmo que `int`
unsigned short int | 16 | 0 a 65.535
signed short int | 16 | O mesmo que `short int`
long int | 32 | -2.147.483.647 a 2.147.483.647
signed long int | 32 | O mesmo que `long int`
unsigned long int | 32 | 0 a 4.294.967.295
float | 32 | Seis dígitos de precisão
double | 64 | Dez dígitos de precisão
long double | 80 | Dez dígitos de precisão

Sabendo disso, nós podemos tratar do nosso primeiro operador, o de atribuição.

## Operadores de Atribuição

O operador de atribuição `=` pode ser usado dentro de qualquer expressão válida em C: `nome_da_variavel = expressao;`, onde a expressão pode ser tão simples quanto uma constante ou tão complexa quanto a sua necessidade. Veja os exemplos a seguir:

```
int main() {
  int idade = 29;
  
  char inicialNome = "R";

  float peso = 74.5;
  float altura = 1.80;
  float imc = calculaImc(peso, altura);
  return 0;
}

float calculaImc(float peso, float altura) {
  // cálculo de IMC
  return 
}
```

O destino, ou a parte esquerda, da atribuição deve ser uma variável ou um ponteiro (veremos o significado disso mais a frente).

Em um comando de atribuição, a regra de conversão de tipos é simples: o valor do lado direito de uma atribuição é convertido no tipo do lado esquerdo. Para entender melhor essa operação, vejamos o código a seguir:

```
int x;
char ch;
float f;

int main()
{
	ch = x;	/* linha 1 */
	x = f;	/* linha 2 */
	f = ch;	/* linha 3 */
	f = x;	/* linha 4 */
  return 0;
}
```
Na linha 1, os bits mais significativos do inteiro `x` são ignorados, deixando `ch` com os 8 bits menos significativos. Se `x` está entre 256 e 0, `ch` e `x` têm o mesmo valor; do contrário, `ch` reflete apenas os bits menos significativos de `x`.

Na linha 2, `x` recebe apenas a parte inteira de `f`.

Na linha 3, `f` converte o valor inteiro de 8 bits de `ch` no mesmo valor em ponto flutuante. 

Na linha 4, `f` converte o valor inteiro de 16 bits no formato em ponto flutuante.

Veja algumas conversões de tipos em atribuições na tabela a seguir:

Tipo do destino | Tipo da expressão | Possível informação perdida
-- | -- | --
signed char | char | Se valor > 127, o destino é negativo
char | short int | Os 8 bits mais significativos
char | int | Os 8 bits mais significativos
char | long int | Os 24 bits mais significativos
int | long int | Os 16 bits mais significativos
int | float | A parte fracionária, no mínimo
float | double | Precisão, o resultado é arredondado
double | long double | Precisão, o resultado é arredondado

Por fim, em C é possível realizar atribuições múltiplas, como no exemplo a seguir:

```
int main() {
  int x, y, z;
	x = y = z = 0;
  return 0;
}
```

Além do operador `=`, temos a possibilidade de combiná-lo com operadores matemáticos (`+` para adição, `-` para subtração, `*` para multiplicação, `/` para divisão e `%` para resto da divisão).

```
int main() {
  int c = 3, d = 5, e = 4, f = 6, g = 12;

  c += 7; /* mesmo que c = c + 7, resulta em c = 10 */
  d -= 4; /* mesmo que d = d - 4, resulta em d = 1 */
  e *= 5; /* mesmo que e = e * 5, resulta em e = 20 */
  f /= 3; /* mesmo que f = f / 3, resulta em f = 2 */
  g %= 9; /* mesmo que g = g % 9, resulta em g = 3 */

  return 0;
}
```

**Exercício 1**: Escreva um progama que recebe um número inteiro negativo e um número decimal positivo. Em seguida, converta o primeiro número em um decimal e o decimal em inteiro, testando pelo menos dois tipos diferentes de conversões. Realize também uma operação combinada com atribuição com cada número após a conversão.

## Operadores de Incremento e Decremento

Para este tipo de operação, só é possível trabalhar com adição e subtração, em duas ordens diferentes em relação à variável. Observe a tabela a seguir:

Operador | Exemplo | Significado
-- | -- | --
++ | ++a | Incrementa `a` em 1 e, em seguida, use o novo valor de `a` na expressão em que `a` reside.
++ | a++ | Usa o valor atual de `a` na expressão em que `a` reside e, em seguida, incrementa `a` por 1.
-- | --b | Decrementa `b` em 1 e, em seguida, use o novo valor de `b` na expressão em que `b` reside.
-- | b-- | Usa o valor atual de `b` na expressão em que `b` reside e, em seguida, decrementa `b` por 1.

Para ficar mais claro, observe o exemplo a seguir:

```
#include <stdio.h>

int main() {
  int c = 5;
  printf("%d", c); // imprime o valor de c antes do pós-incremento
  printf("%d", c++); // imprime 5 e então incrementa
  printf("%d", c); // imprime 6

  int c = 5;
  printf("%d", c); // imprime o valor de c antes do pré-incremento
  printf("%d", ++c); // incrementa c e então imprime 6
  printf("%d", c); // imprime 6

  return 0;
}
```

**Exercício 2**: Verifique qual o resultado, dado dois números `a` e `b` fornecidos pelo usuário, das seguintes operações: 
1) a = 5; b = a * (a++);
2) a = 5; b = a * ++a;
3) a = 5; b = a * a++;

## Operadores de Comparação

Antes de começarmos a incrementar os nossos programas com fluxos condicionais, é importante conhecer os operadores envolvidos na comparação, primeiramente, entendendo quais são os operadores de igualdade:

Operador | Exemplo | Condição avaliada
-- | -- | --
== | x == y | `x` é igual a `y`
!= | x != y | `x` é diferente de `y`

E em seguida, os operadores relativos:

Operador | Exemplo | Condição avaliada
-- | -- | --
Operador > | x > y | `x` é maior a `y`
Operador < | x < y | `x` é menor a `y`
Operador >= | x >= y | `x` é maior ou igual a `y`
Operador <= | x <= y | `x` é menor ou igual a `y`

Os operadores retornam o resultado inteiro, sendo o resultado 0 quando a expressão é falsa e 1 quando verdadeira. Para testar, vamos imprimir como no exemplo:

```
#include <stdio.h>

int main() {
  int x = 5;
  int y = 10;
  
  printf("%d\n", x < y); // imprime 1
  printf("%d\n", x == y); // imprime 0

  return 0;
}
```

**Exercício 3**: Faça um programa que recebe dois valores inteiros e os compara, imprimindo os resultados das comparações.

**Desafio 1**: Pesquise como imprimir "true" ou "false" ao invés de 0 ou 1 usando o operador ternário `?`. 

Essas comparações irão nos ajudar a tomar decisões no nosso programa. Vamos começar com a estrutura mais simples, o **if-else**.

## Estrutura de Decisão: if-else

Para decidir entre duas soluções possíveis para um problema, é necessário comparar valores e decidir o que fazer. Em C, utilizamos `if` seguido de uma expressão para denotar que dali em diante, um novo bloco de código será executado dado que uma condição seja atendida. 

Já a palavra `else` significa senão, ou seja, se a condição da expressão do `if` não for satisfeita, então o que estará dentro do bloco do else será executado. 

Apenas o código associado ao `if` ou o código associado ao `else` será executado, nunca ambos.

A forma geral da sentença **if-else** é a seguinte:

```
  if (expressão) 
  {
    comando;
  } 
  else 
  {
    comando;
  }
```

Atenção: o `else` nem sempre é necessário, ou seja, ele é opcional.

Veja o exemplo a seguir: Para verificar se um número é par ou ímpar, utilizamos o operador `%`: se o resto da divisão for 0, o número é par, caso contrário, será ímpar.

Uma forma de resolver esse problema é a seguinte: 

```
#include <stdio.h>

int main() {
  int num;

  printf("Digite um número: ");
  scanf("%d", &num);
  
  if (num % 2 == 0) 
  {
    printf("O número é par!");
  } 
  else 
  {
    printf("O número é ímpar!");
  }

  return 0;
}
```

Veja que, ao avaliar a expressão `num % 2 == 0` (o resto da divisão por 2 é igual a zero), eu abro dois blocos de código. Um para se a condição for verdadeira e o outro, se for falsa.

Como cada bloco só tem uma linha, poderíamos abrir mão das chaves (somente nesse caso). 

```
#include <stdio.h>

int main() {
  int num;

  printf("Digite um número: ");
  scanf("%d", &num);
  
  if (num % 2 == 0) printf("O número é par!");
  else printf("O número é ímpar!");

  return 0;
}
```

E ainda há mais uma forma: como o `else` é opcional, podemos dispensar ele também.

```
#include <stdio.h>

int main() {
  int num;

  printf("Digite um número: ");
  scanf("%d", &num);
  
  if (num % 2 == 0) printf("O número é par!");
  printf("O número é ímpar!");

  return 0;
}
```

**Exercício 4**: Faça um Programa que peça um valor e mostre na tela se o valor é positivo ou negativo.

**Desafio 2**: Faça um Programa que pergunte em que turno você estuda. Peça para digitar 1-matutino ou 2-Vespertino ou 3-Noturno. Imprima a mensagem "Bom Dia!", "Boa Tarde!" ou "Boa Noite!" ou "Valor Inválido!", conforme o caso.
