#include <ctype.h>
#include <math.h>
#include <stdbool.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h> 

// Exercicio 1
void vetorIntercalado(){
  const int totalElementos = 20, metadeTotal = totalElementos / 2;
  int vetor1[metadeTotal], vetor2[metadeTotal], vetor3[totalElementos];
  int numeroAtual;
  
  for(int x = 0; x < totalElementos; x++){
    printf("Digite o %d° número \n", x + 1);
    scanf("%d", &numeroAtual);

    int indice = x % metadeTotal;
    
    if(x < metadeTotal){
      vetor1[indice] = numeroAtual;
    } else {
      vetor2[indice] = numeroAtual;
    }

  }

  for (int x = 0, y = 0; y < metadeTotal; x+= 2, y++){
    vetor3[x] = vetor1[y];
    vetor3[x + 1] = vetor2[y];
  }

   for (int x = 0; x < totalElementos; x++)
     printf("%d° Elemento = %d \n", x + 1, vetor3[x]);

}
// Exercicio 2
void lancamentoDados() { 
  const int quantidadeLancamentos = 100, faceDados = 6;
  int lancamentos[quantidadeLancamentos];
  int valores[faceDados] = {0};

  srand(time(NULL)); 
  
 for (int x = 0; x < quantidadeLancamentos; x++){
    int lancamento = (random() % faceDados) + 1;
    
    lancamentos[x] = lancamento;
    valores[lancamento - 1] += 1;
  }

  for (int x = 0; x < faceDados; x++)
     printf("Quantidade do valor %d = %d\n", x + 1, valores[x]);

}
// Exercicio 3
void nomeVertical(){
  char nome[100];

  printf("Digite seu nome \n");
  scanf("%[^\n]", nome);

  for (int x = 0; x < strlen(nome); x++)
    printf("%c\n", nome[x]);
}
// Desafio 1 
void pessoaCriminosa(){
  char perguntas[5][100] = {
    "Telefonou para a vítima?",
    "Esteve no local do crime?",
    "Mora perto da vítima?",
    "Devia para a vítima?",
    "Já trabalhou com a vítima?"
  };
  
  int gravidadeCrime = 0;  
  int atualRespota;  
    
  for(int x = 0; x < 5; x++){
    printf("%s\n", perguntas[x]);
    scanf("%d", &atualRespota);
  
    gravidadeCrime += atualRespota;
  }  
  
  switch(gravidadeCrime){
    case 2: {
      printf("Suspeita\n");
      break;
    }
    case 3:
    case 4:{
      printf("Cumplice\n");
      break;
    }
    case 5:{
      printf("Assasino\n");
      break;
    }
  
    default: {
      printf("Inocente\n");
      break;
    }
   } 
  
}
// Desafio 2
void valoresAleatorio(){
  
  int quantidadeValores = 0;
  int valorSaida = -1;
  int acimaMedia = 0;
  int abaixoNumeroQualquer = 0;
  int numeroQualquer = 7; 
  
  float valorAtual, somaTotal = 0, mediaValores;
  float valoresLidos[1000];  
  
   do {
     printf("Digite o %d° valor \n", quantidadeValores + 1);
     scanf("%f", &valorAtual);
  
      if(valorAtual != valorSaida){
         valoresLidos[quantidadeValores] = valorAtual;
         quantidadeValores++;
         somaTotal += valorAtual;
      } 
     
   } while(valorAtual != valorSaida);
  
    printf("\n");
    printf("Total de Valores = %d \n", quantidadeValores);
    printf("\n");
  
    for (int x = 0; x < quantidadeValores; x++)
      printf("Valor %d = %.2f ",x + 1, valoresLidos[x]);
  
    printf("\n\n");
    
    for (int x = quantidadeValores - 1; 0 <= x; x--)
      printf("Valor %d = %.2f\n", x + 1, valoresLidos[x]);
  
    printf("\n");
  
    printf("Soma total = %.2f\n\n", somaTotal);
  
    mediaValores = somaTotal / quantidadeValores;
    
    printf("Media dos valores = %.2f\n\n", mediaValores);
  
    for (int x = 0; x < quantidadeValores; x++){
      if(valoresLidos[x] > mediaValores){
        printf("Valor %.2f acima da média \n", valoresLidos[x]);
        acimaMedia++;
      } 
    }
  
    printf("\nQuantidade acima da media = %d\n\n", acimaMedia);
  
    for (int x = 0; x < quantidadeValores; x++){
      if(valoresLidos[x] < numeroQualquer){
        printf("Valor %.2f abaixo de %d \n", valoresLidos[x], numeroQualquer);
        abaixoNumeroQualquer++;
      } 
    }
  
    printf("\nQuantidade abaixo de %d = %d\n\n", numeroQualquer, abaixoNumeroQualquer);
  
}
// Desafio 3
void mostrarNomeEscadinha(){
  char nome[100];
  
  printf("Digite seu nome\n");
  scanf("%[^\n]", nome);

  int len = strlen(nome);

  for(int x = 0; x < len; x++){
    if(nome[x] == ' ')
       continue;
       
    for(int y = 0; x >= y; y++)
       printf("%c", nome[y]);
      
    printf("\n"); 
  }
  
  for(int x = len - 2; x >= 0; x--){ 
    if(nome[x] == ' ')
       continue;
    
    for(int y = 0; x >= y; y++)
        printf("%c", nome[y]);
      
    printf("\n"); 
  }

}

int main8(){

  return 0;
}