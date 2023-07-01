package menu;

import general.*;
import interfaces.menu.IMenuEmployee;
import interfaces.menu.ISubMenuOption;
import utils.Decoration;
import utils.Global;
import utils.Utils;

import java.util.*;

import java.util.function.Function;

public class StudentMenu implements IMenuEmployee<Student> {

    private String studentId;

  /**
 * A classe StudentMenu implementa o menu de opções disponíveis para um estudante.
 * Ele exibe opções como visualizar o RDM, a grade curricular, o histórico e o resultado do vestibular.
 */
    public StudentMenu(String studentId) {
        this.studentId = studentId;
    }

  /**
     * Opção do menu para exibir o histórico acadêmico do estudante.
     */
    public void optionShowHistoric() {

        Student student = getUser();
        List<SubjectStudent> subjects = student.getHistoric();

        if (subjects.size() == 0) {
            Decoration.showMessageAndClearScreen("Hustórico vazio");
            return;
        }

        List<String> headers = List.of(
                "Código",
                "Componente",
                "Carga Hóraria",
                "Média",
                "Faltas",
                "Status",
                "Periodo"
        );

        Function<SubjectStudent, List<?>> callBack = (subject -> {
            return List.of(
                    subject.getCode(),
                    subject.getName(),
                    subject.getHours(),
                    subject.getAverage(),
                    subject.getAbsences(),
                    subject.getStatus(),
                    subject.getPeriod()
            );
        });

        System.out.println("| ---------------------------------|");
        System.out.printf("| Matricula: %s           |\n", student.getId());
        System.out.printf("| CRA: %.2f                       |\n", student.getCRA());
        System.out.println("---------------------------------|");

        Utils.printTable(subjects, callBack, headers);
     }

   /**
     * Opção do menu para exibir o RDM (Registro de Disciplinas Matriculadas) do estudante.
     */
    public void optionShowRDM() {
        Student student = getUser();
        ArrayList<SubjectStudent> subjects = student.getSubjectStudent();

        if (subjects.size() == 0) {
            Decoration.showMessageAndClearScreen("RDM vazio");
            return;
        }

        List<String> headers = List.of(
                "Máteria",
                "Nota 1",
                "Nota 2",
                "Faltas",
                "Final",
                "Status"
        );

        Function<SubjectStudent, List<?>> callBack = (subject -> {
            List<String> notes = subject.getListNotes();

            return List.of(
                    subject.getName(),
                    notes.get(1),
                    notes.get(1),
                    subject.getAbsences(),
                    notes.get(2),
                    subject.getStatus()
            );
        });


        Utils.printTable(subjects, callBack, headers);
    }

/**
     * Opção do menu para exibir a grade curricular.
     */

    public void optionShowCurriculum() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        List<Subject> subjects = academicSystem.db.subjects.getAll();

        if (subjects.size() == 0) {
            Decoration.showMessageAndClearScreen("Grade Curricular vazio");
            return;
        }

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


  /**
     * Opção do menu para exibir os resultados do vestibular do estudante.
     */
    public void optionShowEntranceExam() {
        Student student = getUser();
        EntranceExam entranceExam = student.getEntranceExam();

        List<String> headers = List.of(
                "Ciências Humanas",
                "Ciências da Natureza",
                "Linguagens Códigos",
                "Mátematica",
                "Redação"
        );

        Function<EntranceExam, List<?>> callBack = (entranceExam1 -> {
            return List.of(
                    entranceExam.getHumanities(),
                    entranceExam.getNaturalSciences(),
                    entranceExam.getLanguages(),
                    entranceExam.getMathematics(),
                    entranceExam.getEssay()
            );
        });

        Utils.printTable(List.of(entranceExam), callBack, headers);
    }

    /**
     * Obtem a lista de opções do menu.
     */
    @Override
    public List<ISubMenuOption> getOptions() {

        return List.of(
                new OptionMenu("Ver RDM", this::optionShowRDM),
                new OptionMenu("Ver Grade Curricular", this::optionShowCurriculum),
                new OptionMenu("Ver Histórico", this::optionShowHistoric),
                new OptionMenu("Ver Vestibular", this::optionShowEntranceExam)
        );

    }

   /**
     * Obtem a lista de opções do menu.
     */
    @Override
    public String getHeader() {
        Student student = getUser();
        return Decoration.generateWelcomeHeader(student.getFullName());
    }

  /**
     * Busca o estudante no banco de dados.
     */
    @Override
    public Student getUser() {
        AcademicSystem academicSystem = Global.getAcademicSystem();
        return academicSystem.db.students.findById(studentId);
    }

  
  /**
     * Executa o menu
     */
    @Override
    public void run() {
        new MenuExecutor(this).run();
    }



}
