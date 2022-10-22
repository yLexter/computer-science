#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <string.h>

int main4() {

  // Exercicio 1

  char produto1[100], produto2[100];
  float preco1, preco2;

  printf("Nome do 1° produto \n");
  scanf("%s", produto1);

  printf("Preço do 1° Preço \n");
  scanf("%f", &preco1);

  printf("Nome do 2° produto \n");
  scanf("%s", produto2);

  printf("Preço do 2° Produto \n");
  scanf("%f", &preco2);

  preco1 == preco2 ? printf("Preço igual \n") :
  preco1 > preco2 ?
      printf("%s com preço %.2f é mais barato", produto1, preco1) :
      printf("%s com preço %.2f é mais barato", produto2, preco2);

  // Exercicio 2

  int totalProdutos = 3;

  const char nomesProdutos[totalProdutos][50];
  const float precosProdutos[totalProdutos];

  for (int x = 0; x < totalProdutos; x++) {
    printf("Digite o nome do %d° primeiro \n", x + 1);
    scanf("%s", &nomesProdutos[x]);

    printf("Digite o preco do %d° produto \n", x + 1);
    scanf("%f", &precosProdutos[x]);
  }

  float maiorPreco = precosProdutos[0];
  int indiceMaiorPreco = 0;
  bool precoIguais = true;

  for (int y = 0; y < totalProdutos; y++) {

    if (precoIguais && maiorPreco != precosProdutos[y]) {
      precoIguais = false;
    }

    if (precosProdutos[y] > maiorPreco) {
      maiorPreco = precosProdutos[y];
      indiceMaiorPreco = y;
    }
  }

  if (precoIguais) {
    printf("Todos os produtos tem preços iguais \n");
  } else {
    printf("O produto com maior preço é %s com preço de %.2f \n",
           nomesProdutos[indiceMaiorPreco], precosProdutos[indiceMaiorPreco]);
  }

 // Desafio 1 

  int totalLadosTriangulo = 3;
  float ladosTriangulos[totalLadosTriangulo];

  for (int a = 0; a < totalLadosTriangulo; a++) {
    printf("Digite a medida do %d° triangulo \n", a + 1);
    scanf("%f", &ladosTriangulos[a]);
  }

  bool trianguloInvalido =
      ladosTriangulos[0] > ladosTriangulos[1] + ladosTriangulos[2] ||
      ladosTriangulos[1] > ladosTriangulos[0] + ladosTriangulos[2] ||
      ladosTriangulos[2] > ladosTriangulos[1] + ladosTriangulos[0];

  bool trianguloEquilatero = 
      ladosTriangulos[0] == ladosTriangulos[1] &&
      ladosTriangulos[1] == ladosTriangulos[2] && 
      ladosTriangulos[0] == ladosTriangulos[2];

  bool trianguloIsosceles = 
     ladosTriangulos[0] == ladosTriangulos[1] ||
     ladosTriangulos[1] == ladosTriangulos[2] || 
     ladosTriangulos[0] == ladosTriangulos[2];

  if (trianguloInvalido) {
    printf("Medidas invalidas para formar um triangulo \n");
  } else if (trianguloEquilatero) {
    printf("Triangulo equilatero \n");
  } else if (trianguloIsosceles) {
    printf("Triangulo isosceles \n");
  } else {
    printf("Triangulo escaleno \n");
  }

  // exercicio 3
  
  char usuario[100], senha[100];
  bool inputInvalido = true;

  while (inputInvalido){
    printf("Digite o nome de usuario \n");
    scanf("%s", usuario);

    printf("Digite a sua senha \n");
    scanf("%s", senha);

    if (usuario == EOF || senha == EOF){
      printf("Informe os dados corretamente \n");
      continue;
    }

    if (!strcmp(usuario, senha)){
       printf("Nome de usuario e senha não podem ser iguais \n");
       continue;
    }

    inputInvalido = false;
    
  }

  // Desafio 2
  // Por algum motivo o scanf não pega aq, tem q pegar o trecho de codigo e rodar em outro ambiente

  int quantidadeMaiusculas = 0;
  int quantidadeMinisculas = 0;

  char vogais[5] = "aeiou";
  char input[100];

  printf("Digite uma frase \n");
  scanf("%[^\n]", input);

  for (int vog = 0; vog < strlen(vogais); vog++) {

    for (int in = 0; in < strlen(input); in++) {
      char letra = input[in];
      char vogal = vogais[vog];

      if (letra == vogal) quantidadeMinisculas++;
      if (letra == toupper(vogal)) quantidadeMaiusculas++;
    }
    
  }

  printf("Quantidade de maiusculas = %d\nQuantidade de minusculas = %d\n",
         quantidadeMaiusculas, quantidadeMinisculas);


  
}