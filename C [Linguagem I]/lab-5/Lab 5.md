# Lab 5

Neste roteiro, iremos nos especializar no uso de outras estruturas de repetição, controladas ou não por condições predefinidas, ou seja, que podem ter  um fim determinado com base em uma contagem.

## Revisando o Uso de Estruturas de Repetição

As estruturas de repetição permitem que um conjunto de instruções seja executado até que ocorra uma certa condição ou até que determinada condição deixe de ser satisfeita. A primeira estrutura que vimos, `while` (que quer dizer "enquanto"), tem o funcionamento muito parecido com o bloco `if`, sendo que, com `if`, o que está dentro do bloco é executado apenas uma vez; no bloco `while`, o que está dentro do bloco pode ser executado mais de uma vez.

```
  if (expressão) 
  {
    comando; // executado uma única vez
  } 

  while (expressão) 
  {
    comando; // executado mais de uma vez
  } 
```

Como não se sabe quantas vezes a condição será satisfeita, dizemos que a estrutura de repetição `while` tem final em aberto.

Se quisermos criar um programa que imprima uma contagem de 1 a 5, teremos a seguinte solução:

```
#include <stdio.h>

int main() {
  int contador = 1; // inicialização
  
  while (contador <= 5) { // condição de parada/continuidade
    printf("%d ", contador);
    ++contador; // incremento
  }
  puts("");
  
  return 0;
}
```

Note que precisamos atualizar o contador ao final do laço `while` sempre com uma variável do tipo `int`. Os valores de ponto flutuante podem ser aproximados, portanto, controlar laços de contagem com variáveis de ponto flutuante pode resultar em valores de contador imprecisos e testes de terminação imprecisos. Por esse motivo, você deve sempre controlar os laços de contagem com valores inteiros.

**Exercício 1**: Faça um programa que leia 5 números e informe a soma dos números.

**Exercício 2**: Faça um programa que calcule a média aritmética de um número determinado de números, por exemplo:

```
Média de quantos números? 3
Digite um número: 6
Digite um número: 9
Digite um número: 7
A média dos valores é 7.
```

## Estruturas de Repetição: for

Em uma solução envolvendo contagem é possível utilizar o laço `for`, com uma sintaxe menos verbosa, da seguinte forma:

```
for(inicialização; condição de parada/continuidade; incremento) {
  comando;
}
```

Com esse laço, portanto, a solução da impressão de contagem ficaria da seguinte forma:

```
#include <stdio.h>

int main() {
  // inicialização; condição de parada/continuidade; incremento
  for (int contador = 1; contador <= 5; ++contador) {
    printf("%d ", contador);
  }
  
  puts("");
  
  return 0;
}
```

A parte do incremento pode ser reescrita de várias maneiras, inclusive com variações para permitir incremento de dois em dois, de três em três, etc.

```
contador = contador + 1
contador += 1
++contador
contador++
```

**Exercício 3**: Reescreva a solução do exercício 2 utilizando `for`.

**Exercício 4**: Faça um programa que calcule o fatorial de um número inteiro fornecido pelo usuário. Exemplo: 5! = 5.4.3.2.1 = 120.

## Estruturas de Repetição: do-while

Novamente iremos utilizar `while`, só que com uma pequena diferença: exigir que, o conteúdo do bloco de repetição seja executado pelo menos uma vez. Isso porque, ao contrário dos laços `for` e `while`, que testam a condição do laço no começo, o `do-while` testa a condição no final do laço. 

```
do {
  comando;
} while (condição);

```

Então, se precisarmos reescrever a solução do primeiro exemplo, ficaremos com o seguinte:

```
#include <stdio.h>

int main() {
  int contador = 1; // inicialização
  
  do { 
    printf("%d ", contador);
  } while (++contador <= 5);
  puts("");
  
  return 0;
}
```

É possível mesclar a atualização do contador com a avaliação da condição também, como no exemplo anterior.

**Exercício 5**: Reescreva novamente a solução do exercício 2 utilizando `do-while`.

## Forçando a Parada de um Laço

Assim como na estrutura `switch`, também é possível utilizar a palavra reservada `break` para frear a execução de uma repetição. Por exemplo:

```
#include <stdio.h>

int main() {
  // inicialização; condição de parada/continuidade; incremento
  for (int contador = 1; contador <= 5; ++contador) {
    printf("%d ", contador);

    if (contador == 3)
      break;
  }
  
  puts("");
  
  return 0;
}
```

Uma operação contrária ao freio seria permitir que o laço continuasse de maneira forçada, ou pulasse uma etapa. Para isso, podemos utilizar a palavra reservada `continue`:

```
#include <stdio.h>

int main() {
  // inicialização; condição de parada/continuidade; incremento
  for (int contador = 1; contador <= 5; ++contador) {
    if (contador == 3)
      continue;
      
    printf("%d ", contador);

  }
  
  puts("");
  
  return 0;
}
```

**Exercício 6**: Replique os dois códigos anteriores invertendo a ordem da função `printf` e do bloco `if` para avaliar o efeito das palavras `break` e `continue`.

**Desafio 1**: Faça um programa que peça dois números, base e expoente, calcule e mostre o primeiro número elevado ao segundo número. Forneça várias soluções utilizando dos três laços que você aprendeu, mas não utilize a função de potência da linguagem.