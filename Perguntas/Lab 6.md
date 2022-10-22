# Lab 6

A maioria dos programas de computador que resolvem problemas do mundo real são muito maiores do que os apresentados nos primeiros capítulos. A experiência mostrou que a melhor maneira de desenvolver e manter um programa é construí-lo a partir de partes menores, cada uma mais fácil de manter do que o programa original. Essa técnica é chamada de dividir e conquistar. Descreveremos alguns recursos-chave do C para projetar, implementar, operar e manter grandes programas.

## Como funcionam as funções já conhecidas

Em C, você usa funções para modularizar programas combinando as novas funções que você escreve com funções da biblioteca padrão de C, pré-empacotadas. A biblioteca padrão `stdio.h` C fornece uma rica coleção de funções para realizar cálculos matemáticos comuns, manipulações de strings, manipulações de caracteres, entrada/saída e muitas outras operações úteis. As funções pré-empacotadas facilitam seu trabalho porque fornecem muitos dos recursos de que você precisa.

As funções são invocadas por uma chamada de função, que especifica o nome da função e fornece informações (como argumentos) de que a função precisa para executar sua tarefa designada. Uma analogia comum para isso é a forma hierárquica de gerenciamento. Um chefe (a função de chamada ou chamador) pede a um trabalhador (a função de chamada) para executar uma tarefa e relatar quando estiver concluída.

Por exemplo, uma função que exibe dados na tela chama a função de trabalho printf para executar essa tarefa. A função printf exibe os dados e relata - ou retorna - ao chamador quando conclui sua tarefa. A função de chefe não sabe como a função de trabalhador executa sua tarefa designada. O trabalhador pode chamar outras funções de trabalhador, e o chefe não saberá disso.

O diagrama a seguir mostra uma função de chefe se comunicando hierarquicamente com várias funções de trabalhador:

![image](boss-workers.png)

As funções printf, scanf e pow que usamos nos roteiros anteriores são da biblioteca padrão. O trecho de código `main()` também é uma função, responsável pela execução do programa.

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

É interessante conhecer a rica coleção de funções da biblioteca padrão C para ajudar a reduzir o tempo de desenvolvimento do programa. Quando possível, use funções padrão em vez de escrever novas. As funções da biblioteca padrão C são escritas por especialistas, bem testadas e eficientes. Além disso, usar as funções da biblioteca padrão C ajuda a tornar os programas mais portáteis.

Não só as funções da biblioteca padrão, mas também da biblioteca `math.h`, que nos ajudam a desempenhar cálculos matemáticos. 

```
#include <stdio.h>
#include <math.h>

int main()
{  
  int numero;
  
  printf("Digite um número inteiro: ");
  scanf("%d", &numero);
  printf("%d", sqrt(numero)); // raiz quadrada
  printf("%d", log(numero)); // logaritmo natural
  printf("%d", log10(numero)); // logaritmo de base 10
  printf("%d", pow(numero, 2)); // potência
  
  return 0;
}
```

**Exercício 1**: Pesquise funções matemáticas de trigonometria e elabore um código com exemplos de uso delas.

## Definindo as primeiras funções 

Você pode definir funções para executar tarefas específicas que podem ser usadas em vários pontos de um programa. As instruções que definem a função são escritas uma vez e são ocultadas de outras funções. Como veremos, essa ocultação é crucial para uma boa prática de codificação na Engenharia de Software.

O formato de uma função geralmente cumpre o seguinte esquema: 

```
tipoDeRetorno nomeDaFuncao(parametros) {
  // codigo
  // return ;
}
```

Todo e qualquer código pode ser redefinido em uma função, com o intuito de reutilizar soluções para complementar outras, em uma estratégia de dividir para conquistar: nossa solução para um problema inteiro deverá ser composta de soluções menores (e reutilizáveis).

Vamos a um primeiro exemplo. No exercício 3 do Lab 1, foi pedido o seguinte: Tendo como dados de entrada o nome e a altura de uma pessoa, construa um programa que calcule seu peso ideal, usando a seguinte fórmula: (72.7*altura) - 58, sendo o resultado formatado com 3 dígitos dentro de uma mensagem final com o nome fornecido pelo usuário.

Pois bem, sua solução foi ligeiramente parecida ou igual a essa:

```
#include <stdio.h>


int main()
{  
  float altura = 0.0;
  printf("Digite sua altura: ");
  scanf("%f", &altura);
  
  float pesoIdeal = (72.7*altura) - 58;
  printf("O seu peso ideal é %.3f", pesoIdeal);
  
  return 0;
}
```

