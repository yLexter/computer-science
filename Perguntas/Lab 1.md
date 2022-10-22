# Lab 1

Vamos ao nosso lab de hoje. Você já conhece um pouco de C e já consegue resolver alguns problemas simples usando a linguagem para programar soluções para esses problemas, certo? Vejamos como trabalhar melhor com o armazenamento em variáveis, os dados, considerando seus tipos, e o que é o escopo.

## Escopo de variáveis

Começando de trás para frente, o que vimos na aula passada irá contribuir para definir o que é o escopo. Na maioria das linguagens derivadas do C, existe a delimitação explícita de um bloco de código. Veja o exemplo da função principal, a função `main`:

```
#include <stdio.h>

int main()
{  
  return 0;
}
```

Note que a função é um bloco de código, e que deve estar dentro de chaves `{}`. A esse espaço da função principal podemos chamar de _escopo local_, onde, o que está definido ali não é "enxergado" de quem está fora da função.

Porém, considere este outro exemplo:

```
#include <stdio.h>

int soma;

int main()
{
  return 0;
}
```

A variável soma está disponível para ser utilizada não só na função `main`, mas em qualquer função neste mesmo código. Isso quer dizer que esta variável pode ser "enxergada" por todo o código, sendo considerada de _escopo global_. 

Logo, se uma variável está definida fora das funções, é de escopo global, senão, será de escopo local.

**Exercício 1**: Escreva um código que pede um número ao usuário, e, em seguida, mostre a mensagem "O número informado foi [número]". Esse número deve ser armazenado em uma variável global.

**Exercício 2**: Reescreva o programa do **exercício 2 do Lab 0**, sendo que, as variáveis que representam os números recebidos do usuário devem ser de escopo global e, o resultado final, deve ser de escopo local.

## Tipos de Dados

Dentre as informações que podemos utilizar para resolver problemas usando C, há cinco tipos básicos: caractere, inteiro, ponto flutuante, ponto flutuante de precisão dupla e sem valor (_char_, _int_, _float_, _double_ e _void_, respectivamente). 

Para testar, execute o código a seguir:

```
#include <stdio.h>

int main()
{  
  char caractere;
  int inteiro;
  float pontoFlutuante;
  double precisaoDupla;
  
  printf("%s\n", caractere);
  printf("%d\n", inteiro);
  printf("%f\n", pontoFlutuante);
  printf("%lf\n", precisaoDupla);
  
  return 0;
}
```

Você deve encontrar uma saída semelhante a essa:

```
(null)
0
0.000000
0.000000
```

Veja que, o valor de `caractere`, sem qualquer atribuição de tamanho ou informação, é `null`, ou seja, informação nula, vazia. Já o número inteiro difere dos demais por não ter casas decimais. Os tipos `float` e `double` diferem na sua precisão (6 e 10 dígitos, respectivamente).

Para formatar um valor decimal, poderíamos fazer o seguinte: 

```
#include <stdio.h>

int main()
{  
  float pontoFlutuante;
  double precisaoDupla;
  
  printf("%.6f\n", pontoFlutuante);
  printf("%.10lf\n", precisaoDupla);
  
  return 0;
}
```

E então, você deve encontrar uma saída semelhante a essa:

```
0.000000
0.0000000000
```

Já para o caractere, é preciso ter o cuidado de definir o seu tamanho. Por exemplo, se quisermos coletar apenas uma letra do teclado, poderíamos resolver da seguinte forma:

```
#include <stdio.h>

int main()
{  
  char letra[1];
  
  printf("Digite uma letra: ");
  scanf("%c", &letra);
  printf("%s", letra);
  
  return 0;
}
```

E então, você deve encontrar uma saída semelhante a essa (digitando a letra C no teclado):

```
Digite uma letra: C
C
```

O mesmo `char` também funciona para nomes (strings). Só definir um tamanho um pouco maior, ou até mesmo nenhum tamanho:

```
#include <stdio.h>

int main()
{ 
  char professorLP1[5] = "Ramon";
  char professorP1[] = "Não sei dizer ainda";

  printf("O professor de LP1 a tarde é: %s", professorLP1);  
  printf("\n");
  printf("O professor de P1 da outra turma é: %s", professorP1);
}
```

E então, você deve encontrar uma saída semelhante a essa:

```
O professor de LP1 a tarde é: Ramon
O professor de P1 da outra turma é: Não sei dizer ainda
```

**Exercício 3**: Tendo como dados de entrada o nome e a altura de uma pessoa, construa um programa que calcule seu peso ideal, usando a seguinte fórmula: (72.7*altura) - 58, sendo o resultado formatado com 3 dígitos dentro de uma mensagem final com o nome fornecido pelo usuário.

**Desafio 1**: Faça um Programa que peça a temperatura em graus Fahrenheit, transforme e mostre a temperatura em graus Celsius. (Formate bem a sua entrada e saída!) A fórmula para conversão é: C = 5 * ((F-32) / 9). 

**Desafio 2**: Faça um Programa que pergunte quanto você ganha por hora, o número de horas trabalhadas no mês, e as porcentagens de desconto do Imposto de Renda e INSS. 
Calcule e mostre o total do seu salário no referido mês em um programa que nos dê:
- salário bruto
- quanto pagou ao INSS
- quanto pagou de IR
- e o salário líquido.

Exiba o resultado conforme a tabela abaixo (considere que foi fornecido um salário de 1000 reais e porcentagens de desconto do IR e INSS de 11 e 8 por cento, respectivamente):

```
+ Salário Bruto : R$ 1000.00
- IR (11%) : R$ 110.00
- INSS (8%) : R$ 80.00
= Salário Liquido : R$ 810.00
```
