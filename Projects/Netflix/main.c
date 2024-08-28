#include <stdbool.h>
#include <stdio.h>
#include <string.h>

const int totalFilmes = 100;
const int totalMelhoresSemana = 10;
const int elementoNulo = -1;

int totalFavoritos = 0;
int favoritos[1000];
bool saiuProgama = false;

char filmes[1000][1000] = {
    " Jackie Chan Adventures (2000-2004) | 1h38m",
    " Jackie Chan Is the Prisoner (1990) | 1h35m",
    " Os Sete Samurais (1954) | 2h18m",
    " Bonnie e Clyde - Uma Rajada de Balas (1967) | 1h15m",
    " Cães de Aluguel (1992) | 2h23m",
    " Apertem os Cintos… O Piloto Sumiu (1980) | 2h20m",
    " O Labirinto do Fauno (2006) | 1h45m",
    " Doutor Jivago (1965) | 1h12m",
    " O Franco-Atirador (1978) | 2h45m",
    " Contatos Imediatos de Terceiro Grau (1977) | 1h49m",
    " Up - Altas Aventuras (2009) | 1h15m",
    " Rocky, um Lutador (1976) | 2h8m",
    " Amnésia (2000) | 1h3m",
    " Coração Valente (1995) | 1h9m",
    " Quem Quer Ser um Milionário? (2008) | 1h4m",
    " Senhor dos Anéis: O Retorno do Rei (2003) | 2h29m",
    " A Bela e a Fera (1991) | 2h45m",
    " Seven - Os Sete Crimes Capitais (1995) | 1h7m",
    " A Origem (2010) | 1h53m",
    " Duro de Matar (1988) | 1h28m",
    " Senhor dos Anéis: A Sociedade do Anel (2001) | 2h5m",
    " Amadeus (1984) | 1h4m",
    " Sindicato de Ladrões (1954) | 2h13m",
    " Wall-E (2008) | 1h49m",
    " 12 Homens e uma Sentença (1957) | 1h53m",
    " Os Caça-Fantasmas (1984) | 2h21m",
    " O Segredo de Brokeback Mountain (2005) | 2h8m",
    " A Ponte do Rio Kwai (1957) | 1h40m",
    " Banzé no Oeste (1974) | 1h29m",
    " Todos os Homens do Presidente (1976) | 1h38m",
    " O Jovem Frankenstein (1974) | 2h29m",
    " Quase Famosos (2000) | 1h22m",
    " Um Corpo que Cai (1958) | 1h9m",
    " Gladiador (2000) | 2h16m",
    " Monty Python Em Busca do Cálice Sagrado (1975) | 1h37m",
    " Avatar (2009) | 2h33m",
    " O Rei Leão (1994) | 1h36m",
    " Touro Indomável (1980) | 1h44m",
    " Mary Poppins (1964) | 2h15m",
    " Feitiço do Tempo (1993) | 2h26m",
    " Intriga Internacional (1959) | 2h59m",
    " Amor, Sublime Amor (1961) | 2h60m",
    " O Fabuloso Destino de Amélie Poulain (2001) | 2h9m",
    " Thelma & Louise (1991) | 1h27m",
    " Crepúsculo dos Deuses (1950) | 2h45m",
    " Batman: O Cavaleiro das Trevas (2008) | 1h57m",
    " Brilho Eterno de uma Mente sem Lembranças (2004) | 2h41m",
    " Taxi Driver - Motorista de Táxi (1976) | 1h44m",
    " Butch Cassidy (1969) | 2h28m",
    " Gênio Indomável (1997) | 1h20m",
    " A Malvada (1950) | 1h56m",
    " O Grande Lebowski (1998) | 1h31m",
    " Jurassic Park: O Parque dos Dinossauros (1993) | 2h49m",
    " Janela Indiscreta (1954) | 1h15m",
    " Os Suspeitos (1995) | 2h48m",
    " Quanto Mais Quente Melhor (1959) | 2h19m",
    " O Resgate do Soldado Ryan (1998) | 2h17m",
    " Titanic (1997) | 1h8m",
    " Matrix (1999) | 1h32m",
    " Toy Story (1995) | 1h22m",
    " Alien, o Oitavo Passageiro (1979) | 1h45m",
    " Psicose (1960) | 2h13m",
    " Clube da Luta (1999) | 2h6m",
    " O Iluminado (1980) | 1h24m",
    " Harry e Sally - Feitos Um para o Outro (1989) | 1h24m",
    " Dr Estranho | 1h60m",
    " Curtindo a Vida Adoidado (1986) | 1h6m",
    " Laranja Mecânica (1971) | 1h44m",
    " Beleza Americana (1999) | 1h49m",
    " Fargo (1996) | 2h20m",
    " Star Wars: O Império Contra-Ataca (1980) | 1h25m",
    " A Princesa Prometida (1987) | 1h53m",
    " Um Estranho no Ninho (1975) | 1h7m",
    " Blade Runner, o Caçador de Andróides (1982) | 1h37m",
    " A Primeira Noite de um Homem (1967) | 2h21m",
    " Clube dos Cinco (1985) | 2h60m",
    " Cantando na Chuva (1952) | 2h14m",
    " A Noviça Rebelde (1965) | 1h3m",
    " Tubarão (1975) | 1h32m",
    " Lawrence da Arábia (1962) | 2h55m",
    " O Silêncio dos Inocentes (1991) | 2h0m",
    " Chinatown (1974) | 2h1m",
    " A Felicidade Não se Compra (1946) | 1h38m",
    " Os Bons Companheiros (1990) | 2h18m",
    " Noivo Neurótico, Noiva Nervosa (1977) | 2h9m",
    " Apocalypse Now (1979) | 2h35m",
    " O Sol é Para Todos (1962) | 2h42m",
    " Forrest Gump: O Contador de Histórias (1994) | 1h54m",
    " Indiana Jones e os Caçadores da Arca Perdida (1981) | 1h35m",
    " De Volta Para o Futuro (1985) | 2h39m",
    " Star Wars: Uma Nova Esperança (1977) | 1h2m",
    " A Lista de Schindler (1993) | 2h59m",
    " 2001: Uma Odisséia no Espaço (1968) | 1h45m",
    " O Poderoso Chefão: Parte II (1974) | 2h31m",
    " Casablanca (1942) | 2h32m",
    " Pulp Fiction - Tempo de Violência (1994) | 1h50m",
    " Um Sonho de Liberdade (1994) | 1h18m",
    " Cidadão Kane (1941) | 1h7m",
    " O Mágico de Oz (1939) | 2h38m",
    " O Poderoso Chefão (1972) | 1h30m",
};