Dispensando o comentário de escopo (por enquanto), toda a nossa solução foi codificada dentro da função principal. Porém, e se precisássemos dessa funcionalidade mais de uma vez? Como ficaria nosso código? Repetir o código inteiro é uma solução?

```
#include <stdio.h>

int numero = 0;

int main()
{  
  float altura = 0.0;
  printf("Digite sua altura: ");
  scanf("%f", &altura);
  
  float pesoIdeal = (72.7*altura) - 58;
  printf("O seu peso ideal é %.3f", pesoIdeal);

  float altura = 0.0;
  printf("Digite sua altura: ");
  scanf("%f", &altura);
  
  float pesoIdeal = (72.7*altura) - 58;
  printf("O seu peso ideal é %.3f", pesoIdeal);

  // ... ?
  return 0;
}
```

Sim, mas não a melhor. Até, por que, se essa funcionalidade fosse invocada várias vezes em laços ou em condicionais, por exemplo, ficaria ainda mais difícil de manter e controlar qualquer mudança no código.

Vamos então criar uma função para informar qual o peso ideal de qualquer pessoa. Inicialmente, podemos extrair todo o código envolvido na solução para uma definição em um bloco à parte:

```
#include <stdio.h>

int numero = 0;

float calculaPesoIdeal(); // prototype 

int main()
{  
  float peso = calculaPesoIdeal(); // chamada
  
  return 0;
}

float calculaPesoIdeal() { // definição
  float altura = 0.0;
  printf("Digite sua altura: ");
  scanf("%f", &altura);
  
  float pesoIdeal = (72.7*altura) - 58;
  printf("O seu peso ideal é %.3f", pesoIdeal);

  return pesoIdeal;
}
```

Note que, além de definirmos a função, como estamos acostumados em linguagens mais simples, definimos um `prototype`, ou seja, uma espécie de "esqueleto" preparando o compilador para entender como será a nossa função. Isso é feito antes da função principal, onde chamamos nossa função `calculaPesoIdeal()`. 

Ela resolve o nosso problema. Porém, se quisermos que a função apenas execute os cálculos para a obtenção do peso ideal, sem pedir valores do usuário? 

```
#include <stdio.h>

float calculaPesoIdeal(); // prototype 

int main()
{  
  float peso = calculaPesoIdeal(); // chamada
  
  return 0;
}

float calculaPesoIdeal() { // definição
  float pesoIdeal = (72.7*altura) - 58; // altura?
  printf("O seu peso ideal é %.3f", pesoIdeal);

  return pesoIdeal;
}
```

É preciso que a função receba a altura antes de realizar o cálculo, como argumento, ou parâmetro.

```
#include <stdio.h>

float calculaPesoIdeal(float altura); // prototype 

int main()
{  
  float altura = 0.0;
  printf("Digite sua altura: ");
  scanf("%f", &altura);

  float pesoIdeal = calculaPesoIdeal(altura); // chamada
  printf("O seu peso ideal é %.3f", pesoIdeal);
  
  return 0;
}

float calculaPesoIdeal(float altura) { // definição
  float pesoIdeal = (72.7*altura) - 58;
  return pesoIdeal;
}
```

Note que "escondemos" o cálculo do peso ideal da função principal. Se houver alguma mudança interna, ela ficará encapsulada na função, cumprindo, assim, com boas práticas de código.

**Exercício 2**: Defina uma função que retorna o maior dentre 4 números inteiros passados como parâmetro.

**Exercício 3**: Modifique o exemplo da função do cálculo de peso para inserir condições entre peso ideal para mulheres e homens, aplicando as seguintes fórmulas - mulheres: (62.1\*altura) - 44.7 e  homens: (72.7*altura) - 58.

**Desafio 1**: Faça um programa que converta da notação de 24 horas para a notação de 12 horas. Por exemplo, o programa deve converter 14:25 em 2:25 P.M. A entrada é dada em dois inteiros. Seu programa deve contar com duas funções: uma para fazer a conversão e uma para a saída. Registre a informação A.M./P.M. como um valor ‘A’ para A.M. e ‘P’ para P.M. Assim, a função para efetuar as conversões terá um parâmetro formal para registrar se é A.M. ou P.M. Inclua um loop que permita que o usuário repita esse cálculo para novos valores de entrada todas as vezes que desejar.

**Desafio 2**: Pesquise qual a função para gerar números aleatórios e monte um "jogo" onde você tenta advinhar o número gerado pelo computador.

**Desafio 3**: Construa uma função que receba uma data no formato DD/MM/AAAA e devolva uma string no formato D de mesPorExtenso de AAAA. Opcionalmente, valide a data e retorne "NULL" caso a data seja inválida. Você pode utilizar funções da biblioteca <time.h>.