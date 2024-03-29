package general;

/**
 * Essa classe representa o resultado do exame de entrada de um candidato. Ela contém as notas nas diferentes áreas
 * do exame, como humanidades, ciências naturais, idiomas, matemática e redação.
 */
public class EntranceExam {
    /**
     * A pontuação mínima possível no exame de entrada.
     */
    public static final double minimumScore = 0;
    /**
     * A pontuação máxima possível no exame de entrada.
     */
    public static final double maximumScore = 1000;

    public double humanities;
    public double naturalSciences;
    public double languages;
    public double mathematics;
    public double essay;

    /**
     * Cria um novo objeto EntranceExam com as notas fornecidas para cada área do exame.
     *
     * @param humanities      A nota na área de humanidades.
     * @param naturalSciences A nota na área de ciências naturais.
     * @param languages       A nota na área de idiomas.
     * @param mathematics     A nota na área de matemática.
     * @param essay           A nota na redação.
     */
    public EntranceExam(double humanities, double naturalSciences, double languages, double mathematics, double essay) {
        this.humanities = humanities;
        this.naturalSciences = naturalSciences;
        this.languages = languages;
        this.mathematics = mathematics;
        this.essay = essay;
    }

    /**
     * Obtém a nota na área de humanidades.
     *
     * @return A nota na área de humanidades.
     */
    public double getHumanities() {
        return humanities;
    }

    /**
     * Define a nota na área de humanidades.
     *
     * @param humanities A nota na área de humanidades a ser definida.
     */
    public void setHumanities(double humanities) {
        this.humanities = humanities;
    }

    /**
     * Obtém a nota na área de ciências naturais.
     *
     * @return A nota na área de ciências naturais.
     */
    public double getNaturalSciences() {
        return naturalSciences;
    }

    /**
     * Define a nota na área de ciências naturais.
     *
     * @param naturalSciences A nota na área de ciências naturais a ser definida.
     */
    public void setNaturalSciences(double naturalSciences) {
        this.naturalSciences = naturalSciences;
    }

    /**
     * Obtém a nota na área de idiomas.
     *
     * @return A nota na área de idiomas.
     */
    public double getLanguages() {
        return languages;
    }

    /**
     * Define a nota na área de idiomas.
     *
     * @param languages A nota na área de idiomas a ser definida.
     */
    public void setLanguages(double languages) {
        this.languages = languages;
    }

    /**
     * Obtém a nota na área de matemática.
     *
     * @return A nota na área de matemática.
     */
    public double getMathematics() {
        return mathematics;
    }

    /**
     * Define a nota na área de matemática.
     *
     * @param mathematics A nota na área de matemática a ser definida.
     */
    public void setMathematics(double mathematics) {
        this.mathematics = mathematics;
    }

    /**
     * Obtém a nota na redação.
     *
     * @return A nota na redação.
     */
    public double getEssay() {
        return essay;
    }

    /**
     * Define a nota na redação.
     *
     * @param essay A nota na redação a ser definida.
     */
    public void setEssay(double essay) {
        this.essay = essay;
    }
}

