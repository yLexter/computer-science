package Java.QuestoesLabAv1.Question12;
import java.util.*;
public class Election {

  private Candidate candidates[];
  private Candidate winner;
  private ArrayList<Integer> votes;
  private int votesNule;
  private int votesWhite;
  private int voteNule;
  private int voteWhite;
  private LinkedHashMap<String, Runnable> optionsMenu;
  private boolean electionHappening;


  /**
   * Inicia algumas configurações padrões
   */
  {
    this.votes = new ArrayList<>();
    this.electionHappening = true;
    this.setOptionsMenu();
  }

  /**
   * Seta as opções do menu iniciais e suas respectivas funções.
   * A opção de sair sempre será a a ultíma.
   */
  private void setOptionsMenu() {
    this.optionsMenu = new LinkedHashMap<>();

    optionsMenu.put("Ver Candidatos", this::showCandidates);
    optionsMenu.put("Computar Votos", this::computeVotes);
    optionsMenu.put("Mostrar Resultados", this::showResults);
    optionsMenu.put("Sair", () -> {});
  }

  /**
   * Construtor para iniciar o as eleições com voto nulo e branco padrões
   *
   * @param candidates Array de Candidatos
   *
   *
   */
  public Election(Candidate[] candidates) throws Exception {
    this.candidates = candidates;
    this.voteNule = 4;
    this.voteWhite = 0;

    this.checkValidDate();
  }

  /**
   * Construtor para iniciar o as eleições, com voto nulo e branco customizado
   *
   * @param candidates Um Array de Candidatos
   * @param voteNule Um número para representar o voto nulo
   * @param voteWhite Um número para representar o voto branco
   */
  public Election(Candidate[] candidates, int voteNule, int voteWhite) throws Exception {
    this.candidates = candidates;
    this.voteNule = voteNule;
    this.voteWhite = voteWhite;

    this.checkValidDate();
  }

  /**
   Incrementano no total de votos nulos
   */
  private void addVotesNule() {
    this.votesNule++;
  }

  /**
   Incrementano no total de votos brancos
   */
  private void addVotesWhite() {
    this.votesWhite++;
  }

  /**
   * Valida se os dados passado é valido, está é a função principal
   * que chamará outras para validar os dados fornecidos
   *
   */
  private void checkValidDate() throws Exception {
    this.checkValidCandidates();
  }

  /**
   * Checa se os candidatos fornecidos são válidos, validando se existe candidatos com colisão de números,
   * Se os números são iguais ao voto nulo ou branco e
   * se a lista de candidatos tem pelomenos 2 candidatos
   */
  private void checkValidCandidates() throws Exception {

    HashMap<Integer, Candidate> mapCandidates = new HashMap<Integer, Candidate>();

    if (this.candidates == null)
      throw new Exception("This array candidates is null");

    if (this.candidates.length <= 1)
      throw new Exception("Candidates list needs of two candidates");

    for (Candidate candidate : this.candidates) {

      if (candidate.number == this.voteNule || candidate.number == voteWhite) {
        String errorMessage = String.format("The number of the candidate %s (%d) is colliding with number null (%d) or number white (%d) ", candidate.name, candidate.number , this.voteNule, this.voteWhite);

        throw new Exception(errorMessage);
      }

      Candidate currentCandidate = mapCandidates.get(candidate.number);

      if (currentCandidate != null) {
        String errorMessage = String.format("The number of the candidate %s (%d) is colliding with %s (%d)", candidate.name, candidate.number, currentCandidate.name, currentCandidate.number);
        throw new Exception(errorMessage);
      } else {
        mapCandidates.put(candidate.number, candidate);
      }



    }

  }

  /**
   * Computa um voto, caso não exista um candidato com o número fonecido,
   * será computado como nulo
   */
  private void setVote(int number) {

    if (number == this.voteNule) {
      this.addVotesNule();
      return;
    }

    if (number == this.voteWhite) {
      this.addVotesWhite();
      return;
    }

    for (Candidate candidate : this.candidates) {

      if (candidate.number == number) {
        candidate.addVote();
        return;
      }

    }

    this.addVotesNule();
  }

  /**
   *  Computa todos os votos
   *
   * */
  private void computeVotes() {

    if (this.votes.size() > 0) {
      System.out.println("A Computação de votos já foi computada");
      return;
    }

    this.votes.addAll(this.getRandomVotes());

    for (int vote : this.votes)
      this.setVote(vote);

    System.out.printf("A Computação de votos foi realizada com sucesso \n\n");
  }

  /**
   *  Mostra todos os candidatos da eleição
   * */
  public void showCandidates() {

     System.out.println("------ Candidatos --------");

     for(Candidate candidate: this.candidates) {
       System.out.printf("Candidado: %s\n", candidate.name);
       System.out.printf("Número: %d\n\n", candidate.number);
     }

     System.out.printf("Voto nulo: %d | Voto Branco %d\n", this.voteNule, this.voteWhite);

     System.out.println("---------------------");

  }

