package general;

public class EntranceExam {

        public static final double minimumScore = 0;
        public static final double maximumScore = 1000;

        public double humanities;
        public double naturalSciences;
        public double languages;
        public double mathematics;
        public double essay;

        public EntranceExam(double humanities, double naturalSciences, double languages, double mathematics, double essay) {
            this.humanities = humanities;
            this.naturalSciences = naturalSciences;
            this.languages = languages;
            this.mathematics = mathematics;
            this.essay = essay;
        }

        public double getHumanities() {
            return humanities;
        }

        public void setHumanities(double humanities) {
            this.humanities = humanities;
        }

        public double getNaturalSciences() {
            return naturalSciences;
        }

        public void setNaturalSciences(double naturalSciences) {
            this.naturalSciences = naturalSciences;
        }

        public double getLanguages() {
            return languages;
        }

        public void setLanguages(double languages) {
            this.languages = languages;
        }

        public double getMathematics() {
            return mathematics;
        }

        public void setMathematics(double mathematics) {
            this.mathematics = mathematics;
        }

        public double getEssay() {
            return essay;
        }

        public void setEssay(double essay) {
            this.essay = essay;
        }

}
