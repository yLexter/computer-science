#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

// Exercicio 1
void abrirArquivo(){
  FILE *arquivo = fopen("lab11.txt", "w");

  if (arquivo ==   NULL) {
    printf("Não foi possivel abrir o arquivo \n");
    return;
  }

  fprintf(arquivo, "Estou aprendendo a escrever em arquivos usando C.");
  fclose(arquivo);
}

// Exercicio 2
// Ele escreve na ultima linha, ou seja, junto com a ultima linha do arquivo
void abrirArquivoDois() {
  FILE *arquivo = fopen("lab11.txt", "a");

  if (arquivo ==   NULL) {
    printf("Não foi possivel abrir o arquivo \n");
    return;
  }

  fprintf(arquivo, "Estou aprendendo a escrever em arquivos usando C.");
  fclose(arquivo);
}

// Exercicio 3
void obterMedia(){
  FILE *arquivo = fopen("notas.txt", "rt");
  char nota[100];
  float media = 0;

  if (arquivo == NULL) {
    printf("Não foi possivel abrir o arquivo\n");
    return;
  }

  while(fscanf(arquivo, "%s", nota) > 0)
    media += atof(nota);
    
  printf("A media é %.2f", media);
}

// Exercicio 4
// A diferença é que o fgets pega toda a frase e o scanf pega 
// até o primeiro espaço encontrado
void lerArquivo() {
  char linha[100];
  char linha2[100];

  FILE *arquivo = fopen("lab11.txt", "rt");

  fgets(linha, 100, arquivo);
  printf("%s", linha);

  fseek(arquivo, 0, SEEK_SET);

  fscanf(arquivo, "%s", linha2);
  printf("%s", linha2);

  fclose(arquivo);  
}

// Desafio 1
void escreverNoFinal() {
   FILE *arquivo = fopen("lab11.txt", "a");

  if (arquivo == NULL) {
    printf("Não foi possivel abrir o arquivo\n");
    return;
  }

  fputs("Estou aprendendo a escrever em arquivos usando C.", arquivo);
  fclose(arquivo);
} 

// Desafio 2
void copiaArquivo(char *origem, char *copia){
  char linha[100];
  FILE *arquivoOrigem = fopen(origem, "rt");

  if (arquivoOrigem == NULL) {
    printf("Não foi possivel abrir o arquivo\n");
    return;
  }

  FILE *arquivoCopia = fopen(copia, "w");

  if (arquivoOrigem == NULL) {
    printf("Não foi possivel abrir o arquivo\n");
    return;
  }
  
  while(fgets(linha, 100, arquivoOrigem) != NULL)
    fprintf(arquivoCopia, "%s", linha);
  
  fclose(arquivoCopia);
  fclose(arquivoOrigem);
}

int main11() {

}