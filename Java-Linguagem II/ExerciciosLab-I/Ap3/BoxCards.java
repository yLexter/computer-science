package Java.ExerciciosLab.Ap3;

import java.security.SecureRandom;

public class BoxCards {

    private static int totalCards = 52;
    private static Card box[] = new Card[BoxCards.totalCards];

    int randomNumber(int limit) {
        return new SecureRandom().nextInt(limit);
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
            int radomNumber = new SecureRandom().nextInt(BoxCards.totalCards);

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




    @Override
    public String toString() {
        return "Classe de caixa, representa uma caixa de um baralho";
    }

}
