package menu;

import general.*;
import interfaces.IMenuEmployee;
import interfaces.ISubMenuOption;
import utils.Global;
import utils.Utils;

import java.util.*;

import java.util.function.Function;

// ToDo Implementar Construtor, interface do rdm e pegar dados da db do estudante
public class StudentMenu implements IMenuEmployee<Student> {

    private String studentId;

    public StudentMenu(String studentId) {
        this.studentId = studentId;
    }

    public void optionShowHistoric() {
            Student student = getUser();
            List<SubjectStudent> subjects = student.getSubjects();
            Subject biggestNameSubject = Utils.getSubjectWithBiggerName(subjects);

            System.out.println("+----------+-------+--------+-----------+----------------------");
            System.out.println("| Nome | Código | Horas |Status | Média | Final");
            System.out.println("+----------+-------+---------+----------+----------------------");

            for (SubjectStudent subject : subjects) {
                String complementarySpaces = " ".repeat(
                        biggestNameSubject.getName().length() - subject.getName().length()
                );

                System.out.printf("| %s %s | %s | %d | %s | %d | %s |\n",
                        subject.getName(),
                        complementarySpaces,
                        subject.getCode(),
                        subject.getHours(),
                        subject.getStatus(),
                        subject.getAbsences(),
                        subject.getAverage()
                );

            }

            System.out.println("+--------+-------+-------------------+");
     }
    public void optionShowRDM() {
        Student student = getUser();
        List<SubjectStudent> subjects = student.getSubjects();

        List<String> headers = List.of(
                "Máteria",
                "Nota 1",
                "Nota 2",
                "Faltas",
                "Final",
                "Status"
        );

        Function<SubjectStudent, List<?>> callBack = (subject -> {
            return List.of(
                    subject.getName(),
                    subject.getNote1(),
                    subject.getNote2(),
                    subject.getAbsences(),
                    subject.getFinalExameScore(),
                    subject.getStatus()
            );
        });

        Utils.printTable(subjects, callBack, headers);
    }
    public void optionShowCurriculum() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<Subject> subjects = academicSystem.db.subjects.getAll();

        List<String> headers = List.of(
                "Codígo",
                "Horas",
                "Nome"
        );

        Function<Subject, List<?>> callBack = (subject -> {
            return List.of(
                    subject.getCode(),
                    subject.getHours(),
                    subject.getName()
            );
        });

        Utils.printTable(subjects, callBack, headers);
    }
    public void optionShowEntranceExam() {
        Student student = getUser();
        EntranceExam entranceExam = student.getEntranceExam();

        List<String> headers = List.of("Ciências Humanas",
                "Ciências da Natureza",
                "Linguagens Códigos",
                "Mátematica",
                "Redação"
        );

        Function<EntranceExam, List<?>> callBack = (entranceExam1 -> {
            return List.of(
                    entranceExam.getHumanities(),
                    entranceExam.naturalSciences,
                    entranceExam.getLanguages(),
                    entranceExam.getMathematics(),
                    entranceExam.getEssay()
            );
        });

        Utils.printTable(List.of(entranceExam), callBack, headers);
    }

    @Override
    public List<ISubMenuOption> getOptions() {

        return List.of(
                new OptionMenu("Ver RDM", this::optionShowRDM),
                new OptionMenu("Ver Grade Curricular", this::optionShowCurriculum),
                new OptionMenu("Ver Histórico", this::optionShowHistoric),
                new OptionMenu("Ver Vestibular", this::optionShowEntranceExam)
        );

    }

    @Override
    public Student getUser() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        return academicSystem.db.students.findById(studentId);
    }

    @Override
    public void run() {
        new MenuExecutor(this).run();
    }



}
