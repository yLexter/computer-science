package Java.ExerciciosLab.Ap4;

public class Ap4 {

    public static void main(String[] args) {

        Conta ob1 = new Conta();
        Conta ob2 = new Conta("José", "Maria", 1000, 1000D);

        ob1.exibirConta();
        ob1.setNome("Lucas");
        ob1.exibirConta();

        ob2.exibirConta();
        ob2.setNome("Lucas");
        ob2.setSobrenome("Maia");
        ob2.exibirConta();

    }

}
