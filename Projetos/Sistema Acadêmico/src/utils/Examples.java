package utils;
import general.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 classe de testes para gerar instancias para o sistema
*/

public class Examples {
    public static List<Admin> getAdmins() {

        Admin adm1 = new Admin("Lucas", "Ferreira",  LocalDate.of(2003, 2, 2), "15151551");

        System.out.printf("Id Admin: %s | Senha: %s\n\n", adm1.getId(), adm1.getFormatedDateOfBirth());

        return List.of(adm1);
    }
    public static List<Teacher> getTeachers() {

        Teacher t1 = new Teacher("Mateus", "Silva", LocalDate.of(2003, 2, 2), "8789784");

        System.out.printf("Id Professor: %s | Senha: %s\n", t1.getId(), t1.getFormatedDateOfBirth());

        return List.of(t1);
    }
    public static List<Student> getStudents() {

        Student s1 = new Student("Alice", "Smith", LocalDate.of(2003, 2, 2), "12345678901",  "CC", new EntranceExam(600.0, 700.0, 800.0, 900.0, 1000.0));

        System.out.printf("Id Estudante: %s | Senha: %s\n", s1.getId(), s1.getFormatedDateOfBirth());

        return List.of(
                s1,
                new Student("Bob", "Johnson", LocalDate.of(2002, 8, 10), "23456789012",  "CC", new EntranceExam(550.0, 650.0, 750.0, 850.0, 950.0)),
                new Student("Charlie", "Brown", LocalDate.of(2004, 2, 28), "34567890123",  "CC", new EntranceExam(700.0, 800.0, 900.0, 1000.0, 600.0)),
                new Student("Daniela", "Silva", LocalDate.of(2001, 11, 5), "45678901234", "CC", new EntranceExam(650.0, 750.0, 850.0, 950.0, 550.0)),
                new Student("Eduardo", "Santos", LocalDate.of(2005, 7, 20), "56789012345",  "CC", new EntranceExam(800.0, 900.0, 1000.0, 600.0, 700.0))
        );
    }
    public static List<Room> getRooms() {
        return List.of(
                new Room("A100", 20),
                new Room("B200", 30),
                new Room("C150", 40),
                new Room("B300", 50),
                new Room("A120", 60),
                new Room("C180", 70),
                new Room("B130", 80),
                new Room("A250", 20),
                new Room("C200", 30),
                new Room("A110", 40),
                new Room("B180", 50),
                new Room("C250", 60),
                new Room("A140", 70),
                new Room("B170", 80),
                new Room("C120", 20),
                new Room("B220", 30),
                new Room("A280", 40),
                new Room("C160", 50),
                new Room("B190", 60),
                new Room("A200", 70)
        );
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
    public static List<CollegeClass> getGollegesClass() {

        Room c1 = new Room("B150", 50);
        String idCollegeClass = UUID.randomUUID().toString();
        AcademicSystem academicSystem = Global.getAcademicSystem();
        Teacher teacher = academicSystem.db.teachers.getAll().get(0);
        Subject subject = getSubjects().get(0);

        academicSystem.db.rooms.save(c1);

        List<SubjectStudent> s1 = Subject.studentToSubjectStudent(academicSystem.db.students.getAll(), subject, idCollegeClass);

        CollegeClass col1 = new CollegeClass(
                subject.getCode(),
                subject.getName(),
                subject.getHours(),
                teacher.getId(),
                idCollegeClass
        );

        col1.setStudents(s1);

        return List.of(
           col1
        );


    }
}