char melhoresSemana[1000][1000] = {
    "The Boys",  
    "Lucifer",       
    "The Flash",      
    "The Walking Dead",
    "The Sadman", 
    "She Hulk",       
    "Peaky Blinders", 
    "Grey's Anatomy",
    "See",        
    "Game of Thrones"
};

char opcoes[100][100] = {
    "Ver Catalogo", 
    "Ver Favoritos",
    "Ver Melhores da Semana",
    "Sair"
};

void cabecalho() {
  printf("---------------------------------------------------------- \n");
}

void textoCentralizado(char msg[100]) {
  printf("           %s \n\n", msg); 
}

void verMelhoresSemana() {

  textoCentralizado("Melhores da Semana");

  for (int x = 0; x < totalMelhoresSemana; x++) {
    printf("%d. %s \n", x + 1, melhoresSemana[x]);
  }

  cabecalho();
}

void catalago() {

  int filmeEscolhido;

  textoCentralizado("Lista de Filmes");

  for (int x = 0; x < totalFilmes; x++) {
    printf("%d. %s\n", x + 1, filmes[x]);
  }

  cabecalho();

  printf("\n Digite o número do filme para colocar nos favoritos ou %d para voltar \n", elementoNulo);
  scanf("%d", &filmeEscolhido);

  if (filmeEscolhido == elementoNulo){
    cabecalho();
    return;
  }
    
  if (filmeEscolhido < 1 || filmeEscolhido > totalFilmes) {
    printf("\nOpção inválida, tente novamente! \n\n");
    cabecalho();
    return;
  }

  for (int x = 0; x < totalFavoritos; x++) {
    if (favoritos[x] == filmeEscolhido - 1) {
      printf("\nVocê já adicionou esse filme ao favoritos! \n\n");
      cabecalho();
      return;
    }
  }

  favoritos[totalFavoritos] = filmeEscolhido - 1;
  totalFavoritos++;
  
  printf("\n\nFilmes adicionado aos favoitos com sucesso \n\n");
}

void verFavorito() {

  if (totalFavoritos == 0) {
    printf("\nNão há favoritos salvos \n\n");
    cabecalho();
    return;
  }

  textoCentralizado("Todos os Favoritos");

  for (int x = 0; x < totalFavoritos; x++) {
    int posicao = favoritos[x];
    printf("%d. %s\n", x + 1, filmes[posicao]);
  }
  
  printf("\n");
  cabecalho();
}

void menuInicial() {

  const int total = 4;
  int opcaoEscolhida;

  textoCentralizado("Menu Inicial");

  for (int x = 0; x < total; x++) {
    printf("%d. %s\n", x + 1, opcoes[x]);
  }

  printf("Digite sua a opção que deseja \n");
  scanf("%d", &opcaoEscolhida);
  cabecalho();

  switch (opcaoEscolhida) {
      case 1:
          catalago();
          break;
      case 2:
          verFavorito();
          break;
      case 3:
          verMelhoresSemana();
          break;
      case total:
          saiuProgama = true;
          printf("Você saiu do progama \n");
          cabecalho();
          break;
    
      default: {
        printf("Você escolheu uma opção errada \n");
        cabecalho();
        break;
      }
  }
  
}

int main(void) {

  while (!saiuProgama) {
    menuInicial();
  }

  return 0;
}
