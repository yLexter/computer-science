package utils;

import database.*;
import database.DatabaseMemory.*;
import general.AcademicSystem;
import general.GeneralInformation;

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
              "1.0.0",
              2
        );


        Database db = new Database(
          new DatabaseStudent(),
          new DatabaseTeacher(),
          new DatabaseAdmin(),
          new DatabaseSubject(),
          new DatabaseGeneralInformation(generalInformation),
          new DatabaseCollegeClass(),
          new DatabaseClassRoom(),
          new DatabaseRoom()
        );

        return academicSystem = new AcademicSystem(db);
    }





}
