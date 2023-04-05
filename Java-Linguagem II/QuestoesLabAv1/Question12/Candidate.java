package Java.QuestoesLabAv1.Question12;

public class Candidate {

    public String name;
    public int number;
    private int votes;

    /**
     * Construtor para iniciar o objeto
     *
     * @param name nome do candidato
     * @param number número do candidato
     */
    public Candidate(String name, int number) throws Exception {
        this.name = name;
        this.number = number;
        this.votes = 0;

        this.validCandidate();
    }

    /**
     * Valida se o nome fornecido não é nulo ou vazio
     *
     */
    private void validCandidate() throws Exception {

        if (this.name == null || this.name == "")
            throw new Exception("This name cannot be null ou void");

    }

    /**
     * Retorna o total de votos do candidatos
     *
     * @return total de votos do candidato
     */
    int getVotes() {
        return this.votes;
    }

    /**
     * Adiciona  voto para o candidato
     */
    void addVote() {
        this.votes++;
    }

}
