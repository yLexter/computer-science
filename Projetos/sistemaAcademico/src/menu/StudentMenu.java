package menu;

import general.*;
import interfaces.ISubMenu;
import interfaces.ISubMenuOption;
import utils.Utils;

import java.util.*;

// ToDo Implementar Construtor, interface do rdm e pegar dados da db do estudante
public class StudentMenu implements ISubMenu {

    private Student student;

    private record OptionShowHistoric(String label) implements ISubMenuOption {

        @Override
        public void run() {

            List<SubjectStudent> subjects = null;
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
    }

    private record OptionShowRDM(String label) implements ISubMenuOption {

        @Override
        public void run() {




        }


    }

    private record OptionShowCurriculum(String label) implements ISubMenuOption {

        @Override
        public void run() {
            List<Subject> allSubjects = Utils.getSubjects();
            Subject biggestNameSubject = Utils.getSubjectWithBiggerName(allSubjects);

            System.out.println("+----------+-------+--------+-----------+----------------------");
            System.out.println("| Código | Horas | Nome");
            System.out.println("+----------+-------+---------+----------+----------------------");

            for (Subject subject : allSubjects) {
                String complementarySpaces = " ".repeat(
                        biggestNameSubject.getName().length() - subject.getName().length()
                );

                System.out.printf("| %s | %d | %s %s |\n",
                        subject.getCode(),
                        subject.getHours(),
                        subject.getName(),
                        complementarySpaces
                );
            }

            System.out.println("+--------+-------+-------------------+");
        }

    }

    @Override
    public Map<Integer, ISubMenuOption> getOptions() {

        Map<Integer, ISubMenuOption>  newOptions = new LinkedHashMap<>();

        newOptions.put(1, new OptionShowRDM("Ver RDM"));
        newOptions.put(2, new OptionShowCurriculum("Ver Grade Curricular"));
        newOptions.put(3, new OptionShowHistoric("Ver Histórico"));

        return newOptions;
    }

    @Override
    public void run() {
        new Menu(this).run();
    }


}
