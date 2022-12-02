# Lab 11

Arquivos são usados para retenção permanente de grandes quantidades de dados. Os computadores armazenam arquivos em dispositivos de armazenamento secundário, como unidades de estado sólido, unidades flash e discos rígidos.

Nesse roteiro iremos trabalhar com arquivos, entendendo uma forma de armazenamento persistente para os dados dos nossos programas.

## Lidando com arquivos na linguagem C

C visualiza cada arquivo como um fluxo sequencial de bytes (stream). Streams fornecem canais de comunicação entre arquivos e programas. 

Quando um arquivo é aberto, um fluxo é associado ao arquivo. Três fluxos são abertos automaticamente quando a execução do programa começa — a entrada padrão, a saída padrão e o erro padrão. O fluxo de entrada padrão permite que um programa leia dados do teclado, e o fluxo de saída padrão permite que um programa imprima dados na tela. 

Abrir um arquivo retorna um ponteiro para uma estrutura FILE (definida em <stdio.h>) que contém as informações usadas para processar o arquivo. Essa estrutura inclui um descritor de arquivo - um índice em uma matriz do sistema operacional chamada de tabela de arquivo aberto. Cada elemento da matriz contém um bloco de controle de arquivo que o sistema operacional usa para administrar um determinado arquivo.

```
#include <stdio.h>

int main (void) {

  FILE *file;

  // caminho até o arquivo incluindo o nome
  // modo de abertura: read, write ou append
  file = fopen("caminhho/ate/o/arquivo/meu_arquivo.txt", "w"); 

  return 0;
}
```

Como no exemplo acima, é necessário um novo tipo de dados, o tipo FILE, para acessar o arquivo, por meio de um ponteiro que aponta para esse dado.

Observe que abrir um arquivo é atribuir ao ponteiro a chamada da função `fopen`, que tem dois parâmetros: o caminho até o arquivo (no Windows, deve-se separar as pastas com duas barras e, no Linux, ...) contendo, inclusive, o nome do arquivo, e o modo de abertura, que pode ser de leitura (r - read), escrita (w - write) ou adição (a - append). Nesse caso, estamos atribuindo a função de escrita, onde um novo arquivo será criado.

Caso não especifiquemos o caminho completo, o arquivo será criado no mesmo diretório que está rodando o nosso programa.

```
#include <stdio.h>

int main (void) {

  FILE *file;

  // caminho até o arquivo incluindo o nome
  // modo de abertura: read, write ou append
  file = fopen("meu_arquivo.txt", "w"); 

  return 0;
}
```

## Guardando informação em arquivos

Para guardar uma informação em um arquivo basta apenas utilizar o mesmo princípio de imprimir uma informação no terminal. Isso mesmo, iremos utilizar a função `printf`, mas com um diferencial.

```
#include <stdio.h>

int main (void) {

  FILE *file;

  // caminho até o arquivo incluindo o nome
  // modo de abertura: read, write ou append
  file = fopen("meu_arquivo.txt", "w"); 

  fprintf(file, "Aula de Programação em C");
  
  return 0;
}
```

A função `fprintf` recebe o arquivo que irá ser escrito e a informação a ser guardada, como parâmetro. 

Para fechar esse exemplo, lembre-se de fazer como estamos acostumados em um editor de texto, de salvar as alterações quando clicamos para fechar. Na linguagem C, isso é equivalente a chamar a função `fclose` passando o nosso ponteiro como parâmetro.

```
#include <stdio.h>

int main (void) {

  FILE *file;

  // caminho até o arquivo incluindo o nome
  // modo de abertura: read, write ou append
  file = fopen("meu_arquivo.txt", "w"); 

  fprintf(file, "Aula de Programação em C");

  fclose(file);
  
  return 0;
}
```

Isso é só por segurança. Caso você não invoque essa função, as coisas acontecem normalmente. 

**Exercício 1**: Replique o exemplo acima escrevendo em um arquivo chamado "lab11.txt" a seguinte frase: "Estou aprendendo a escrever em arquivos usando C."

