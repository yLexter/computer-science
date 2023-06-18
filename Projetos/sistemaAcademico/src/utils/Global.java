package utils;

import database.*;
import general.AcademicSystem;
import general.Admin;
import general.GeneralInformation;

import java.time.LocalDate;
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

        GeneralInformation generalInformation = new GeneralInformation(
              "Computação",
              2,
              false,
              "1.0.0"
        );


        Database db = new Database(
          new DatabaseStudent(),
          new DatabaseTeacher(),
          new DatabaseAdmin(),
          new DatabaseSubjects(DatabaseSubjects.getSubjects()),
          new DatabaseGeneralInformation(generalInformation),
          new DatabaseCollegeClass()
        );

        return academicSystem = new AcademicSystem(db);
    }





}
