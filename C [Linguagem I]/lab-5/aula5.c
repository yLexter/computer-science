#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <string.h>

int main5() {

  // Exercicio 1

  float somaTotal = 0, currentNumber = 0, totalNumeros = 5;

  for (int x = 0; x < totalNumeros; x++) {
    printf("Digite o %d° numero \n", x + 1);
    scanf("%f", &currentNumber);

    somaTotal += currentNumber;
  }

  printf("A soma total é %.2f \n", somaTotal);

  // Exercicio 2

    int totalNumero_1;
    float somatorio_1 = 0, currentNumber_2;

    printf("Digite a quantidade de numeros \n");
    scanf("%d", &totalNumero_1);

    while (totalNumero_1 > 0){

      printf("Digite o %d° numero \n", totalNumero_1);
      scanf("%f", &currentNumber_2);

      somatorio_1 += currentNumber_2;

      totalNumero_1--;
    }
  
    printf("o somatorio é %.2f \n", somatorio_1);

  // exercicio  3

  int quantidadeNumeros;
  float media, currentNumber1, somaTotal1 = 0;

  printf("Digite o total de numeros \n");
  scanf("%d", &quantidadeNumeros);

  for (int y = 0; y < quantidadeNumeros; y++) {
    printf("Digite o %d° numero \n", y + 1);
    scanf("%f", &currentNumber1);

    somaTotal1 += currentNumber1;
  }

  media = somaTotal1 / quantidadeNumeros;

  printf("A medida total dos numeros é %.2f \n", media);

  // exercicio 4

  int fatorialUser, fatorial = 1;

  printf("Digite o fatorial que deseja calcular \n");
  scanf("%d", &fatorialUser);

  for (int z = fatorialUser; z > 1; z--) 
    fatorial *= z;
  
  printf("O fatorial de %d é %d \n", fatorialUser, fatorial);

  // Exercicio 5

  int counter;
  float somatoria = 0, currentNumber2;

  printf("Digite a quantidade de numeros que deseja \n");
  scanf("%d", &counter);

  do {
    printf("Digite o %d numero \n", counter);
    scanf("%f", &currentNumber2);

    somatoria += currentNumber2;

  } while (counter-- > 1);

  printf("A somatoria dos numeros é %f \n", somatoria);

  // Exercicio 6

  // Contador inicia em 1 e vai até 5 mas como tem um break = a 3 quando contador chegar a 3 ele sai do laço

   for (int contador_1 = 1; contador_1 <= 5; ++contador_1) {
    
   if (contador_1 == 3)
      break;
     
     printf("%d\n", contador_1);
  }

  printf("\n");

  // Se o if estivesse em cima, o contador pularia para proxima interação sem executa o printf de 3;

  for (int contador_2 = 1; contador_2 <= 5; ++contador_2) {
      
    printf("%d\n", contador_2);

    if (contador_2 == 3)
        continue;
    
  }

  // Desafio 1

  int number1, number2, resultado = 1;

  printf("Digite a base \n");
  scanf("%d", &number1);

  printf("Digite o expoente \n");
  scanf("%d", &number2);

  for (int m = number2; m > 0; m--) 
    resultado *= number1;
  
  printf("%d elevado a %d é %d \n", number1, number2, resultado);
}