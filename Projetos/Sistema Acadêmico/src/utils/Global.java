package utils;

import database.*;
import database.DatabaseMemory.*;
import general.AcademicSystem;
import general.AcademicSystemSettings;

import java.util.Scanner;

/**
 * A classe Global contém métodos estáticos para acessar objetos globais do sistema.
 */
public class Global {
    private static Scanner scanner;
    private static AcademicSystem academicSystem;


  /**
     * Obtém uma instância do Scanner para entrada de dados do usuário.
     * Se o Scanner já estiver inicializado, retorna a instância existente;
     * caso contrário, cria uma nova instância e a retorna.
     * @return Instância do Scanner.
     */
    public static Scanner getScanner() {
        if (scanner != null)
            return scanner;
        return scanner = new Scanner(System.in);
    }

   /**
     * Obtém uma instância do AcademicSystem, que representa o sistema acadêmico.
     * Se o AcademicSystem já estiver inicializado, retorna a instância existente;
     * caso contrário, cria uma nova instância com as configurações padrão e a retorna.
     * @return Instância do AcademicSystem.
     */
    public static AcademicSystem getAcademicSystem() {
        if (academicSystem != null)
            return academicSystem;

        AcademicSystemSettings academicSystemSettings = new AcademicSystemSettings(
              "Computação",
              2,
              false,
              "1.0.0",
              2,
              7,
              3
        );

        Database db = new Database(
              new DatabaseStudent(),
              new DatabaseTeacher(),
              new DatabaseAdmin(),
              new DatabaseSubject(),
              new DatabaseAcademicSystemSettings(academicSystemSettings),
              new DatabaseCollegeClass(),
              new DatabaseRoom(),
              new DatabaseClassSchedule()
        );

        return academicSystem = new AcademicSystem(db);
    }

}
