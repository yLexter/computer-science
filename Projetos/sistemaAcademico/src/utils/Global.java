package utils;

import database.Database;
import java.util.Scanner;

public class Global {
    private static Scanner scanner;
    public static Scanner getScanner() {
        if (scanner != null)
            return scanner;
        return scanner = new Scanner(System.in);
    }

    public Database getDatabase() {
        // ToDo instancia de Database
        return null;
    }

}
