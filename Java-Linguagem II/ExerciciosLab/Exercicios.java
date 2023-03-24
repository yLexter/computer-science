// Exercico 1 e questão 3 da prova
class Date {
    private int dia;
    private int mes;
    private int ano;
    public String[] meses = {
        "Janeiro",
        "Fevereiro",
        "Março",
        "Abril",
        "Maio",
        "Junho",
        "Julho",
        "Agosto",
        "Setembro",
        "Outubro",
        "Novembro",
        "Dezembro"
    };
  
    public Date(int dia, int mes, int ano) {
  
      if (dia > 0 && dia <= 31)
        this.dia = dia;
  
      if (mes > 0 && mes <= this.meses.length)
        this.mes = mes;
  
      if (ano > 0)
        this.ano = ano;
  
    }
  
    void displayDate() {
      String mes = this.mes <= 0 ? this.meses[0] : this.meses[this.mes - 1];
  
      System.out.printf("%d de %s de %d", this.dia, mes, this.ano);
    }
  
    public static void test() {
  
        new Date(2,2,2003).displayDate();
        new Date(-2,-2,-2003).displayDate();
          
    }
  
  }
  
  // Exercicio 2
  class Dice {
    private int totalFaces;
    private int totalSum;
    private int[] throwsDices;
  
    public Dice(int totalFaces) {
      this.totalFaces = totalFaces;
      this.throwsDices = new int[totalFaces + 1];
    }
  
    Dice rollDice() {
      for (int x = 0; x < 10000000; x++) {
        int roll = new Random().nextInt(this.totalFaces) + 1;
        this.throwsDices[roll] += 1;
        this.totalSum += roll;
      }
  
      return this;
    }
  
    void showRolls() {
  
      for (int x = 1; x < this.throwsDices.length; x++)
        System.out.printf("Face %d: %d\n", x, this.throwsDices[x]);
  
      System.out.printf("Soma total: %d", this.totalSum);
    }
  
     public static void test() {
  
       Dice dice = new Dice(20);
  
       dice
         .rollDice()
         .showRolls();
     }
  }
  
  // Exercicio 3
  class Card {
    private String face;
    private String naipe;
  
    public static String naipes[] = {
        "Copas",
        "Ouros",
        "Rei",
        "Espada"
    };
  
    public static String faces[] = {
        "As",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "10",
        "Valete",
        "Dama",
        "Rei",
    };
  
  
    public Card(String face, String naipe) {
      this.face = face;
      this.naipe = naipe;
    }
  
    public void setFace(String face) {
      this.face = face;
    }
  
    public void setNaipe(String naipe) {
      this.naipe = naipe;
    }
  
    public String getNaipe() {
      return this.naipe;
    }
  
    public String getFace() {
      return this.face;
    }
  
    @Override
    public String toString() {
      return String.format("%d de %d", this.face, this.naipe);
    }
  
  }
  
  class BoxCards {
  
    private static int totalCards = 52;
    private static Card box[] = new Card[BoxCards.totalCards];
  
    int randomNumber(int limit) {
      return new Random().nextInt(limit);
    }
  
    BoxCards generateCards() {
  
      for (int x = 0; x < BoxCards.totalCards; x++) {
  
        String randomNaipe = Card.naipes[x / Card.faces.length];
        String randomFace = Card.faces[x % Card.faces.length];
  
        this.box[x] = new Card(randomFace, randomNaipe);
      }
  
      return this;
    }
  
    BoxCards shuffleBox() {
  
      for (int x = 0; x < BoxCards.totalCards; x++) {
        int radomNumber = new Random().nextInt(BoxCards.totalCards);
  
        Card copyCard = BoxCards.box[x];
  
        BoxCards.box[x] = BoxCards.box[radomNumber];
        BoxCards.box[radomNumber] = copyCard;
      }
  
      return this;
    }
  
    void showBox() {
  
      for (int x = 0; x < BoxCards.totalCards; x++) {
  
        Card card = BoxCards.box[x];
  
        System.out.printf("Face: %s - Naipe: %s\n", card.getFace(), card.getNaipe());
  
        if (x % (Card.faces.length - 1) == 0 && x != 0)
          System.out.println();
  
      }
    }
  
  
    public static void test() {
  
      BoxCards box = new BoxCards();
  
      box
        .generateCards()
        .shuffleBox()
        .showBox();
      
    }
  
    @Override
    public String toString() {
      return "Classe de caixa, representa uma caixa de um baralho";
    }
  
  }
  
  // Exercicio 6
  class Fibonachi {
  
    static long getFibonachi(long number) {
      if (number <= 2)
        return 1;
      return Fibonachi.getFibonachi(number - 1) +
             Fibonachi.getFibonachi(number - 2);
    }
  
      public static void test() {
        System.out.println(Fibonachi.getFibonachi(10));
      }
  
  
  }