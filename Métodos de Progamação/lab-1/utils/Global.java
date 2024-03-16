package utils;

import entities.ControleAcademico;
import java.util.Scanner;

public class Global {
    private static ControleAcademico controleAcademico;
    private static Scanner scanner;

    public static ControleAcademico getControleAcademico() {
        if (controleAcademico != null) {
            return controleAcademico;
        }

        return controleAcademico = new ControleAcademico();
    }
}
