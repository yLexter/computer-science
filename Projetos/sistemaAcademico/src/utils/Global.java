package utils;

import general.AcademicSystem;

import java.util.Scanner;

public class Global {
    private static Scanner scanner;
    private static AcademicSystem academicSystem;
    public static Scanner getScanner() {
        if (scanner != null)
            return scanner;
        return scanner = new Scanner(System.in);
    }

    public static AcademicSystem getAcademicSystem() {
        if (academicSystem != null)
            return academicSystem;
        return academicSystem = new AcademicSystem();
    }


}
