package utils;

import java.util.Scanner;

public class Global {
    private static Scanner scanner;
    public static Scanner getScanner() {
        if (scanner != null)
            return scanner;
        return scanner = new Scanner(System.in);
    }

}
