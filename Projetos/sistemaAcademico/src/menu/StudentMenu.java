package menu;

import general.*;
import interfaces.ISubMenu;
import interfaces.ISubMenuOption;
import utils.Global;
import utils.Utils;

import java.util.*;

// ToDo Implementar Construtor, interface do rdm e pegar dados da db do estudante
public class StudentMenu implements ISubMenu {

    private Student student;
    public StudentMenu(Student student) {
        this.student = student;
    }
    public void OptionShowHistoric() {

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
    public void OptionShowRDM() {

        List<SubjectStudent> subjects = student.getSubjects();


        System.out.println("----------------------------------------------------------------------------------------");
        System.out.format("| %-15s|   %-10s|   %-10s|   %-10s|   %-10s|   %-10s |%n", "Máteria", "Nota 1", "Nota 2", "Faltas", "Final", "Status");
        System.out.println("----------------------------------------------------------------------------------------");

        for (SubjectStudent subject : student.getSubjects()) {
            System.out.format("| %-15s|   %-10s|   %-10s|   %-10s|   %-10s|   %-10s |%n",
                    subject.getName(),
                    subject.getNote1(),
                    subject.getNote2(),
                    subject.getAbsences(),
                    subject.getFinalExameScore(),
                    subject.getStatus());

        }

        System.out.println("---------------------------------------------------------------------------------------");

    }
    public void OptionShowCurriculum() {
            AcademicSystem academicSystem = Global.getAcademicSystem();
            List<Subject> allSubjects = academicSystem.db.subjects.getAll();
            Subject biggestNameSubject = Utils.getSubjectWithBiggerName(allSubjects);

            System.out.println("+----------+-------+--------+-----------+----------------------");
            System.out.format("| %-8s | %-3s | %-10s %n", "Código", "Horas" , "Nome");
            System.out.println("+----------+-------+---------+----------+----------------------");

            for (Subject subject : allSubjects) {
                String complementarySpaces = " ".repeat(
                        biggestNameSubject.getName().length() - subject.getName().length()
                );

                System.out.format("| %-8s |  %-3s | %-10s %s |%n",
                        subject.getCode(),
                        subject.getHours(),
                        subject.getName(),
                        complementarySpaces
                );
            }
           System.out.println("+--------+-------+-------------------+");
    }

    @Override
    public Map<Integer, ISubMenuOption> getOptions() {

        Map<Integer, ISubMenuOption>  newOptions = new LinkedHashMap<>();

        newOptions.put(1, new OptionMenu("Ver RDM", this::OptionShowRDM));
        newOptions.put(2, new OptionMenu("Ver Grade Curricular", this::OptionShowCurriculum));
        newOptions.put(3, new OptionMenu("Ver Histórico", this::OptionShowHistoric ));

        return newOptions;
    }

    @Override
    public void run() {
        new MenuExecutor(this).run();
    }


}