  /**
   *  Organiza o array de candidatos na forma decrescente
   *  usando o Bubble Sorte
   * */
  private void sortCandidates() {

    for(int x = 0; x < this.candidates.length; x++) {

      for(int y = 0; y < this.candidates.length - 1; y++) {
        if (this.candidates[y].getVotes() < this.candidates[y + 1].getVotes()) {
          Candidate temp = this.candidates[y];
          this.candidates[y] = this.candidates[y + 1];
          this.candidates[y + 1] = temp;
        }

      }
    }
  }

  /**
   *
   * Obtém a porcetagem em relação ao total de votos de um candidato
   *
   * @return A Porcentagem de votos
   */
  private float getPercent(int votes) {

    float floatVotes = votes;

    if (this.votes.size() == 0)
      return 0;

    return (floatVotes * 100) / this.votes.size();
  }

  /**
   *
   * Obtém os ganhadores das eleições,
   * poderá ser mais de um caso o vencedor
   * tenha votos iguais a outros
   * @retyrn A Lista de candidatos Ganhador(es)
   */
  private ArrayList<Candidate> getWinners() {

    this.sortCandidates();

    Candidate currentWinner = this.candidates[0];

    ArrayList<Candidate> winners = new ArrayList<Candidate>();

    for (Candidate candidate : this.candidates) {
      if (candidate.getVotes() == currentWinner.getVotes())
        winners.add(candidate);
    }

    return winners;
  }

  /**
   *
   * Obtém um vencedor definitivo, caso tenha vários,sorteará um aleatoriamente.
   *
   * @return O ganhador da eleição
   */
  private Candidate getOutrigthWinner() {

    ArrayList<Candidate> winners = this.getWinners();

    int randomNumber = new Random().nextInt(winners.size());

    if (winners.size() == 1)
      return winners.get(0);

    return winners.get(randomNumber);
  }

  /**
   *
   * Mostra os resultados da eleição
   *
   */
  private void showResults() {

    if (this.votes.size() == 0) {
      System.out.printf("Nenhum voto foi computado! \n\n");
      return;
    }

    Candidate winner = this.getOutrigthWinner();

    System.out.printf("\n-----Candidatos----\n\n");

    for (Candidate candidate : this.candidates) {
      int votes = candidate.getVotes();

      System.out.printf("Nome: %s\n", candidate.name);
      System.out.printf("Número: %d\n", candidate.number);
      System.out.printf("Votos: %d (%.2f%%)\n ", votes, this.getPercent(votes));
      System.out.println();
    }

    System.out.printf("-------------------\n\n");
    System.out.printf("Votos Nulos: %d (%.2f%%)\n", this.votesNule, this.getPercent(this.votesNule));
    System.out.printf("Votos Brancos: %d (%.2f%%)\n", this.votesWhite, this.getPercent(this.votesWhite));
    System.out.printf("Total de votos: %d\n", this.votes.size());
    System.out.println();
    System.out.printf("-------------------\n");
    System.out.printf("O vencedor foi %s (N° %d) com %d votos!\n\n", winner.name, winner.number , winner.getVotes());
    System.out.println("* Caso haja empate de votos, o sistema irá sortear um vencedor \n\n");
  }

  /**
   * Finaliza a eleição
   *
   */
  private void finishElection() {
    this.electionHappening = false;
  }

  /**
   *
   * Inicia o menu inicial das eleições
   *
   */
  public void start() {

  String[] options = this.optionsMenu.keySet().toArray(new String[0]);

  while(this.electionHappening) {

    System.out.println("------- Menu Inicial ----------");

    for (int x = 0; x < options.length; x++)
      System.out.printf("%d. %s \n", x + 1, options[x]);

     System.out.println("--------------------------");
     System.out.print("Digite a opção que deseja: ");

     int input = new Scanner(System.in).nextInt();

     System.out.println();

     if(input == options.length) {
        System.out.println("Você escolheu sair.");
        this.finishElection();
        continue;
     }

     if(input < 1 || input >= options.length) {
       System.out.printf("A Opção escolhida é invalida\n\n");
       continue;
     }

     this.optionsMenu.get(options[input - 1]).run();

     System.out.println();

  }

  }

  /**
   *
   * Pega todos os números dos candidatos junto com o voto nulo e branco
   * e armazena em um arrayList
   *
   * @return Uma arraylist com os números das eleições
   */

  public ArrayList<Integer> getNumbersElection() {
    ArrayList<Integer> numbersElections = new ArrayList<>();

    numbersElections.add(this.voteNule);

    for(Candidate candidate : this.candidates)
      numbersElections.add(candidate.number);

    numbersElections.add(this.voteWhite);

    return numbersElections;

  }

  /**
   *
   * Gera votos aleatorios para a eleição
   *
   */
  public ArrayList<Integer> getRandomVotes() {

    int total = 4999;

    ArrayList<Integer> numbersElection = this.getNumbersElection();
    ArrayList<Integer> votes = new ArrayList<>();

    for(int x = 0; x < total; x++) {
      int randomVote = new Random().nextInt(numbersElection.size());

      votes.add(numbersElection.get(randomVote));
    }

    return votes;
  }



}
  
 
  
  