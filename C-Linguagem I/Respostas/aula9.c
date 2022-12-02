#include <ctype.h>
#include <math.h>
#include <stdbool.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h> 

// Exercicio 1
void calculoExpressao(int *p1, int *p2){
    printf("Subtração: %d\n", *p1 - *p2);
    printf("Valor: %d\n", **&p1);
    printf("Expressão: %d\n", 3 * - *p1 / (*p2) + 7);
}
// Exercicio 2
void areaPerimetroCirculo(float *raio){
   const float pi = 3.14; 
   float area = 2 * pi * (*raio);
   float perimetro = pi * pow(*raio, 2); 
    
   printf("Area: %.2f\n", area);
   printf("Perimetro: %.2f\n", perimetro); 
}
// Desafio 1
void total_vogal_consoantes(char *palavra) {
    int totalVogais = 0, totalConsoantes = 0;
    char alfabeto[26] = "aeioubcdfgjklmnpqrstvwxyz";

    while(*palavra != '\0'){
      
      for(int x = 0; x < 25; x++) {
        char letra = alfabeto[x];
  
        if(*palavra == letra || *palavra == toupper(letra)){
           x <= 4 ? totalVogais++ : totalConsoantes++;
           break;
        }
      }  
      
      palavra++;
    }

   printf("Total de Vogais: %d\n", totalVogais);
   printf("Total de Consoantes: %d", totalConsoantes);
}


int main9(){

  return 0;
}