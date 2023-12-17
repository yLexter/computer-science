#include <ctype.h>
#include <math.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// Exercicio 1
void leiCossenos(float b, float c, float anguloA) {
  const float pi = 3.1415;
  float anguloRadianos = (pi * anguloA) / 180;
  float aQuadrado = pow(b, 2) + pow(c, 2) - (2 * b * c * cos(anguloRadianos));
  float ladoTriangulo = sqrt(aQuadrado);
  printf("A = %.1f", ladoTriangulo);
}
// Exercicio 2
int maiorInt(int value1, int value2, int value3, int value4) {
  const int totalValores = 4;
  int maiorValor = value1;
  int todosNumeros[totalValores] = {value1, value2, value3, value4};

  for (int x = 0; x < totalValores; x++) {
    if (maiorValor < todosNumeros[x]) {
      maiorValor = todosNumeros[x];
    }
  }

  return maiorValor;
}
// Exercicio 3
void pesoIdeal(float altura, char sexo) {

  float pesoIdeal;

  if (sexo == 'H') {
    pesoIdeal = (72.7 * altura) - 58;
    printf("O peso ideal para homens de %.2f de altura é ideal é %.2f", altura,
           pesoIdeal);
    return;
  }

  if (sexo == 'M') {
    pesoIdeal = (62.1 * altura) - 44.7;
    printf("O peso ideal para mulheres de %.2f de altura é ideal é %.2f",
           altura, pesoIdeal);
    return;
  }

  printf("Não sei que sexo você é mas eu so aceito H ou M");
}
// Desafio 1
void jogoRandom() {
  srand(time(NULL));

  int randomNumero = rand() % 20;
  int numeroEscolhido = -1;

  while (numeroEscolhido != randomNumero) {
    printf("Digite o número \n");
    scanf("%d", &numeroEscolhido);

    if (numeroEscolhido == randomNumero) {
      printf("Parabens! Você conseguiu acertar o número: %d\n", randomNumero);
      continue;
    }

    int moduloNumero = abs(randomNumero - numeroEscolhido);

    if (moduloNumero == 1) {
      printf("Você está muitp peerto! \n");
    } else if (moduloNumero == 2) {
      printf("Ta chegando perto hein \n");
    } else if (moduloNumero == 3) {
      printf("Ta chegando perto\n");
    } else if (moduloNumero == 4) {
      printf("Está um pouco longe \n");
    } else if (moduloNumero == 5) {
      printf("Ta longe \n");
    } else if (moduloNumero == 6) {
      printf("Muito longe \n");
    } else {
      printf("Muito muito longe \n");
    }
  }
}
// Desafio 2
void converterHora(int hour, int minute, char horario) {
  printf("%d:%d %s", hour, minute, horario == 'P' ? "PM" : "AM");
}
void conversaoHora(int hour, int minute) {

  if (hour >= 24 || hour < 0) {
    printf("A hora precisa ser maior que 0 e menor q 24");
    return;
  }

  if (minute < 0 || minute >= 60) {
    printf("O minuto precisa ser maior que 0 e menor que 60");
    return;
  }

  char time = hour >= 12 ? 'P' : 'A';
  int horaFinal = hour == 0 ? 12 : hour % 12;

  converterHora(horaFinal, minute, time);
}
// Desafio 3
void mesPorExtenso(char *pointer, int dia, int mes, int ano) {

  char meses[12][100] = {
      "Janeiro", "Fevereiro", "Março",  "Abril",  "Maio",  "Junho",
      "Julho",  "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
  };

  if (mes < 1 || mes > 12) {
    printf("Mês núlo\n");
  } else {
    snprintf(pointer, 100, "%d de %s de %d", dia, meses[mes - 1], ano);
  }
}

int main7() { return 0; }