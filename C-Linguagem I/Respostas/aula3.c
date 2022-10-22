#include <stdint.h>

int main3() {

  // Exercicio 1
  int numero;

  printf("Digite um valor: \n");
  scanf("%d", &numero);

  int multiplo_3_4_5 = numero % 2 == 0 || numero % 3 == 0 || numero % 4 == 0;

  printf("%s \n", !multiplo_3_4_5 ? "não è multiplo" : "é multiplo");

  // exercicio 2

  int horario;

  printf("Qual seu Horario ?\n1-Matutino\n2-Vespertino\n3-Noturno\n");
  scanf("%d", &horario);

  horario == 1 ? printf("Bom Dia \n") :
  horario == 2 ? printf("Boa Tarde \n") :
  horario == 3 ? printf("Boa Noite \n") : printf("Horario Invalido \n");  
  
  // exercecio 3

  float nota1, nota2;

  printf("Digite a 1° nota \n");
  scanf("%f", &nota1);

  printf("Digite a 2° nota \n");
  scanf("%f", &nota2);

  float media = (nota1 + nota2) / 2;

  media == 10 ? printf("Aprovado com Distinção \n") : 
  media >= 7 ? printf("Aprovado \n") : printf("Reprovado \n");

  // exercicio 4

  int opcao;

  printf("1- consultar saldo \n2- fazer emprestimo \n3- consultar fatura do cartao de credito \n4- pedir novo cartao \n");
  scanf("%d", &opcao);

  switch (opcao) {
    case 1:
      printf("Você escolhei consultar saldo \n");
      break;
  
    case 2:
      printf("Voce escolhei fazer emprestimo \n");
      break;
  
    case 3:
      printf("Voce escolhei consultar fatura do cartao \n");
      break;
  
    case 4:
      printf("Voce escolheu pedir novo cartao \n");
      break;
  
    default:
      printf("Voce escolheu uma opcao invalida, voce falara agora com o "
             "atendente de telemarketing, divirta-se \n");
      break;
    }

  // desafio 1

  float nota_1, nota_2, media_1;
  char conceito;

  printf("Digite a 1° nota \n");
  scanf("%f", &nota_1);

  printf("Digite a 2° nota \n");
  scanf("%f", &nota_2);

  media_1 = (nota_1 + nota_2) / 2;

  conceito = 
    media_1 >= 9 && media_1 <= 10 ? 'A' : 
    media_1 >= 7.5 && media_1 < 9 ? 'B' : 
    media_1 >= 6 && media_1 < 7.5 ? 'C' : 
    media_1 >= 4 && media_1 < 6   ? 'D' : 
    media_1 >= 0 && media_1 < 4   ? 'E' : 'N';

  switch (conceito) {
    case 'A':
    case 'B':
    case 'C': {
      printf("Aprovado \n");
      break;
    }
  
    case 'D':
    case 'E': {
      printf("Reprovado \n");
      break;
    }
  
    default:
      printf("A soma das notas foram maiores que 20 ou menores que 0 \n");
      break;
    }

  return 0;
}