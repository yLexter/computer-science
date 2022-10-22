# Lab 4

Neste roteiro, iremos revisar o uso de condições nas soluções dos nossos programas em C, bem como iniciar o uso de repetições como estruturas de controle de fluxo.

## Revisando o Uso de Estruturas Condicionais

No roteiro "Lab 2" conhecemos a primeira forma de controle de fluxo de saídas em um programa, a estrutura `if-else`, que, basicamente, testa se uma expressão é verdadeira ou falsa (partindo da palavra reservada `if`), podendo ter ou não, uma válvula de escape (o conteúdo do bloco `else`).

Já o comando switch testa sucessivamente o valor de uma expressão contra uma lista de constantes inteiras ou de caractere. Quando o valor coincide, os comandos associados são executados. Um switch pode ter pelo menos 257 comandos case (C ANSI).

Veja que, em ambas as estruturas, existem um bloco de código que estará associado a uma condição ser verdadeira. Para o `if-else` a construção é mais dinâmica, podendo ser substituído pelo operador ternário `?`; já no `switch` fica um pouco mais restrito a avaliação de cláusulas (cases) de valores constantes.

Para recapitular, veja o exemplo da resolução do problema "Bom dia, boa tarde ou boa noite" usando as estruturas que estudamos até aqui:

```
#include <stdio.h>

int main() 
{
  int turno = 0;
  printf("Digite 1-Matutino, 2-Vespertino e 3-Noturno: ");
  scanf("%d", &turno);

  // resolução usando if-else
  if (turno == 1)
    printf("Bom dia!");
  else if (turno == 2)
    printf("Boa tarde!");
  else if (turno == 3)
    printf("Boa noite!");
  else 
    printf("Valor Inválido!");

  // resolução usando operador ternário
  turno == 1 ? printf("Bom dia!") : turno == 2 ? printf("Boa tarde!") : turno == 3 ? printf("Boa noite!") : printf("Valor Inválido!");

  // resolução usando switch cases
  switch (turno) {
    case 1:
      printf("Bom dia!");
      break;
    case 2:
      printf("Boa tarde!");
      break;
    case 3:
      printf("Boa noite!");
      break;
    default:
      printf("Valor Inválido!");
      break;
  }

  return 0;
}
```

**Exercício 1**: Faça um programa que pergunte o nome e o preço de dois produtos e informe qual produto você deve comprar, sabendo que a decisão é sempre pelo mais barato.

**Exercício 2**: Aprimore a solução do programa anterior, perguntando o preço de três produtos e, em seguida, informe qual é o produto mais caro.

**Desafio 1**: Faça um Programa que peça o valor dos 3 lados de um triângulo. O programa deverá informar se os valores podem ser um triângulo. Indique, caso os lados formem um triângulo, se o mesmo é: equilátero, isósceles ou escaleno. Três lados formam um triângulo quando a soma de quaisquer dois lados for maior que o terceiro. Dicas:
- Triângulo Equilátero: três lados iguais;
- Triângulo Isósceles: quaisquer dois lados iguais;
- Triângulo Escaleno: três lados diferentes

## Estruturas de Repetição: while

As estruturas de repetição permitem que um conjunto de instruções seja executado até que ocorra uma certa condição ou até que determinada condição deixe de ser satisfeita. A primeira estrutura que iremos trabalhar, `while` (que quer dizer "enquanto"), tem o funcionamento muito parecido com o bloco `if`: a diferença é que, no `if`, o que está dentro do bloco é executado apenas uma vez. Já no bloco `while`, o que está dentro do bloco pode ser executado mais de uma vez.

```
  if (expressão) 
  {
    comando;
  } 

  while (expressão) 
  {
    comando;
  } 
```

Como não se sabe quantas vezes a condição será satisfeita, dizemos que a estrutura de repetição `while` tem final em aberto.

Vejamos um primeiro exemplo: um contador de números. Suponha que desejemos contar quantos números o usuário digita no teclado. Implementar essa solução sem saber quantas vezes exatamente o usuário irá digitar alguma coisa é complicado, não? 

```
#include <stdio.h>

int main() 
{
  int contador = 0;
  int numero = 0;

  // quantas vezes preciso pedir ao usuário para digitar? 
  printf("Digite um número: ");
  scanf("%d", &numero); 
  contador++;

  printf("Digite um número: ");
  scanf("%d", &numero); 
  contador++;

  printf("Digite um número: ");
  scanf("%d", &numero); 
  contador++;

  printf("Digite um número: ");
  scanf("%d", &numero);
  contador++;

  // ... 

  printf("Você digitou %d números", contador);
  
  return 0;
}
```

Veja que temos alguns trechos de código repetidos. Fatalmente esses trechos serão suprimidos em um só dentro de um bloco `while`: 

```
#include <stdio.h>

int main() 
{
  int contador = 0;
  int numero = 0;
  
  printf("Digite um número: ");
  scanf("%d", &numero); 

  while () {
    contador++;
    printf("Digite um número: ");
    scanf("%d", &numero); 
  }

  printf("Você digitou %d números", contador);
  
  return 0;
}
```

Bem melhor, não? O que falta para completarmos nosso programa é estabelecer a condição de parada (ou de continuidade, depende do ponto de vista). Podemos pensar no seguinte: quando o usuário não digitar mais um número, a contagem deve ser encerrada. Logo, nossa condição para o programa continuar é: enquanto o usuário digitar um número. 

Em C, temos o valor `EOF` (pode significar o Ctrl + Z, ^Z ou ainda -1) para determinar a ausência de dados, ou seja, quando o usuário simplesmente tecla Enter. Então, com o auxílio desse valor, nosso programa ficaria assim:

```
#include <stdio.h>

int main() 
{
  int contador = 0;
  printf("Digite um número: ");
  scanf("%d", &numero); 

  while (numero != EOF) {
    contador++;
    printf("Digite um número: ");
    scanf("%d", &numero); 
  }

  printf("Você digitou %d números", contador);
  
  return 0;
}
```

~~**Exercício 3**: Faça um programa que leia um nome de usuário e a sua senha e não aceite a senha igual ao nome do usuário, mostrando uma mensagem de erro e voltando a pedir as informações.~~

**Desafio 2**: Faça um programa contador de vogais maiúsculas e minúsculas. As consoantes e símbolos devem ser desprezadas, e, ao finnal do programa, mostre a quantidade de vogais maiúsculas e de minúsculas que o usuário digitou. _Dica: use switch case._ 
