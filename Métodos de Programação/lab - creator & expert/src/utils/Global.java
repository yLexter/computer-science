package utils;

import entities.ControleAcademico;
import java.util.Scanner;

public class Global {
    private static ControleAcademico controleAcademico;

    public static ControleAcademico getControleAcademico() {

        if (controleAcademico == null) {
            controleAcademico = new ControleAcademico();
        }

        return controleAcademico;
    }
}
