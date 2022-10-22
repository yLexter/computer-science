#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <string.h>
#include <math.h>
#include <time.h>
#include <stdlib.h>

// Exercicio 1
void leiCossenos(float b, float c, float anguloA){
  const float pi = 3.1415;
  float anguloRadianos = (pi * anguloA) / 180;
  float aQuadrado = pow(b,2) + pow(c,2) - (2*b*c * cos(anguloRadianos));
  float ladoTriangulo = sqrt(aQuadrado);
  printf("A = %.1f", ladoTriangulo);
}
// Exercicio 2  
int maiorInt(int value1, int value2, int value3, int value4){
   const int totalValores = 4;
   int maiorValor = value1;
   int todosNumeros[totalValores] = {value1,value2,value3,value4};

   for (int x = 0; x < totalValores; x++){
     if (maiorValor < todosNumeros[x]){
        maiorValor = todosNumeros[x];
     }
   }

  return maiorValor;
}
// Exercicio 3
void pesoIdeal(float altura, char sexo){

  float pesoIdeal;
  
  if (sexo == 'H'){
   pesoIdeal = (72.7*altura) - 58;
   printf("O peso ideal para homens de %.2f de altura é ideal é %.2f", altura, pesoIdeal);
   return;
  }

  if(sexo == 'M'){
    pesoIdeal = (62.1*altura) - 44.7;
    printf("O peso ideal para mulheres de %.2f de altura é ideal é %.2f", altura, pesoIdeal);
    return;
  }

  printf("Não sei que sexo você é mas eu so aceito H ou M");
}
// Desafio 1
void jogoRandom(){
  int randomNumber = rand() % 20;
  int inputUser = -1;

  while(inputUser != randomNumber){
    printf("Digite o número \n");
    scanf("%d", &inputUser);

    if(inputUser == randomNumber){
      printf("Parabens! Você conseguiu Acertar o numero: %d\n", randomNumber);
      continue;
    }

    int diffNumber = abs(randomNumber - inputUser);

    if (diffNumber == 1){
      printf("Você está muitp peerto! \n");
    } else if (diffNumber == 2) {
      printf("Ta chegando perto hein \n");
    } else if(diffNumber == 3){
      printf("Ta chegando perto\n");
    } else if (diffNumber == 4){
      printf("Está um pouco longe \n");
    } else if (diffNumber == 5) {
      printf("Ta longe \n");
    } else if (diffNumber == 6){
      printf("Muito longe \n");
    } else {
      printf("Muito muito longe \n");
    }
  }
}
// Desafio 2
void convert(int hour, int minute, char horario){
  printf("%d:%d %s", hour, minute, horario == 'P' ? "PM" : "AM");
}
void dateConvert(int hour, int minute){
  if (hour >= 24 || hour < 0){
    printf("A hora precisa ser maior que 0 e menor q 24");
    return;
  }

  if (minute < 0 || minute >= 60){
    printf("O minuto precisa ser maior que 0 e menor que 60");
    return;
  }

  char time = hour >= 12 ? 'P' : 'A';
  int horaFinal = hour == 0 ? 12 : hour % 12;

  convert(horaFinal, minute, time);
}
// Desafio 3
void mesPorExtenso(char *pointer,int dia, int mes, int ano) {

  char formartString[25];
  
  switch (mes) {
    case 1:
      snprintf(formartString, 25, "%d de Janeiro de %d", dia, ano);
      pointer = formartString;
      break;
    case 2:
      snprintf(formartString, 25, "%d de Fevereiro de %d", dia, ano);
      pointer = formartString;
      break;
    case 3:
      snprintf(formartString, 25, "%d de Março de %d", dia, ano);
      pointer = formartString;
      break;
    case 4:
      snprintf(formartString, 25, "%d de Abril de %d", dia, ano);
      pointer = formartString;
      break;
    case 5:
      snprintf(formartString, 25, "%d de Maio de %d", dia, ano);
      pointer = formartString;
      break;
    case 6:
      snprintf(formartString, 25, "%d de Jinho de %d", dia, ano);
      pointer = formartString;
      break;
    case 7:
      snprintf(formartString, 25, "%d de Julho de %d", dia, ano);
      pointer = formartString;
      break;
    case 8:
      snprintf(formartString, 25, "%d de Agosto de %d", dia, ano);
      pointer = formartString;
      break;
    case 9:
      snprintf(formartString, 25, "%d de Setembro de %d", dia, ano);
      pointer = formartString;
      break;
    case 10:
      snprintf(formartString, 25, "%d de Outubro de %d", dia, ano);
      pointer = formartString;
      break;
    case 11:
      snprintf("%d de Novembro de %d",dia, ano);
      pointer = formartString;
      break;
    case 12:
      snprintf(formartString, 25, "%d de Dezembro de %d", dia, ano);
      pointer = formartString;
      break;
    default:
      snprintf(formartString, 25, "NULL");
      pointer = formartString;
      break;
    }
}


int main6(){  
  
  
  return 0;
}