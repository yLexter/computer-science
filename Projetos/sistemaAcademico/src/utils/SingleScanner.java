package utils;

import java.util.Scanner;

public class SingleScanner {
    private static Scanner scanner;
    public static Scanner getScanner() {
        if (scanner != null)
            return scanner;
        return scanner = new Scanner(System.in);
    }

}
