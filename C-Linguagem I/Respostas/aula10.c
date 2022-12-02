#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

// Exercicio 1
// Erro: Variavel de leitura não pode ser modificada.
void modificaCaracteres(const char *sPtr) {
  while (*sPtr != '\0') {
  //  *sPtr = '*';
    sPtr++;
  }
}

// Exercicio 2
// Nada mudou, imprime apenas as minusculas.
void imprimeMinuscula(char const *sPtr) {
  while (*sPtr != '\0') {
    if(islower(*sPtr))
      printf("%c", *sPtr);
    sPtr++;
  }
}

// Exercicio 3
// Erro: Não é possivel modificar a variavel sPtr
void imprimeMinuscula2(const char *const sPtr) {
  while (*sPtr != '\0') {
    if(islower(*sPtr))
      printf("%c", *sPtr);
  // sPtr++;
  }
}

// Exercicio 
// Houve mudança apenas nos 2 ultimos caracteres
void perccorrerArray(){
  char nome[] = "cinco";
  float numeros[] = { 1.5, 2.4, 3.2, 4.9, 5.9 };
  double numeros2[] = { 9.55, 4.8, 2.4, 3.2, 4.9 };

  for(int x = 0; x < 5; x++){
    printf("Endereco Char: %p\n", &nome[x]);
    printf("Endereco float: %p\n", &numeros[x]);
    printf("Endereco double: %p\n", &numeros2[x]);
    printf("\n");
  }
}

int main10() {

}