**Exercício 2**: Reutilizando o mesmo código, troque o modo de abertura para `append` e escreva outra frase e observe o resultado. Em seguida, retorne novamente o modo de abertura para `write` e escreva uma terceira frase no arquivo, observando o resultado.

**Desafio 1**: Pesquise uma forma diferente de incluir informações nos arquivos utilizando as funções `fputs` e `fputc` e produza uma versão diferente dos programas feitos nos dois exercícios anteriores.

## Recuperando informações dos arquivos

Assim como utilizamos uma função parente do `printf` para "imprimir" informações em arquivos, vamos utilizar uma função parente do `scanf` para ler informações deles, assim como lemos do teclado.

```
#include <stdio.h>

int main (void) {

  FILE *file;

  // caminho até o arquivo incluindo o nome
  // read, write ou append
  file = fopen("meu_arquivo.txt", "r"); 

  int x, y, z;

  // ponteiro para o arquivo
  // formato que as informações estão escritas
  // variáveis de destino das informações
  fscanf(file, "%d %d %d", &x, &y, &z);

  printf("%d %d %d", x, y, z);

  fclose(file);

  return 0;
}
```

A função `fscanf` procura, no arquivo apontado pelo ponteiro passado no primeiro parâmetro, pelo formato especificado no segundo parâmetro, e guarda essas informações nas variáveis especificadas. Bem parecido com o `scanf`, não é verdade? No nosso exemplo acima, temos um arquivo que armazena três números inteiros, separados por espaço.

Mas, e se o arquivo não existir? Como fazer? O programa exibirá outras informações, porém não exatamente as que estariam no arquivo. Na verdade, é exibido qualquer outra informação previamente armazenada no endereço de memória utilizado. Ou seja, a função `fscanf` não foi chamada.

É preciso ter a garantia de que o ponteiro `file` aponte para um arquivo válido. Uma saída é informar ao usuário o que houve:

```
#include <stdio.h>

int main (void) {

  FILE *file;
  char fileName [] = "meu_arquivo2.txt";

  // caminho até o arquivo até o nome
  // read, write ou append
  file = fopen(fileName, "r"); 
  if (file == NULL) {
    printf("O arquivo %s não pode ser aberto\n", fileName);
    return 0;
  }

  int x, y, z;

  // ponteiro para o arquivo
  // formato que as informações estão escritas
  // variáveis de destino das informações
  fscanf(file, "%d %d %d", &x, &y, &z);

  printf("%d %d %d", x, y, z);

  fclose(file);

  return 0;
}
```

**Exercício 3**: Utilizando a função `fscanf`, tente realizar a leitura de 5 números decimais no arquivo chamado "notas.txt" e imprima a média dessas notas no terminal.

Tem mais uma coisa, e se o arquivo não tiver formatado exatamente como o especificado no segundo parâmetro da função `fscanf`? Primeiramente, seriam novamente coletados os dados previamente armazenados nos endereços de memórias utilizados. 

Em segundo lugar, precisamos de uma função com mais possibilidades de processamento e menos limitações. Para entendermos isso, observe o próximo exemplo:

```
#include <stdio.h>
#include <stdlib.h>

int main (void) {

  FILE *file;
  char fileName [] = "frases.txt";

  file = fopen(fileName, "r"); 
  if (file == NULL) {
    printf("O arquivo %s não pode ser aberto\n", fileName);
    return 0;
  }

  char frase [100];
  while(fgets(frase, 100, file) != NULL) {
    printf("%s", frase);
  }

  fclose(file);

  return 0;
}
```

A função utilizada `fgets` utilizada no laço faz a leitura de uma linha até 100 caracteres, que é o número especificado no segundo parâmetro, e armazena no array de caracteres do mesmo tamanho a string contida no arquivo apontado pelo ponteiro do terceiro parâmetro.

**Exercício 4**: Faça um programa que lê apenas a primeira linha de um arquivo em duas versões: a primeira, usando a função `fgets` e a segunda usando `fscanf` (utilize apenas um %s para formatar a coleta no arquivo). Observe a diferença entre as abordagens.

**Desafio 2**: Crie um programa que tenha uma função onde se copia o conteúdo de um arquivo para outro. Dica: use a função `fputs` pesquisada anteriormente.