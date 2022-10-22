#include <stdio.h>

int main0(void) {

  // Exercicio 1


  printf("Frase 1\n");
  printf("Frase 2\n");


  // Exercicio 2


  int numero1, numero2, numero3;

  printf("Digie o primeiro número \n");
  scanf("%d", &numero1);

  printf("Digie o primeiro número \n");
  scanf("%d", &numero2);

  printf("Digie o primeiro número \n");
  scanf("%d", &numero3);

  int resultado1 = (numero1 - numero2) * numero3;

  printf("A soma de (%d - %d) * %d é %d \n", numero1,numero2,numero3,resultado1);


  // Desafio Final

  int expoente;
  const int constante = 2;

  printf("Digite o expoente\n");
  scanf("%d", &expoente);

  int resultado = pow(constante, expoente);

  printf("O Resulado de %d ^ %d é %d", constante, expoente, resultado);

  return 0;
}



