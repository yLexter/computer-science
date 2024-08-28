#include <ctype.h>
#include <math.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// Exercicio 1
bool anoBissexto(int ano){
  return ano % 400 == 0 || (ano % 4 == 0 && ano % 100 != 0);
}

// Exercicio 2
void imprimePotencia(int inicial, int final, int expoente){
  for (int x = inicial; x <= final; x++){
    int resultado = pow(x, expoente);
    printf("%d \n", resultado);
  }
}

// Desafio I
bool isPrimo(int numero){
  if(numero == 1)
    return false;
  
  for(int x = 2; ((numero / 2) + 1) > x; x++){
     if (numero % x == 0)
        return false;
  }
  
  return true;
}

// Exercicio 3
int somatoriaRecursiva(int valorFinal){
   if(valorFinal == 1)
     return 1;
  return valorFinal + somatoriaRecursiva(valorFinal - 1);
}

// Desafio 2
int fibonachi(int x){
   if(x <= 1)
     return 1;
  return fibonachi(x - 1) + fibonachi(x - 2);
}

// Desafio 3
void quantidadePrimos1_100(){
  int count = 0;

  for (int x = 1; x <= 100; x++){
    if (isPrimo(x)) count++;
  }

  printf("%d \n", count);
}

int main7(){

  return 0;
}