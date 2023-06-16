package utils;

import general.Subject;
import java.util.*;

public class Utils {

    public static <T extends Subject> Subject getSubjectWithBiggerName(List<T> subjects) {
        return subjects
                .stream()
                .reduce(subjects.get(0), (prev, curr) -> prev.getName().length() > curr.getName().length() ? prev : curr);
    }

    public static List<Subject> getSubjects() {
        List<Subject> subjects = new ArrayList<>();

        subjects.add(new Subject("CPT01005", "INTRODUÇÃO À COMPUTAÇÃO", 60));
        subjects.add(new Subject("CPT01001", "MATEMÁTICA DISCRETA I", 60));
        subjects.add(new Subject("CPT01003", "ALGORITMOS", 60));
        subjects.add(new Subject("CPT01004", "METODOLOGIA CIENTÍFICA", 60));
        subjects.add(new Subject("LTP01158", "PORTUGUÊS INSTRUMENTAL", 30));
        subjects.add(new Subject("CPT01006", "ÉTICA EM COMPUTAÇÃO", 30));
        subjects.add(new Subject("CPT01007", "LÓGICA PARA COMPUTAÇÃO", 60));
        subjects.add(new Subject("SOC01097", "TECNOLOGIA, CIÊNCIA E SOCIEDADE", 30));
        subjects.add(new Subject("MAT01120", "CÁLCULO DIFERENCIAL E INTEGRAL I", 60));
        subjects.add(new Subject("LTI01056", "INGLÊS INSTRUMENTAL", 30));
        subjects.add(new Subject("CPT01017", "MATEMÁTICA DISCRETA II", 60));
        subjects.add(new Subject("CPT01011", "LABORATÓRIO DE PROGRAMAÇÃO I", 60));
        subjects.add(new Subject("MAT01121", "VETORES E GEOMETRIA ANALÍTICA", 60));
        subjects.add(new Subject("CPT01010", "LINGUAGEM DE PROGRAMAÇÃO I", 60));
        subjects.add(new Subject("CPT01087", "ANÁLISE E PROJETO DE SISTEMA", 60));
        subjects.add(new Subject("MAT01148", "CÁLCULO DIFERENCIAL E INTEGRAL III", 60));
        subjects.add(new Subject("EST01093", "PROBABILIDADE E ESTATÍSTICA I", 60));
        subjects.add(new Subject("CPT01027", "LINGUAGENS FORMAIS E TEORIA DA COMPUTAÇÃO", 60));
        subjects.add(new Subject("CPT01026", "TECNOLOGIAS DE DESENVOLVIMENTO DE INTERFACE GRÁFICA", 60));
        subjects.add(new Subject("CPT01025", "LABORATÓRIO DE ESTRUTURA DE DADOS", 60));
        subjects.add(new Subject("CPT01024", "ESTRUTURA DE DADOS", 60));
        subjects.add(new Subject("CPT01093", "PARADIGMAS DE PROGRAMAÇÃO", 60));
        subjects.add(new Subject("CPT01092", "ENGENHARIA DE SOFTWARE I", 60));
        subjects.add(new Subject("EST01094", "PROBABILIDADE E ESTATÍSTICA II", 60));
        subjects.add(new Subject("CPT01030", "TEORIA DA COMPUTAÇÃO", 60));
        subjects.add(new Subject("CPT01031", "LABORATÓRIO DE BANCO DE DADOS", 60));
        subjects.add(new Subject("CPT01032", "BANCO DE DADOS", 60));
        subjects.add(new Subject("CPT01033", "SISTEMAS OPERACIONAIS", 60));
        subjects.add(new Subject("CPT01034", "REDES DE COMPUTADORES", 60));
        subjects.add(new Subject("CPT01091", "ENGENHARIA DE SOFTWARE II", 60));
        subjects.add(new Subject("CPT01036", "INTERAÇÃO HUMANO-COMPUTADOR", 60));
        subjects.add(new Subject("CPT01037", "COMPILADORES", 60));
        subjects.add(new Subject("CPT01038", "SEGURANÇA DA INFORMAÇÃO", 60));
        subjects.add(new Subject("CPT01039", "SISTEMAS DISTRIBUÍDOS", 60));
        subjects.add(new Subject("CPT01040", "EMPREENDEDORISMO", 60));
        subjects.add(new Subject("CPT01041", "PROJETO E ANÁLISE DE ALGORITMOS", 60));
        subjects.add(new Subject("CPT01090", "TÓPICOS AVANÇADOS EM COMPUTAÇÃO I", 60));
        subjects.add(new Subject("CPT01043", "INTELIGÊNCIA ARTIFICIAL", 60));
        subjects.add(new Subject("CPT01044", "PROGRAMAÇÃO CONCORRENTE", 60));
        subjects.add(new Subject("CPT01045", "SISTEMAS INTELIGENTES", 60));
        subjects.add(new Subject("CPT01046", "TÓPICOS AVANÇADOS EM COMPUTAÇÃO II", 60));
        subjects.add(new Subject("CPT01047", "TRABALHO DE CONCLUSÃO DE CURSO", 60));

        return subjects;
    }


}
