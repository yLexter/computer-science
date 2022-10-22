# Lab 3

Conheceremos algumas das estruturas de controle de fluxo na linguagem C, entendendo todas as alternativas para avaliar condições e produzir diferentes saídas para os nossos programas.

## Revisando a mais básica estrutura de decisão: if-else

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

**Exercício 1**: Faça um Programa que peça um valor e mostre na tela se o valor é um múltiplo de 3, 4 ou 5. Dica: consulte o exemplo de par ou ímpar do lab anterior.

## Operador Ternário: ?

Existe uma forma econômica de escrever um if-else onde avalia-se apenas uma expressão, utilizando o operador ternário `?`.

A forma genérica de escrita com esse operador é como no exemplo a seguir:

```
expressão ? comando : outroComando;
```

Por exemplo, o problema do número par ou ímpar poderia ser resolvido em uma única linha, da seguinte forma:

```
#include <stdio.h>

int main() {
  int num;

  printf("Digite um número: ");
  scanf("%d", &num);
  
  num % 2 == 0 ? printf("O número é par!") : printf("O número é ímpar!");

  return 0;
}
```

Vale lembrar que é possível escrever várias expressões aninhadas com operadores ternários, ao invés de um comando.

```
exp1 ? comando : exp2 ? comando : outroComando;
```

**Exercício 2**: Reescreva a solução do desafio 2 do lab anterior com o uso de operador ternário. Substitua as possíveis entradas por números (1-Matutino, 2-Vespertino e 3-Noturno).

## Condições Aninhadas

Um comando `if` pode ser o objeto de outro `if` ou `else`. Condições aninhadas são comuns em programação. 

Em C, o `else` sempre se refere ao `if` mais próximo, que está dentro do mesmo bloco do `else` e não está associado a outro `if`. Para entender esse conceito, considere o exemplo genérico a seguir.

```
if(exp1)
{
    if(exp2) comando 1;
    if(exp3) comando 2; // este if está associado
    else comando 3;     // a este else
} else comando 4; // esse aqui está associado ao if(exp1)
```

O padrão C ANSI especifica que pelo menos 15 níveis de aninhamento devem ser suportados. Na prática, a maioria dos compiladores permite mais que isso.

**Exercício 3**: Faça um programa para a leitura de duas notas parciais de um aluno. O programa deve calcular a média alcançada por aluno e apresentar:
A mensagem "Aprovado", se a média alcançada for maior ou igual a sete;
A mensagem "Reprovado", se a média for menor do que sete;
A mensagem "Aprovado com Distinção", se a média for igual a dez.

## Uma estrutura mais formal de decisão: switch

O comando switch testa sucessivamente o valor de uma expressão contra uma lista de constantes inteiras ou de caractere. Quando o valor coincide, os comandos associados são executados. Um switch pode ter pelo menos 257 comandos case (C ANSI).

Sua estrutura básica conta com os seguintes comandos:

```
switch (expr) {
  case const1:
	sequência de comandos;
	break;
  case const2:
	sequência de comandos;
	break;
	...
  case constn:
  	sequência de comandos;
	break;
  default:
	sequência de comandos;
    	break;
}
```

A expressão neste caso pode ser uma variável do tipo `int` ou um `char`, que podem ser recebidos do teclado ou não. 

As cláusulas (cases) `const1`, `const2`, ..., `constn` são espécies de `if` que avalia se a expressão é igual ao valor do `case`. Se algum dos valores corresponder, então o bloco será executado. 

A cláusula `default` deve sempre ser incluída, caso contrário, os valores não testados explicitamente em um `switch` e serão ignorados. E, por fim, em cada cláusula é necessário o uso da palavra reservada `break`.

Para entender melhor, veja o seguinte exemplo. Para resolver o problema do turno, poderíamos criar um `switch`, começando pelo caso padrão, onde nenhuma letra válida é identificada. 

```
#include <stdio.h>

int main() 
{
  int turno = 0;
  printf("Digite 1-Matutino, 2-Vespertino e 3-Noturno: ");
  scanf("%d", &turno);

  switch (turno) {
    
    default:
      printf("Valor Inválido!");
      break;
  }

  return 0;
}
```

Vamos incluir a primeira cláusula: ao reconhecer o valor 1, é impresso "Bom dia!";

```
#include <stdio.h>

int main() 
{
  int turno = 0;
  printf("Digite 1-Matutino, 2-Vespertino e 3-Noturno: ");
  scanf("%d", &turno);

  switch (turno) {
    case 1:
      printf("Bom dia!");
      break;
    default:
      printf("Valor Inválido!");
      break;
  }

  return 0;
}
```

Da mesma forma, podem ser incluídas as demais cláusulas:

```
#include <stdio.h>

int main() 
{
  int turno = 0;
  printf("Digite 1-Matutino, 2-Vespertino e 3-Noturno: ");
  scanf("%d", &turno);

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

**Exercício 4**: Simule, com a ajuda de um `switch`, um atendimento telefônico em um banco. São cinco opções: consultar saldo, fazer empréstimo, consultar fatura do cartão de crédito, pedir novo cartão ou falar diretamente com atendente, sendo esta última, a opção padrão.

**Desafio 1**: (Use `if` e `switch` em sua resolução) Faça um programa que lê as duas notas parciais obtidas por um aluno numa disciplina ao longo de um semestre, e calcule a sua média. A atribuição de conceitos obedece à tabela abaixo:

```
Média de Aproveitamento  Conceito
  Entre 9.0 e 10.0        A
  Entre 7.5 e 9.0         B
  Entre 6.0 e 7.5         C
  Entre 4.0 e 6.0         D
  Entre 4.0 e zero        E
```

O algoritmo deve mostrar na tela as notas, a média, o conceito correspondente e a mensagem “APROVADO” se o conceito for A, B ou C ou “REPROVADO” se o conceito for D ou E.
