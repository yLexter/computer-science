package Java.QuestoesLabAv1.Question12;
public class Question12 {

    public static void main(String[] args) {

        try {

            Candidate candidates[] = {
                    new Candidate("João", 1),
                    new Candidate("Alex", 2),
                    new Candidate("Maria", 3),
            };


            Election election = new Election(candidates);

            election.start();

        } catch (Exception e) {
            System.out.printf("Erro: %s\n\n", e.getMessage());
        }


    }


}
