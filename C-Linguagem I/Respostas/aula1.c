#include <stdio.h>

int variavelGlobal;
int numero1, numero2, numero3;

int main1() {

  // -- Exercicio 1

  printf("Digite o número: \n");
  scanf("%d", &variavelGlobal);
  printf("O número informado é %d\n", variavelGlobal);

  //-- Exercicio 2

  printf("Digie o primeiro número \n");
  scanf("%d", &numero1);

  printf("Digie o primeiro número \n");
  scanf("%d", &numero2);

  printf("Digie o primeiro número \n");
  scanf("%d", &numero3);

  int resultado = (numero1 - numero2) * numero3;

  printf("A soma de (%d - %d) * %d é %d \n", numero1, numero2, numero3,
         resultado);

  // - Exercicio 3

  char nomeUser[100];
  float alturaUser;

  printf("Digite o seu nome \n");
  scanf("%s", &nomeUser);

  printf("Digite sua altura \n");
  scanf("%f.3f", &alturaUser);

  float pesoIdeal = (72.7 * alturaUser) - 58;
  printf("%s seu peso ideal é %.3f \n", nomeUser, pesoIdeal);

  //-- Desafio 1

  float temperaturaF;

  printf("Digite a temperatura em °F \n");
  scanf("%f", &temperaturaF);

  float conversaoCelsius = (5 * (temperaturaF - 32)) / 9;

  printf("A conversão de %.2f°C para °F é %.2f\n", temperaturaF,
         conversaoCelsius);

  //-- Desafio 2

  float salarioPorHora, totalHorasTrabalhadas, impostoRenda, imposto_inss;

  printf("Quanto você ganha por hora \n");
  scanf("%f", &salarioPorHora);
  printf("Digite o total de horas Trabalhadas \n");
  scanf("%f", &totalHorasTrabalhadas);
  printf("Digite a quantidade de imposto de renda \n");
  scanf("%f", &impostoRenda);
  printf("Digite a quantidade de imposto INSS \n");
  scanf("%f", &imposto_inss);

  float salarioBruto = salarioPorHora * totalHorasTrabalhadas;
  float porcetagemImpostoRenda = (impostoRenda / 100) * salarioBruto;
  float porcetagem_inss = (imposto_inss / 100) * salarioBruto;
  float salarioLiquido = salarioBruto - (porcetagemImpostoRenda + porcetagem_inss);
  
  printf("Salario Bruto: R$ %.2f\nIR: R$ %.2f\nINSS: R$ %.2f\nSalário Liquido: R$ %.2f\n",salarioBruto, porcetagemImpostoRenda, porcetagem_inss, salarioLiquido);

  return 0;
}