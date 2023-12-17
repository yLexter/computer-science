package Java.ExerciciosLab.Ap3;

// Exercicio 3
public class Card {
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
