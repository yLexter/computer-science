#include <stdio.h>
#include <string.h>

unsigned long int numeroA;
float numeroB;

int main2() {

  // exercicio1

  // Existe um tipo que só aceita int negativo ou eu teria q fazer um if pra saber se é positivo ou negativo?

  printf("Digite o numero A \n");
  scanf("%lu", &numeroA);

  printf("Digite o numero B \n");
  scanf("%f", &numeroB);

  int aux = numeroA;
  numeroA = numeroB;
  numeroB = aux;

  printf("%lu %f \n", numeroA, numeroB);
  printf("%s \n", numeroA == numeroB ? "true" : "false");

  printf("\n\n");
  
  // exercicio 2

  float a, b;

  printf("digite o valor de A \n");
  scanf("%f", &a);

  printf("digite o valor de B \n");
  scanf("%f", &b);

  int first_b = a * (a++);
  printf("valor 1 %d \n", first_b);
  a--;
  int two_b = a * ++a;
  printf("valor 2 %d \n", two_b);
  a--;
  int three_b = a * a++;
  printf("valor 3 %d \n", three_b);
  printf("\n\n");


  // exercicio 3  --------------

  float numero1, numero2;

  printf("Digite o primeiro numero \n");
  scanf("%f", &numero1);

  printf("Digite o segundo numero \n");
  scanf("%f", &numero2);

  printf("%d \n", numero1 == numero2);

  // desafio 1 ----------------------

  printf("%s \n", numero1 == numero2 ? "true" : "false");
  printf("\n\n");


  // exercicio 4

  float numeroPositivoOuNegativo;
  printf("Digite o numero para saber se é positivo ou negativo \n");
  scanf("%f", &numeroPositivoOuNegativo);

  printf("%s \n",numeroPositivoOuNegativo == 0 ? "neutro" : numeroPositivoOuNegativo < 0 ? "numero negativo" : "numero positivo");
  printf("\n\n");


  // desafio 2

  char horario[2];

  printf("digite o horario que voce estuda \n");
  scanf("%s", horario);

  if (!strcmp(horario,"M-")) {
    printf("Bom dia ");
  } else if (!strcmp(horario, "V-")) {
    printf("Boa tarde");
  } else if (!strcmp(horario,"N-")) {
    printf("Boa noite");
  } else {
    printf("o horario fornecido e invalido");
  }

  return 0;
}