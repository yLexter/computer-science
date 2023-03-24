class Square {

    public static void showSquare(int number) {
  
      for (int x = 0; x < number; x++) {
  
        int topOrRigthSide = number - 1;
        int downOrLeftSide = 0;
        
          for (int y = 0; y < number; y++) {
            
             String caractere = 
                (x == downOrLeftSide || y == downOrLeftSide) ||
                (x == topOrRigthSide || y == topOrRigthSide) ? "* " : "  ";
  
            System.out.print(caractere);          
          }
  
          System.out.println();
      }
  
    }
  
    public static void test() {
      Square.showSquare(10);
    }
  
  }
  
  class Vowels {
  
    public static int getTotalVowels(String word) {
  
      char[] arrayWord = word.toLowerCase().toCharArray();
      char[] vowels = { 'a', 'e', 'i', 'o', 'u' };
  
      int total = 0;
  
      for (int i = 0; i < vowels.length; i++) {
  
        for (int j = 0; j < arrayWord.length; j++) {
  
          if (arrayWord[j] == vowels[i])
            total++;
  
        }
  
      }
  
      return total;
  
    }
  
    public static void test() {
      System.out.println(Vowels.getTotalVowels("Lucas Ferreira Maia"));
    }
  
  }
  
  class Candidate {
  
    public String name;
    public int number;
    private int votes;
  
    Candidate(String name, int number) {
      this.name = name;
      this.number = number;
      this.votes = 0;
    }
  
    int getVotes() {
      return this.votes;
    }
  
    
    void addVote() {
      this.votes++;
    }
  
  }
  
  class Election {
  
    public Candidate candidates[];
    public int votes[];
    public Candidate winner;
  
    public int votesNule;
    public int votesWhite;
  
    public static int voteNule = 4;
    public static int voteWhite = 0;
  
    Election(Candidate candidates[], int votes[]) {
      this.candidates = candidates;
      this.votes = votes;
    }
  
    public void setVote(int number) {
  
      if (number == Election.voteNule) {
        this.votesNule++;
        return;
      }
  
      if (number == Election.voteWhite) {
        this.votesWhite++;
        return;
      }
  
      for (Candidate candidate : this.candidates) {
        
        if (candidate.number == number) {
          candidate.addVote();
          break;
        }
        
      }
      
    }
  
    public void computeVotes() {
  
      for (int x : this.votes)
        this.setVote(x);
  
    }
  
    public void sortCandidates() {
  
      for(int x = 0; x < this.candidates.length; x++) {
  
        for(int y = 0; y < this.candidates.length - 1; y++) {
         
          if (this.candidates[x].getVotes() > this.candidates[y].getVotes()) {
             Candidate temp = this.candidates[y];
             this.candidates[y] = this.candidates[y + 1];
             this.candidates[y + 1] = temp;
          }
  
        }
      }
    }
  
    public float getPercent(int votes) {
  
      float floatVotes = votes;
  
      if (floatVotes == 0)
        return 0;
      
      return (floatVotes * 100) / this.votes.length; 
    }
    
    public ArrayList<Candidate> getWinners() {
  
      Candidate currentWinner = this.candidates[0];
      ArrayList<Candidate> winners = new ArrayList<Candidate>();
      
      for (Candidate candidate : this.candidates) {
          if (candidate.getVotes() > currentWinner.getVotes())
            currentWinner = candidate;
      }
  
      for (Candidate candidate : this.candidates) {
         if (candidate.getVotes() == currentWinner.getVotes())
             winners.add(candidate);
      }
  
       return winners;
    }
  
    public Candidate getOutrigthWinner() {
  
      ArrayList<Candidate> winners = this.getWinners();
      int randomNumber = new Random().nextInt(winners.size());
  
      if (winners.size() == 1)
        return winners.get(0);
  
      return winners.get(randomNumber);
    }
  
    public void showResults() {
  
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
        System.out.printf("Total de votos: %d\n", this.votes.length);
        System.out.println();
    
        System.out.printf("-------------------\n");
  
        System.out.printf("O vencedor foi %s (N° %d) com %d votos!\n\n", winner.name, winner.number , winner.getVotes());
  
       System.out.println("* Caso haja empate de votos, o sistema irá sortear um vencedor.");
  
      
    }
    
    public void start() {
       this.computeVotes();
       this.sortCandidates();
       this.showResults();
    }
  
    public static int[] getRandomVotes (int total, int numberLimit) {
  
      int votes[] = new int[total];
  
      for(int x = 0; x < total; x++) 
         votes[x] = new Random().nextInt(numberLimit + 1);
    
      return votes;
    }
  
    public static void test() {
  
      Candidate candidates[] = {
          new Candidate("João", 1),
          new Candidate("Maria", 2),
          new Candidate("Alex", 3),
      };
  
      int votes[] = Election.getRandomVotes(4999, 4);
  
      Election election = new Election(candidates, votes);
  
      election.start();
  
    }
  
  }
  
  public class Main {
    public static void main(String[] args) {
  
      Square.test();
  
  
    }
  }