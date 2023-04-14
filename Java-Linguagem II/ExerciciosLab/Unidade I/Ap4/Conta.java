package Java.ExerciciosLab.Ap4;

public class Conta {

    private String nome, sobrenome;
    private int nConta;
    private double saldo;

    public Conta(){}

    public Conta(String nome, String sobrenome, int nConta, double saldo){
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setNConta(nConta);
        this.setSaldo(saldo);
    }

    public Conta(String nome, String sobrenome) {
        this.setNome(nome);
        this.setSobrenome(sobrenome);
    }

    public void setSaldo(double saldo){

        if(saldo >= 0)
            this.saldo += saldo;
    }

    public double getSaldo(){
        return saldo;
    }

    public void setNConta(int nConta){

        if(nConta > 0)
            this.nConta = nConta;
    }

    public int getNConta(){
        return nConta;
    }

    public void setNome(String nome) {

        if(nome != "")
            this.nome = nome.trim();
    }

    public String getNome(){
        return this.nome;
    }

    public void setSobrenome(String sobrenome){
        if(sobrenome != "")
            this.sobrenome = sobrenome.trim();
    }

    public String getSobrenome(){
        return sobrenome;
    }

    public void exibirConta(){
        System.out.printf("Conta: %d\nCliente: %s %s\nSaldo: %.2f\n",
                nConta, nome, sobrenome, saldo);
    }


}
